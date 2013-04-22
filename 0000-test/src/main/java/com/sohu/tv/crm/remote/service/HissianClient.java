package com.sohu.tv.crm.remote.service;

import com.caucho.hessian.client.HessianProxyFactory;

public class HissianClient {

    public static void main(String[] args) throws Exception {
        HessianProxyFactory proxyFactory = new HessianProxyFactory();
        CRMRemoteService CRMRemoteService = (CRMRemoteService) proxyFactory.create(CRMRemoteService.class, "http://localhost/interface/remote");
        String result = CRMRemoteService.notifyCRMAfterSchedualPackageUpdated(1);
        System.out.println(result);
    }
}
