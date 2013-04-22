package com.nic.activiti.springintegrated.process;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import com.nic.activiti.springintegrated.AbsProcess;
import com.nic.activiti.springintegrated.ProcessHelper;

public class GateWay01Process extends AbsProcess{

	@Override
    protected String getProcessDefinitionKey() {
        return "gateway01";
    }

    public static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@Test
	public void startProcess() {
	    this.identityService.setAuthenticatedUserId("kermit");
		this.runtimeService.startProcessInstanceByKey(this.getProcessDefinitionKey());
	}
	
	@Test
	public void listRunningTask(){
		List<Task> tasks = this.taskService.createTaskQuery().list();
		System.out.println("running:");
		for (Task task : tasks) {
			System.out.println(task.getProcessInstanceId() + ", "
								+ task.getId() + ", "  
								+ task.getName() + ", " 
								+ task.getDescription() + ", " 
								+ task.getAssignee() + "");
		}
	}
	
	@Test
	public void listHistoricTask(){
		List<HistoricTaskInstance> tasks = this.historyService.createHistoricTaskInstanceQuery().processDefinitionKey(this.getProcessDefinitionKey()).orderByProcessInstanceId().desc().list();
		System.out.println("historic:");
		for (HistoricTaskInstance task : tasks) {
			System.out.println(task.getProcessInstanceId() + ", " + task.getId() + ", " + task.getName() + ", " + task.getEndTime());
		}
	}
	
	@Test
	public void listRunningProcess(){
	    List<ProcessInstance> processInstances = this.runtimeService.createProcessInstanceQuery().list();
	    for(ProcessInstance processInstance : processInstances){
	        System.out.println(processInstance + ", " + processInstance.isSuspended());
	    }
	}
	
	@Test
	public void ownTask(){
		List<Task> tasks = this.taskService.createTaskQuery().processDefinitionKey(this.getProcessDefinitionKey()).list();
		for (Task task : tasks) {
			this.taskService.setOwner(task.getId(), "kermit");
			System.out.println("kermit owned " + task.getId());
		}
	}
	
	@Test
	public void claimTask(){
		List<Task> tasks = this.taskService.createTaskQuery().processDefinitionKey(this.getProcessDefinitionKey()).list();
		for (Task task : tasks) {
			this.taskService.claim(task.getId(), "kermit");
			System.out.println("kermit claimed: " + task.getId() + ", " + task.getName());
		}
	}
	
	@Test
	public void completeTask(){
		List<Task> tasks = this.taskService.createTaskQuery().processDefinitionKey(this.getProcessDefinitionKey()).list();
		HashMap<String, Object> variables = new HashMap<String, Object>();
		variables.put("marker", "three");
		for (Task task : tasks) {
			this.taskService.complete(task.getId(), variables);
			System.out.println("complete task : " + task.getId() + ", " + task.getName());
		}
	}
	
	@Test
	public void deleteProcessCascade(){
		ProcessHelper.deleteProcessCascade(this.getProcessDefinitionKey());
	}
	
	@Test
	public void runFullProcess(){
		HashMap<String, Object> variables = new HashMap<String, Object>();
		variables.put("marker", "three");
		ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey(this.getProcessDefinitionKey());
		String processInstanceId = processInstance.getId();
		for(List<Task> tasks = ProcessHelper.getTasksByProcessInstanceId(processInstanceId);tasks.size() > 0; tasks = ProcessHelper.getTasksByProcessInstanceId(processInstanceId)){
			for (Task task : tasks) {
				this.taskService.claim(task.getId(), "kermit");
				System.out.println("kermit claim task: " + task.getId() + ", " + task.getName());
				this.taskService.complete(task.getId(), variables);
				System.out.println("complete task: " + task.getId() + ", " + task.getName());
			}
			System.out.println();
		}
		
		System.out.println("over.");
	}
	
	
	@Test
	public void listVariables(){
		
		List<ProcessInstance> processInstances = this.runtimeService.createProcessInstanceQuery().list();
		
		for (ProcessInstance processInstance : processInstances) {
			System.out.println("process: " + processInstance.getId() + ", " + this.repositoryService.createProcessDefinitionQuery().processDefinitionId(processInstance.getProcessDefinitionId()).singleResult().getName());
				
			Map<String, Object> variables = this.runtimeService.getVariablesLocal(processInstance.getId());
			Iterator<Entry<String, Object>> it = variables.entrySet().iterator();
			while(it.hasNext()){
				Entry<String, Object> entry = it.next();
				System.out.println("		key: " + entry.getKey() + ", value: " + entry.getValue());
			}
			System.out.println();
		}
	}
	
    @Test
    public void listFormData() {
        List<Task> tasks = this.taskService.createTaskQuery().list();
        for(Task task : tasks){
            TaskFormData taskFormData = this.processEngine.getFormService().getTaskFormData(task.getId());
            List<FormProperty> formProperties = taskFormData.getFormProperties();
            System.out.println(">>>[" + formProperties.size() + "]" + formProperties.toString());
        }
        
        List<ProcessDefinition> processDefinitions = this.repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition processDefinition : processDefinitions) {
            StartFormData startFormData = this.processEngine.getFormService().getStartFormData(processDefinition.getId());
            List<FormProperty> formProperties = startFormData.getFormProperties();
            System.out.println(">>>[" + formProperties.size() + "]" + formProperties.toString());
        }
    }
    
    @Test
    public void setVariables(){
        List<ProcessInstance> processInstances = this.runtimeService.createProcessInstanceQuery().list();
        for(ProcessInstance processInstance : processInstances){
            Object variable = this.runtimeService.getVariable(processInstance.getId(), "var-1");
            System.out.println(variable);
            this.runtimeService.setVariable(processInstance.getId(), "var-1", "hey hey");
            variable = this.runtimeService.getVariable(processInstance.getId(), "var-1");
            System.out.println(variable);
        }
    }
    
    @Test
    public void listDeletedProcess(){
        List<HistoricProcessInstance> historicProcessInstances = this.historyService.createHistoricProcessInstanceQuery().list();
        for (HistoricProcessInstance historicProcessInstance : historicProcessInstances) {
            System.out.println(historicProcessInstance.getId());
            System.out.println(historicProcessInstance.getProcessDefinitionId());
            System.out.println(historicProcessInstance.getDeleteReason());
        }
    }
    
    @Test
    public void test(){
        System.out.println("---");
        System.out.println(this.getProcessDefinitionKey());
        System.out.println("---");
    }
	
}
