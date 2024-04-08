package com.iad.mom.opcua.client.momopcuaclient.config;

import com.iad.mom.opcua.client.momopcuaclient.util.MoMOpcUAUtil;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpcUaConfig {

    @Bean
    public OpcUaClient opcUaClient() throws Exception {
        MoMOpcUAUtil uaUtil = new MoMOpcUAUtil();
        OpcUaClient client = uaUtil.createClient();
        client.connect().get();
        return client;
    }
}
