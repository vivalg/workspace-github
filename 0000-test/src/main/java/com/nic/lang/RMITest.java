package com.nic.lang;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMITest {

    public static void main(String[] args) throws Throwable {
        new RMIServer().startServer();
        new RMIClient().doInvoke("jack");
    }
}

class RMIClient {
    public void doInvoke(String str) throws MalformedURLException, RemoteException, NotBoundException {
        IFoo foo = (IFoo) Naming.lookup("rmi://localhost:8888/RFoo");
        System.out.println(foo);
        String bar = foo.bar(str);
        System.out.println(bar);
    }
}

class RMIServer {
    public Registry startServer() throws RemoteException, MalformedURLException, AlreadyBoundException {
        IFoo foo = new FooImpl();
        Registry registry = LocateRegistry.createRegistry(8888);
        Naming.bind("rmi://localhost:8888/RFoo", foo);
        System.out.println(">>>>port bind sucess!");
        return registry;
    }
}

interface IFoo extends Remote {
    String bar(String str);
}

class FooImpl implements IFoo, Serializable {

    protected FooImpl() throws RemoteException {
        super();
    }

    private static final long serialVersionUID = 1L;

    @Override
    public String bar(String str) {
        System.out.println("foo:" + str);
        return "foo->" + str;
    }

}