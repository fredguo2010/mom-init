package com.iad.mom.opcua.client.momopcuaclient.util;

import org.eclipse.milo.opcua.sdk.client.api.identity.AnonymousProvider;
import org.eclipse.milo.opcua.sdk.client.api.identity.IdentityProvider;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;

import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

public interface MoMOpcUaClient {


    static String getEndpointUrl() {
        return "opc.tcp://HOMESERVER:53530/OPCUA/SimulationServer";
    }

    static Predicate<EndpointDescription> endpointFilter() {
        return e -> getSecurityPolicy().getUri().equals(e.getSecurityPolicyUri());
    }

//    default SecurityPolicy getSecurityPolicy() {
//        return SecurityPolicy.Basic256Sha256;
//    }

    static SecurityPolicy getSecurityPolicy() {
        return SecurityPolicy.None;
    }

    static IdentityProvider getIdentityProvider() {
        return new AnonymousProvider();
    }

    void run(org.eclipse.milo.opcua.sdk.client.OpcUaClient client, CompletableFuture<org.eclipse.milo.opcua.sdk.client.OpcUaClient> future) throws Exception;
}
