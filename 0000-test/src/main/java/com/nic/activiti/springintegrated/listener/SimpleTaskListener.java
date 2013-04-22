package com.nic.activiti.springintegrated.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class SimpleTaskListener implements TaskListener{

    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setAssignee("jack");
        delegateTask.setOwner("kermit");
        delegateTask.addCandidateUser("kermit");
        System.out.println("delegate task ID: " + delegateTask.getId() + ", " + delegateTask.getName());
    }

}
