package com.iad.mom.opcua.client.momopcuaclient;

import com.iad.mom.opcua.client.momopcuaclient.util.MoMOpcUAUtil;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.SmartLifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MomOpcuaClientApplication implements SmartLifecycle {

    private static OpcUaClient opcUaClient;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MomOpcuaClientApplication.class, args);


//        List<NodeId> nodeIdList = new ArrayList<>();
//        nodeIdList.add(new NodeId(3, 1001));
//        nodeIdList.add(new NodeId(3, 1002));
//        nodeIdList.add(new NodeId(3, 1003));
//        nodeIdList.add(new NodeId(3, 1004));
//        nodeIdList.add(new NodeId(3, 1005));
//        nodeIdList.add(new NodeId(3, 1006));
//
//
//
//        //readNodeList(client, nodeIdList);
//        //subscribeSingle(client,nodeIdList.get(1));
//        MoMOpcUAUtil.subscribeNodeList(opcUaClient, nodeIdList);

    }


    @PostConstruct
    public void connect() throws Exception {
        MoMOpcUAUtil util = new MoMOpcUAUtil();
        opcUaClient = util.createClient();
        opcUaClient.connect().get();
    }

    @PreDestroy
    public void disconnect() throws Exception {
        opcUaClient.disconnect().get();
    }


    @Override
    public void start() {
        try {
            opcUaClient.connect().get();
        } catch (Exception e) {
            // Handle error
        }
    }

    @Override
    public void stop() {
        try {
            opcUaClient.disconnect().get();
        } catch (Exception e) {
            // Handle error
        }
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
