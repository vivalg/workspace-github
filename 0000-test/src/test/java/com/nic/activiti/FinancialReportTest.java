package com.nic.activiti;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class FinancialReportTest{
	
	private ProcessEngine processEngine;
	private RepositoryService repositoryService;
	private RuntimeService runtimeService;
	private TaskService taskService;
	
	public FinancialReportTest() {
		// Create Activiti process engine
		this.processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("./activiti-config/activiti.cfg.xml").buildProcessEngine();
		this.repositoryService = processEngine.getRepositoryService();
		this.runtimeService = processEngine.getRuntimeService();
		this.taskService = processEngine.getTaskService();
	}

	@Test
	public void deployRepository(){
		this.repositoryService.createDeployment().addClasspathResource("./activiti-config/FinancialReportProcess.bpmn20.xml").deploy();
	}
	
	@Test
	public void startProcess(){
		this.runtimeService.startProcessInstanceByKey("financialReport");
	}
	
	@Test
	public void claimTask() {

		List<Task> tasks = this.taskService.createTaskQuery().taskCandidateGroup("accountancy").list();
		for (Task task : tasks) {
			this.taskService.claim(task.getId(), "fozzie");
			System.out.println("fozzie claim accountancy task - id: " + task.getId() + ", name: " + task.getName());
		}

	}
	
	@Test
	public void completeTask() {

		List<Task> tasks = this.taskService.createTaskQuery().taskAssignee("fozzie").list();
		for (Task task : tasks) {
			this.taskService.complete(task.getId());
			System.out.println("fozzie complete - id: " + task.getId() + ", name: " + task.getName());
		}
	}
	
	@Test
	public void claimTask2() {

		// Retrieve and claim the second task
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
		for (Task task : tasks) {
			this.taskService.claim(task.getId(), "kermit");
			System.out.println("kermit claim management task - id: " + task.getId() + ", name: " + task.getName());
		}
	}

	@Test
	public void completeTask2() {

		// Completing the second task ends the process
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("kermit").list();
		for (Task task : tasks) {
			taskService.complete(task.getId());
			System.out.println("kermit complete - id: " + task.getId() + ", name: " + task.getName());
		}
	}
	
	@Test
	public void history(){
	    
	    // verify that the process is actually finished
		HistoryService historyService = processEngine.getHistoryService();
		List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().list();
		System.out.println(list.size());
		for (HistoricProcessInstance historicTaskInstance : list) {
			String procId = historicTaskInstance.getId();
			HistoricProcessInstance historicProcessInstance = historyService
					.createHistoricProcessInstanceQuery().processInstanceId(procId) .singleResult();
			System.out.println("Process instance end time: " + historicProcessInstance.getEndTime());
			
		}
	}
}
