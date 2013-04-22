package com.nic.activiti.springintegrated.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

/**
 * 流程定义服务类
 * 
 * @author willli
 * @date 2012-8-10
 */
@Service
public class WorkflowProcessDefinitionService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected RuntimeService runtimeService;

    @Autowired
    protected RepositoryService repositoryService;

    @Autowired
    protected HistoryService historyService;

    /**
     * 根据流程实例ID查询流程定义对象{@link ProcessDefinition}
     * 
     * @param processInstanceId 流程实例ID
     * @return 流程定义对象{@link ProcessDefinition}
     */
    public ProcessDefinition findProcessDefinitionByPid(String processInstanceId) {
        HistoricProcessInstance historicProcessInstance = this.historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        String processDefinitionId = historicProcessInstance.getProcessDefinitionId();
        ProcessDefinition processDefinition = findProcessDefinition(processDefinitionId);
        return processDefinition;
    }

    /**
     * 根据流程定义ID查询流程定义对象{@link ProcessDefinition}
     * 
     * @param processDefinitionId 流程定义对象ID
     * @return 流程定义对象{@link ProcessDefinition}
     */
    public ProcessDefinition findProcessDefinition(String processDefinitionId) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId).singleResult();
        return processDefinition;
    }

    /**
     * 部署classpath下面的流程定义
     * 
     * @param processKey 流程定义KEY
     * @throws Exception
     */
    public void deployFromClasspath(String... processKeys) throws Exception {
        for (String processKey : processKeys) {
            logger.debug("部署流程定义：{}", processKey);
            deploySingleProcess(processKey);
        }
    }

    /**
     * 部署单个流程定义
     * 
     * @param processKey 模块名称
     * @throws IOException 找不到xml定义文件时
     */
    private void deploySingleProcess(String processKey) throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        String classpathResourceUrl = "classpath:/process/" + processKey + ".bpmn20.xml";
        logger.debug("流程定义文件: {}", classpathResourceUrl);
        Resource resource = resourceLoader.getResource(classpathResourceUrl);
        InputStream inputStream = resource.getInputStream();
        if (inputStream == null) {
            logger.warn("无法读取流程定义文件: {}", classpathResourceUrl);
        } else {
            logger.debug("读取文件: {} 中的流程定义", classpathResourceUrl);
            this.repositoryService.createDeployment().addInputStream(resource.getFilename(), inputStream).deploy();
        }
    }
    
    
    public void deploySingleZipProcess(String processKey) throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        String classpathResourceUrl = "classpath:/process/" + processKey + ".zip";
        logger.debug("流程定义文件: {}", classpathResourceUrl);
        Resource resource = resourceLoader.getResource(classpathResourceUrl);
        InputStream inputStream = resource.getInputStream();
        if (inputStream == null) {
            logger.warn("无法读取流程定义文件: {}", classpathResourceUrl);
        } else {
            logger.debug("读取文件: {} 中的流程定义", classpathResourceUrl);
            ZipInputStream zis = new ZipInputStream(inputStream);
            repositoryService.createDeployment().addZipInputStream(zis).deploy();
        }
    }

    /**
     * 重新部署流程定义
     * 
     * @param processKey 流程定义KEY
     * @throws Exception
     * @see #deployFromClasspath
     */
    public void redeploy(String... processKey) throws Exception {
        this.deployFromClasspath(processKey);
    }

    /**
     * 清除流程定义(级联删除与其相关的流程实例、历史流程实例数据，慎用)
     * 
     * @param processDefinitionKey 流程定义Key
     */
    public void cleanProcessDefinition(String processDefinitionKey) {
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionKey).list();
        for (ProcessDefinition processDefinition : processDefinitions) {
            logger.info("删除流程定义: {}" + processDefinition.getId());
            repositoryService.deleteDeployment(processDefinition.getDeploymentId(), true);
        }
    }

}
