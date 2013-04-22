package com.nic.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RuntimeService;

public class FinancialReport {
	
	public static void main(String[] args) {
		// Create Activiti process engine
		ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration().buildProcessEngine();
//		ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("./activiti.cfg.xml").buildProcessEngine();

//		// Get Activiti services
//		RepositoryService repositoryService = processEngine.getRepositoryService();
		RuntimeService runtimeService = processEngine.getRuntimeService();
//
//		// Deploy the process definition
//		repositoryService.createDeployment().addClasspathResource("./FinancialReportProcess.bpmn20.xml").deploy();
//
		// Start a process instance
		runtimeService.startProcessInstanceByKey("financialReport");
		
		
		
	    // Get the first task
//	    TaskService taskService = processEngine.getTaskService();
//		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();
//	    for (Task task : tasks) {
//	      System.out.println("Following task is available for accountancy group: " + task.getName());
//	      
//	      // claim it
//	      taskService.claim(task.getId(), "fozzie");
//	    }
		
	    
		
//	    // Verify Fozzie can now retrieve the task
//	    tasks = taskService.createTaskQuery().taskAssignee("fozzie").list();
//	    for (Task task : tasks) {
//	      System.out.println("Task for fozzie: " + task.getName());
//	      
//	      // Complete the task
//	      taskService.complete(task.getId());
//	    }
//	    
//	    System.out.println("Number of tasks for fozzie: " + taskService.createTaskQuery().taskAssignee("fozzie").count());
	    
	    
	    
//	    // Retrieve and claim the second task
//	    tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
//	    for (Task task : tasks) {
//	      System.out.println("Following task is available for accountancy group: " + task.getName());
//	      taskService.claim(task.getId(), "kermit");
//	    }


	    
//	    // Completing the second task ends the process
//	    tasks = taskService.createTaskQuery().taskAssignee("kermit").list();
//	    for (Task task : tasks) {
//	      taskService.complete(task.getId());
//	    }
	    
	    
	    
	    // verify that the process is actually finished
//	    HistoryService historyService = processEngine.getHistoryService();
//	    List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().list();
//	    System.out.println(list.size());
//	    for (HistoricProcessInstance historicTaskInstance : list) {
//	    	String procId = historicTaskInstance.getId();
//			HistoricProcessInstance historicProcessInstance = historyService
//					.createHistoricProcessInstanceQuery().processInstanceId(procId) .singleResult();
//			System.out.println("Process instance end time: " + historicProcessInstance.getEndTime());
//			
//		}

	}
}
