package org.apframework.activiti;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 积分申报流程demo
 * 包含功能:多实例会签、子流程并行审批、动态设置下一节点执行人员、任务超时自动完成
 *
 * @author guolf
 */
public class CreditsSample {
    public static void main(String[] args) throws InterruptedException {
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000").setJdbcUsername("sa").setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setJobExecutorActivate(true)
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine processEngine = cfg.buildProcessEngine();
        String pName = processEngine.getName();
        String ver = ProcessEngine.VERSION;
        System.out.println("ProcessEngine [" + pName + "] Version: [" + ver + "]");

        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 流程部署
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("credits_sample.bpmn")
                .name("流程测试")
                .category("")
                .deploy();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId()).singleResult();
        System.out.println("流程名称 ： [" + processDefinition.getName() + "]， 流程ID ： ["
                + processDefinition.getId() + "], 流程KEY : " + processDefinition.getKey());

        IdentityService identityService = processEngine.getIdentityService();
        // 启动流程
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 分配任务的人员
        List<String> assigneeList = new ArrayList<String>();
        assigneeList.add("tom");
        assigneeList.add("jeck");
        assigneeList.add("mary");
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("assigneeList", assigneeList);
        identityService.setAuthenticatedUserId("createUserId");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess", "Key001", vars);

        System.out.println("流程实例ID = " + processInstance.getId());
        System.out.println("正在活动的流程节点ID = " + processInstance.getActivityId());
        System.out.println("流程定义ID = " + processInstance.getProcessDefinitionId());

        // 查询指定人的任务
        // ============ 会签任务开始 ===========
        Map mapConfirm = new HashMap();
        mapConfirm.put("confirmSts", 1);

        TaskService taskService = processEngine.getTaskService();
        List<Task> taskList1 = taskService.createTaskQuery().taskAssignee("mary").orderByTaskCreateTime().desc().list();
        System.out.println("taskList1 = " + taskList1);

        Task task1 = taskList1.get(0);
        taskService.setVariablesLocal(task1.getId(), mapConfirm);
        taskService.complete(task1.getId());

        List<Task> taskList2 = taskService.createTaskQuery().taskAssignee("jeck").orderByTaskCreateTime().desc().list();
        System.out.println("taskList2 = " + taskList2);
        Map mapConfirm1 = new HashMap();
        mapConfirm1.put("confirmSts", 1);
        Task task2 = taskList2.get(0);
        taskService.setVariablesLocal(task2.getId(), mapConfirm1);
        taskService.complete(task2.getId());

        List<Task> taskList3 = taskService.createTaskQuery().taskAssignee("tom").orderByTaskCreateTime().desc().list();
        System.out.println("taskList3 = " + taskList3);

        Task task3 = taskList3.get(0);
        taskService.setVariablesLocal(task3.getId(), mapConfirm1);
        taskService.complete(task3.getId());
        // ============ 会签任务结束 ===========

        // 部门主任
        List<Task> taskListDept1 = taskService.createTaskQuery().taskAssignee("dept").orderByTaskCreateTime().desc().list();
        System.out.println("taskListDept1 = " + taskListDept1);
        taskService.complete(taskListDept1.get(0).getId());

        // =============子流程任务开始==========

        // 市场专员
        List<Task> taskListSczy = taskService.createTaskQuery().taskAssignee("sczy").orderByTaskCreateTime().desc().list();
        System.out.println("taskListSczy = " + taskListSczy);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pass", true);
        taskService.complete(taskListSczy.get(0).getId(), map);

        // 市场主任
        List<Task> taskListSczr = taskService.createTaskQuery().taskAssignee("sczr").orderByTaskCreateTime().desc().list();
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("pass3", true);
        System.out.println("taskListSczr = " + taskListSczr);
        taskService.complete(taskListSczr.get(0).getId(), map3);

        // 财务专员
        List<Task> taskListCwzy = taskService.createTaskQuery().taskAssignee("cwzy").orderByTaskCreateTime().desc().list();
        System.out.println("taskListCwzy = " + taskListCwzy);
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("pass1", true);
        taskService.complete(taskListCwzy.get(0).getId(), map1);

        // 财务主任
        List<Task> taskListCwzr = taskService.createTaskQuery().taskAssignee("cwzr").orderByTaskCreateTime().desc().list();
        System.out.println("taskListCwzr = " + taskListCwzr);
        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("pass4", true);
        taskService.complete(taskListCwzr.get(0).getId(), map4);

        List<Task> taskMe = taskService.createTaskQuery().taskAssignee("me").orderByTaskCreateTime().desc().list();
        System.out.println("taskMe = " + taskMe);

        // =============子流程任务结束==========


        // 技术专员审批
        List<Task> taskListJishu = taskService.createTaskQuery().taskAssignee("jishu").orderByTaskCreateTime().desc().list();
        System.out.println("taskListJishu = " + taskListJishu);
        taskService.complete(taskListJishu.get(0).getId());

        // 综合部公示
        List<Task> taskListZonghe = taskService.createTaskQuery().taskAssignee("zhb").orderByTaskCreateTime().desc().list();
        System.out.println("taskListZonghe = " + taskListZonghe);
        taskService.complete(taskListZonghe.get(0).getId());

        // 公示确认，5秒未完成自动进入下一流程
        List<Task> taskListPublic = taskService.createTaskQuery().taskAssignee("张三").orderByTaskCreateTime().desc().list();
        System.out.println("taskListPublic = " + taskListPublic);
        taskService.complete(taskListPublic.get(0).getId());

        Thread.sleep(1000 * 10);

        // 分管领导确认
        List<Task> taskListLeader = taskService.createTaskQuery().taskAssignee("leader").orderByTaskCreateTime().desc().list();
        System.out.println("taskListLeader = " + taskListLeader);
        taskService.complete(taskListLeader.get(0).getId());

        // ==================流程结束======================

        // 历史任务查询
        List<HistoricActivityInstance> historicActivityInstances = processEngine.getHistoryService()
                // 创建历史活动实例查询
                .createHistoricActivityInstanceQuery()
                //.finished() // 查询已经完成的任务
                .orderByHistoricActivityInstanceEndTime()
                .asc()
                .list();
        for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
            System.out.println("任务ID:" + historicActivityInstance.getId());
            System.out.println("流程实例ID:" + historicActivityInstance.getProcessInstanceId());
            System.out.println("活动名称：" + historicActivityInstance.getActivityName());
            System.out.println("办理人：" + historicActivityInstance.getAssignee());
            System.out.println("开始时间：" + historicActivityInstance.getStartTime());
            System.out.println("结束时间：" + historicActivityInstance.getEndTime());
            System.out.println("===========================");
        }

        for (HistoricTaskInstance historicTaskInstance : processEngine.getHistoryService().createHistoricTaskInstanceQuery().taskDefinitionKey("usertask1").list()) {
            System.out.println("historicTaskInstance = " + historicTaskInstance);
        }

    }
}
