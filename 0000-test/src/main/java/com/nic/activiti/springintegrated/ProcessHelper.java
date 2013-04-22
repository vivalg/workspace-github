package com.nic.activiti.springintegrated;

import java.util.HashMap;
import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ProcessHelper {
    
    static final Logger logger = Logger.getLogger(ProcessHelper.class);
	
	public static final ProcessEngine processEngine;
	public static final TaskService taskService;
	public static final RuntimeService runtimeService;
	public static final HistoryService historyService;
	public static final RepositoryService repositoryService;
	public static final ManagementService managementService;
	public static final IdentityService identityService;
	
	//初始化
	static{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("activiti-spring.cfg.xml");
		processEngine = (ProcessEngine) applicationContext.getBean("processEngine");
		taskService = (TaskService) applicationContext.getBean("taskService");
		runtimeService = (RuntimeService) applicationContext.getBean("runtimeService");
		historyService = (HistoryService) applicationContext.getBean("historyService");
		repositoryService = (RepositoryService) applicationContext.getBean("repositoryService");
		managementService = (ManagementService) applicationContext.getBean("managementService");
		identityService = (IdentityService) applicationContext.getBean("identityService");
	}
	
	/**
	 * 创建一个新的流程实例
	 * @param processDefinitionKey
	 * @return
	 */
	public static ProcessInstance startNewProcessInstance(String userId, String processDefinitionKey){
		ProcessHelper.processEngine.getIdentityService().setAuthenticatedUserId(userId);
		return runtimeService.startProcessInstanceByKey(processDefinitionKey);
	}
	

	/**
	 * 根据任务责任人获取当前所有任务
	 * @param assignee
	 * @return
	 */
	public static List<Task> getRuntimeTasksByAssignee(String assignee){
		return taskService.createTaskQuery().taskAssignee(assignee).list();
	}

	public static ProcessDefinition getProcessDefinitionByTask(Task task) {
		String processDefinitionId = task.getProcessDefinitionId();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
		return processDefinition;
	}

	public static ProcessInstance getProcessInstanceByTask(Task task) {
		String processInstanceId = task.getProcessInstanceId();
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		return processInstance;
	}

	public static List<Task> getTasksByProcessInstanceId(String processInstanceId){
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
		return tasks;
	}

	public static List<HistoricTaskInstance> getHistoricTaskInstanceByProcessInstanceId(String processInstanceId){
		return historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).list();
	}

	public static void deleteProcessCascade(String processDefinitionKey) {
		List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).list();
		for (ProcessDefinition processDefinition : processDefinitions) {
			repositoryService.deleteDeployment(processDefinition.getDeploymentId(), true);
		}
	}
	
    public static void runFullProcess(String processDefinitionKey) {
        HashMap<String, Object> variables = new HashMap<String, Object>();
        variables.put("marker", "three");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);
        String processInstanceId = processInstance.getId();
        for (List<Task> tasks = ProcessHelper.getTasksByProcessInstanceId(processInstanceId); tasks.size() > 0; tasks = ProcessHelper .getTasksByProcessInstanceId(processInstanceId)) {
            for (Task task : tasks) {
                taskService.claim(task.getId(), "kermit");
                logger.info("kermit claim task: " + task.getId() + ", " + task.getName());
                taskService.complete(task.getId(), variables);
                logger.info("complete task: " + task.getId() + ", " + task.getName());
            }
            System.out.println();
        }

        System.out.println("over.");
    }
}
