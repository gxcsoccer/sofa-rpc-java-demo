package com.alipay.sofa.rpc;

import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.alipay.sofa.rpc.hessian.HelloService;
import com.alipay.sofa.rpc.hessian.HelloServiceImpl;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;
import com.alipay.sofa.rpc.protobuf.ProtoService;
import com.alipay.sofa.rpc.protobuf.ProtoServiceImpl;

public class HessianServiceServerMain {

    /**
     * Logger for ProtobufServiceServerMain
     **/
    private static final Logger LOGGER = LoggerFactory.getLogger(ProtobufServiceServerMain.class);

    public static void main(String[] args) {
        // 指定注册中心
        RegistryConfig registryConfig = new RegistryConfig()
                .setProtocol("zookeeper")
                .setAddress("127.0.0.1:2181");

        ServerConfig serverConfig = new ServerConfig()
                .setProtocol("bolt") // 设置一个协议，默认bolt
                .setSerialization("hessian2")
                .setPort(12200) // 设置一个端口，默认12200
                .setDaemon(false); // 非守护线程

        ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
                .setInterfaceId(HelloService.class.getName()) // 指定接口
                .setRef(new HelloServiceImpl()) // 指定实现
                .setServer(serverConfig) // 指定服务端
                .setRegistry(registryConfig);


        providerConfig.export(); // 发布服务

        LOGGER.error("started at pid {}", RpcRuntimeContext.PID);
    }
}
