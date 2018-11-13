package com.alipay.sofa.rpc.hessian;

public interface HelloService {
    String sayHello(String name);

    User echoUser(User user);
}
