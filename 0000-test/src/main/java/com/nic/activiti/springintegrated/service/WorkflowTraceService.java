package com.nic.activiti.springintegrated.service;

import java.io.InputStream;
import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 工作流跟踪相关Service
 * 
 * @author willli
 * @date 2012-8-12
 */
@Service
public class WorkflowTraceService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected RuntimeService runtimeService;

    @Autowired
    protected TaskService taskService;

    @Autowired
    protected RepositoryService repositoryService;

    @Autowired
    protected IdentityService identityService;

    public InputStream getWorkflowDiagram(String processInstanceId) {
        ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        String processDefinitionId = processInstance.getProcessDefinitionId();
        List<String> highLightedActivities = this.runtimeService.getActiveActivityIds(processInstanceId);
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(processDefinitionId);
        
        InputStream in = ProcessDiagramGenerator.generateDiagram(processDefinitionEntity, "png", highLightedActivities);
        return in;
    }
    
    public List<ProcessInstance> getAllActiveProcesses(){
        return this.runtimeService.createProcessInstanceQuery().list();
    }

}
