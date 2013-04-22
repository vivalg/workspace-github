package com.nic.activiti.springintegrated.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

public class SimpleListener implements ExecutionListener{

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        execution.setVariable("he", "kermit");
    }

}
