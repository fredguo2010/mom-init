package com.iad.mom.opcua.client.momopcuaclient.component;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OpcUaComponent {
    @Autowired
    private OpcUaClient opcUaClient;

    public void readNode() throws Exception {
        opcUaClient.connect().get();
        // Read node
    }
}
