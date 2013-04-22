package com.nic.activiti.springintegrated;

import java.util.List;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public interface IProcess {

	/**
	 * 创建一个新的流程实例
	 * @return
	 */
	public ProcessInstance startNewProcess(String userId);

	/**
	 * 获取当前进行中的流程实例
	 * @return
	 */
	public List<ProcessInstance> getRuntimeProcessInstances();

	/**
	 * 获取当前进行中的任务
	 * @return
	 */
	public List<Task> getRuntimeTasks();

	/**
	 * 获取历史流程
	 * @return
	 */
	public List<HistoricProcessInstance> getHistoricProcessInstances();

	/**
	 * 获取历史任务
	 * @return
	 */
	public List<HistoricTaskInstance> getHistoricTasks();

}
