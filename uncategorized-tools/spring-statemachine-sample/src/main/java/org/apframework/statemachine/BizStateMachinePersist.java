package org.apframework.statemachine;

import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Shoukai Huang
 * @Date: 2018/9/28 22:04
 */
@Component
public class BizStateMachinePersist implements StateMachinePersist<TurnstileStates, TurnstileEvents, Integer> {

    static Map<Integer, TurnstileStates> cache = new HashMap<>(16);

    @Override
    public void write(StateMachineContext<TurnstileStates, TurnstileEvents> stateMachineContext, Integer integer) throws Exception {
        cache.put(integer, stateMachineContext.getState());
    }

    @Override
    public StateMachineContext<TurnstileStates, TurnstileEvents> read(Integer integer) throws Exception {
        return cache.containsKey(integer) ?
                new DefaultStateMachineContext<>(cache.get(integer), null, null, null, null, "turnstileStateMachine") :
                new DefaultStateMachineContext<>(TurnstileStates.Locked, null, null, null, null, "turnstileStateMachine");
    }
}
