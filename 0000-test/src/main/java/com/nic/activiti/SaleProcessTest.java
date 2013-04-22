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

public class SaleProcessTest {

	private ProcessEngine processEngine;
	private RepositoryService repositoryService;
	private RuntimeService runtimeService;
	private TaskService taskService;

	public SaleProcessTest() {
		// Create Activiti process engine
		this.processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("./activiti.cfg.xml").buildProcessEngine();
		this.taskService = processEngine.getTaskService();
	}

	@Test
	public void deployRepository(){
		this.repositoryService = processEngine.getRepositoryService();
		this.repositoryService.createDeployment().addClasspathResource("./SaleProcess.bpmn20.xml").deploy();
	}

	@Test
	public void startProcess(){
		this.runtimeService = processEngine.getRuntimeService();
		this.runtimeService.startProcessInstanceByKey("saleProcess");
	}

	@Test
	public void claimTask() {
	
		List<Task> tasks = this.taskService.createTaskQuery().taskCandidateGroup("client").list();
		for (Task task : tasks) {
			this.taskService.claim(task.getId(), "client01");
			System.out.println("client01 claim accountancy task - id: " + task.getId() + ", name: " + task.getName());
		}
	
	}

	@Test
	public void completeTask() {
	
		List<Task> tasks = this.taskService.createTaskQuery().taskAssignee("client01").list();
		for (Task task : tasks) {
			this.taskService.complete(task.getId());
			System.out.println("client01 complete - id: " + task.getId() + ", name: " + task.getName());
		}
	}

	@Test
	public void claimTask2() {
	
		// Retrieve and claim the second task
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("sales").list();
		for (Task task : tasks) {
			this.taskService.claim(task.getId(), "sales01");
			System.out.println("sales01 claim management task - id: " + task.getId() + ", name: " + task.getName());
		}
	}

	@Test
	public void completeTask2() {
	
		// Completing the second task ends the process
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("sales01").list();
		for (Task task : tasks) {
			taskService.complete(task.getId());
			System.out.println("sales01 complete - id: " + task.getId() + ", name: " + task.getName());
		}
	}
	
	@Test
	public void claimTask3() {
		
		// Retrieve and claim the second task
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("manager").list();
		for (Task task : tasks) {
			this.taskService.claim(task.getId(), "manager01");
			System.out.println("manager01 claim management task - id: " + task.getId() + ", name: " + task.getName());
		}
	}
	
	@Test
	public void completeTask3() {
		
		// Completing the second task ends the process
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("manager01").list();
		for (Task task : tasks) {
			taskService.complete(task.getId());
			System.out.println("manager01 complete - id: " + task.getId() + ", name: " + task.getName());
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
