package com.nic.activiti.springintegrated;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbsProcess implements IProcess{
    
    @Autowired
    protected ProcessEngine processEngine;
    
    @Autowired
    protected TaskService taskService;
    
    @Autowired
    protected RuntimeService runtimeService;
    
    @Autowired
    protected HistoryService historyService;
    
    @Autowired
    protected RepositoryService repositoryService;
    
    @Autowired
    protected ManagementService managementService;
    
    @Autowired
    protected IdentityService identityService;
	
//	public AbsProcess() {
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("activiti-spring.cfg.xml");
//		this.processEngine = (ProcessEngine) applicationContext.getBean("processEngine");
//		this.taskService = (TaskService) applicationContext.getBean("taskService");
//		this.runtimeService = (RuntimeService) applicationContext.getBean("runtimeService");
//		this.historyService = (HistoryService) applicationContext.getBean("historyService");
//		this.repositoryService = (RepositoryService) applicationContext.getBean("repositoryService");
//		this.managementService = (ManagementService) applicationContext.getBean("managementService");
//		this.identityService = (IdentityService) applicationContext.getBean("identityService");
//	}

	protected abstract String getProcessDefinitionKey();

    @Override
	public ProcessInstance startNewProcess(String userId) {
	    System.out.println(">>process definition key: " + this.getProcessDefinitionKey());
	    this.identityService.setAuthenticatedUserId(userId);
		return this.runtimeService.startProcessInstanceByKey(this.getProcessDefinitionKey());
	}
	
	@Override
	public List<ProcessInstance> getRuntimeProcessInstances() {
		return this.runtimeService.createProcessInstanceQuery().processDefinitionKey(this.getProcessDefinitionKey()).list();
	}

	@Override
	public List<Task> getRuntimeTasks() {
		return this.taskService.createTaskQuery().processDefinitionKey(this.getProcessDefinitionKey()).list();
	}
	
	@Override
	public List<HistoricProcessInstance> getHistoricProcessInstances() {
		return this.historyService.createHistoricProcessInstanceQuery().processDefinitionKey(this.getProcessDefinitionKey()).list();
	}

	@Override
	public List<HistoricTaskInstance> getHistoricTasks() {
		return this.historyService.createHistoricTaskInstanceQuery().processDefinitionKey(this.getProcessDefinitionKey()).list();
	}
	
}
