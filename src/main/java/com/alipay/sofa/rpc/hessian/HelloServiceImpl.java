package com.alipay.sofa.rpc.hessian;

public class HelloServiceImpl implements HelloService {
    public String sayHello(String name) {
        return "hello " + name;
    }

    public User echoUser(User user) {
        return user;
    }
}
