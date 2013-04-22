package com.nic.activiti.springintegrated.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户任务服务类
 * 
 * @author willli
 * @date 2012-8-10
 */
@Service
public class WorkflowTaskService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected TaskService taskService;

    @Autowired
    protected RuntimeService runtimeService;

    @Autowired
    protected HistoryService historyService;

    /**
     * 根据任务承担者查找任务
     * 
     * @param assignee
     * @return
     */
    public List<Task> getTasksByAssignee(String assignee) {
        return this.taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    /**
     * 根据任务持有者查找任务
     * 
     * @param owner
     * @return
     */
    public List<Task> getTasksByOwner(String owner) {
        return this.taskService.createTaskQuery().taskOwner(owner).list();
    }

    /**
     * 分配任务给用户
     * 
     * @param taskId
     * @param assignee
     */
    public void claimUserTask(String taskId, String assignee) {
        this.taskService.claim(taskId, assignee);
    }

    /**
     * 设置任务持有者
     * 
     * @param taskId
     * @param owner
     */
    public void ownUserTask(String taskId, String owner) {
        this.taskService.setOwner(taskId, owner);
    }

    /**
     * 完成任务
     * 
     * @param taskId
     * @param variables 变量
     */
    public void completeUserTask(String taskId, Map<String, Object> variables) {
        this.taskService.complete(taskId, variables);
    }

}
