package com.nic.activiti.springintegrated;

import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProcessDeployer {
    
    private static final String processDefinitionPath = "process/";
    private static final String processDefinitionSuffix = ".bpmn20.xml";

	public static void main(String[] args) {
		String processDefinitionFileName = "TimerBoundaryProcess";
		new ProcessDeployer().deploy(processDefinitionPath + processDefinitionFileName + processDefinitionSuffix);
	    
//		String processDefinitionKey = "multiCandidateUserProcess";
//		new ProcessDeployer().clearDeployCascade(processDefinitionKey);
	}
	
	public void deploy(String processPath){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("activiti-spring.cfg.xml");
		RepositoryService repositoryService = (RepositoryService) applicationContext.getBean("repositoryService");
		repositoryService.createDeployment().addClasspathResource(processPath).deploy();
	}
	
	public void clearDeployCascade(String processDefinitionKey){
	    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("activiti-spring.cfg.xml");
	    RepositoryService repositoryService = (RepositoryService) applicationContext.getBean("repositoryService");
	    List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).list();
	    for (ProcessDefinition processDefinition : processDefinitions) {
            repositoryService.deleteDeployment(processDefinition.getDeploymentId(), true);
        }
	}
}
