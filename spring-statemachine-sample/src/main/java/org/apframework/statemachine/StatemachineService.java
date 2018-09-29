package org.apframework.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

/**
 * @Author: Shoukai Huang
 * @Date: 2018/9/29 23:14
 */
@Service
public class StatemachineService {

    @Autowired
    private StateMachinePersister<TurnstileStates, TurnstileEvents, Integer> stateMachinePersist;
    @Autowired
    private StateMachineFactory<TurnstileStates, TurnstileEvents> stateMachineFactory;

    public void execute(Integer businessId, TurnstileEvents event, Map<String, Object> context) {
        // 利用随记ID创建状态机，创建时没有与具体定义状态机绑定
        StateMachine<TurnstileStates, TurnstileEvents> stateMachine = stateMachineFactory.getStateMachine(UUID.randomUUID());
        stateMachine.start();
        try {
            // 在BizStateMachinePersist的restore过程中，绑定turnstileStateMachine状态机相关事件监听
            stateMachinePersist.restore(stateMachine, businessId);
            // 本处写法较为繁琐，实际为注入Map<String, Object> context内容到message中
            MessageBuilder<TurnstileEvents> messageBuilder = MessageBuilder
                    .withPayload(event)
                    .setHeader("BusinessId", businessId);
            if (context != null) {
                context.entrySet().forEach(p -> messageBuilder.setHeader(p.getKey(), p.getValue()));
            }
            Message<TurnstileEvents> message = messageBuilder.build();

            // 发送事件，返回是否执行成功
            boolean success = stateMachine.sendEvent(message);
            if (success) {
                stateMachinePersist.persist(stateMachine, businessId);
            } else {
                System.out.println("状态机处理未执行成功，请处理，ID：" + businessId + "，当前context：" + context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stateMachine.stop();
        }
    }
}
