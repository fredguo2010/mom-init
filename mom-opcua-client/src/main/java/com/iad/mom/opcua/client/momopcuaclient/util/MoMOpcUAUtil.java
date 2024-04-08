package com.iad.mom.opcua.client.momopcuaclient.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.client.subscriptions.ManagedDataItem;
import org.eclipse.milo.opcua.sdk.client.subscriptions.ManagedSubscription;
import org.eclipse.milo.opcua.stack.client.security.DefaultClientCertificateValidator;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.security.DefaultTrustListManager;
import org.eclipse.milo.opcua.stack.core.types.builtin.*;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class MoMOpcUAUtil {

    static {
        // Required for SecurityPolicy.Aes256_Sha256_RsaPss
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final Logger logger = LoggerFactory.getLogger(MoMOpcUAUtil.class);

    private final CompletableFuture<OpcUaClient> future = new CompletableFuture<>();

    private DefaultTrustListManager trustListManager;

    /**
     * 创建OPC UA客户端
     *
     * @return
     * @throws Exception
     */
    public OpcUaClient createClient() throws Exception {
        Path securityTempDir = Paths.get(System.getProperty("java.io.tmpdir"), "client", "security");
        Files.createDirectories(securityTempDir);
        if (!Files.exists(securityTempDir)) {
            throw new Exception("unable to create security dir: " + securityTempDir);
        }

        File pkiDir = securityTempDir.resolve("pki").toFile();

        LoggerFactory.getLogger(getClass())
                .info("security dir: {}", securityTempDir.toAbsolutePath());
        LoggerFactory.getLogger(getClass())
                .info("security pki dir: {}", pkiDir.getAbsolutePath());

        KeyStoreLoader loader = new KeyStoreLoader().load(securityTempDir);

        trustListManager = new DefaultTrustListManager(pkiDir);

        DefaultClientCertificateValidator certificateValidator =
                new DefaultClientCertificateValidator(trustListManager);

        String opcURL = MoMOpcUaClient.getEndpointUrl();

        return OpcUaClient.create(
                opcURL,
                endpoints ->
                        endpoints.stream()
                                .filter(MoMOpcUaClient.endpointFilter())
                                .findFirst(),
                configBuilder ->
                        configBuilder
                                .setApplicationName(LocalizedText.english("eclipse milo opc-ua client"))
                                .setApplicationUri("urn:eclipse:milo:examples:client")
                                .setKeyPair(loader.getClientKeyPair())
                                .setCertificate(loader.getClientCertificate())
                                .setCertificateChain(loader.getClientCertificateChain())
                                .setCertificateValidator(certificateValidator)
                                .setIdentityProvider(MoMOpcUaClient.getIdentityProvider())
                                .setRequestTimeout(uint(5000))
                                .build()
        );
    }

    /**
     * 遍历树形节点
     *
     * @param client OPC UA客户端
     * @param uaNode 节点
     * @throws Exception
     */
    public static void browseNode(OpcUaClient client, UaNode uaNode) throws Exception {
        List<? extends UaNode> nodes;
        if (uaNode == null) {
            nodes = client.getAddressSpace().browseNodes(Identifiers.ObjectsFolder);
        } else {
            nodes = client.getAddressSpace().browseNodes(uaNode);
        }
        for (UaNode nd : nodes) {
            //排除系统行性节点，这些系统性节点名称一般都是以"_"开头
            if (Objects.requireNonNull(nd.getBrowseName().getName()).contains("_")) {
                continue;
            }
            System.out.println("Node= " + nd.getBrowseName().getName());
            browseNode(client, nd);
        }
    }

    /**
     * 读取节点数据
     *
     * @param client OPC UA客户端
     * @throws Exception
     */
    public static void readNode(OpcUaClient client, NodeId nodeId) throws Exception {
        //读取节点数据
        DataValue value = client.readValue(0.0, TimestampsToReturn.Neither, nodeId).get();
        //标识符
        String identifier = String.valueOf(nodeId.getIdentifier());
        System.out.println(identifier + ": " + String.valueOf(value.getValue().getValue()));
    }

    /**
     * 写入节点数据
     *
     * @param client
     * @throws Exception
     */
    public static void writeNodeValue(OpcUaClient client) throws Exception {
        //节点
        NodeId nodeId = new NodeId(2, "TD-01.SB-01.AG-01");
        short i = 3;
        //创建数据对象,此处的数据对象一定要定义类型，不然会出现类型错误，导致无法写入
        DataValue nowValue = new DataValue(new Variant(i), null, null);
        //写入节点数据
        StatusCode statusCode = client.writeValue(nodeId, nowValue).join();
        System.out.println("结果：" + statusCode.isGood());
    }

    /**
     * 订阅(单个)
     *
     * @param client
     * @throws Exception
     */
    public static void subscribeSingleNode(OpcUaClient client, NodeId nodeId) throws Exception {
        //创建发布间隔1000ms的订阅对象
        client
                .getSubscriptionManager()
                .createSubscription(1000.0)
                .thenAccept(t -> {
                    //节点
                    ReadValueId readValueId = new ReadValueId(nodeId, AttributeId.Value.uid(), null, null);
                    //创建监控的参数
                    AtomicLong clientHandles = new AtomicLong(1L);
                    UInteger clientHandle = uint(clientHandles.getAndIncrement());
                    MonitoringParameters parameters = new MonitoringParameters(clientHandle, 1000.0, null, UInteger.valueOf(10), true);
                    //创建监控项请求
                    //该请求最后用于创建订阅。
                    MonitoredItemCreateRequest request = new MonitoredItemCreateRequest(readValueId, MonitoringMode.Reporting, parameters);
                    List<MonitoredItemCreateRequest> requests = new ArrayList<>();
                    requests.add(request);
                    //创建监控项，并且注册变量值改变时候的回调函数。
                    t.createMonitoredItems(
                            TimestampsToReturn.Both,
                            requests,
                            (item, id) -> item.setValueConsumer((it, val) -> {
                                System.out.println("nodeid :" + it.getReadValueId().getNodeId());
                                System.out.println("value :" + val.getValue().getValue());
                            })
                    );
                }).get();

        //持续订阅
        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * 批量订阅
     *
     * @param client
     * @throws Exception
     */
    public static void subscribeNodeList(OpcUaClient client, List<NodeId> nodeIdList) throws Exception {
        final CountDownLatch eventLatch = new CountDownLatch(1);

        //处理订阅业务
        handlerNode(client, nodeIdList);

        //持续监听
        eventLatch.await();
    }

    public static void handlerNode(OpcUaClient client, List<NodeId> nodeIdList) {
        try {
            //创建订阅
            ManagedSubscription subscription = ManagedSubscription.create(client);

            //监听
            List<ManagedDataItem> dataItemList = subscription.createDataItems(nodeIdList);
            for (ManagedDataItem managedDataItem : dataItemList) {
                managedDataItem.addDataValueListener((t) -> {
                    System.out.println(managedDataItem.getNodeId().getIdentifier().toString() + ":" + t.getValue().getValue().toString());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量读取
     * @param client
     * @param nodeIdList
     * @throws Exception
     */
    public static void readNodeList(OpcUaClient client, List<NodeId> nodeIdList) throws Exception {
        for (NodeId nodeId : nodeIdList) {
            readNode(client, nodeId);
        }
    }

    public static void main(String[] args) throws Exception {
        MoMOpcUAUtil uaUtil = new MoMOpcUAUtil();
        OpcUaClient client = uaUtil.createClient();
        client.connect().get();

//        List<NodeId> nodeIdList = new ArrayList<>();
//        nodeIdList.add(new NodeId(3, 1001));
//        nodeIdList.add(new NodeId(3, 1002));
//        nodeIdList.add(new NodeId(3, 1003));
//        nodeIdList.add(new NodeId(3, 1004));
//        nodeIdList.add(new NodeId(3, 1005));
//        nodeIdList.add(new NodeId(3, 1006));



        //readNodeList(client, nodeIdList);
        //subscribeSingle(client,nodeIdList.get(1));
        //subscribeNodeList(client, nodeIdList);
        browseNode(client,null);
        client.disconnect().get();
    }

}


