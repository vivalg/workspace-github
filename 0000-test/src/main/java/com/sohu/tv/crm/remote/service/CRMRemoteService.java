package com.sohu.tv.crm.remote.service;

/**
 * CRM系统远程服务接口
 *
 * @author willli
 */
public interface CRMRemoteService {

    /**
     * 排期包修改后，通知CRM系统回退对应的执行单、合同审核流程
     *
     * @param executionUnitId 执行单ID
     * @return
     */
    String notifyCRMAfterSchedualPackageUpdated(Integer executionUnitId);
}
