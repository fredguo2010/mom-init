package com.iad.mom.opcua.client.momopcuaclient.controller;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseDirection;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/opcua")
public class OpcUaController {


    @Autowired
    private OpcUaClient opcUaClient;

    @PostMapping("/read")
    public DataValue readNode(@RequestBody Map<String, Object> objectMap) throws Exception {
        NodeId nodeToRead = this.parseNodeId(objectMap);

        //读取节点数据
        DataValue value = opcUaClient.readValue(0.0, TimestampsToReturn.Neither, nodeToRead).get();
        //标识符
        String id = String.valueOf(nodeToRead.getIdentifier());
        System.out.println(id + ": " + String.valueOf(value.getValue().getValue()));
        return value;
    }

    @PostMapping("/write")
    public ResponseEntity<String> writeNode(@RequestBody Map<String, Object> payload) throws Exception {
        NodeId nodeToWrite = this.parseNodeId(payload);
        //创建数据对象,此处的数据对象一定要定义类型，不然会出现类型错误，导致无法写入
        Variant  variant = getVariantFromPayload(payload);
        DataValue nowValue = new DataValue(variant, null, null);
        //写入节点数据
        StatusCode statusCode = opcUaClient.writeValue(nodeToWrite, nowValue).join();

        if (statusCode.isGood()) {
            return ResponseEntity.ok("Node written");
        } else {
            return ResponseEntity.status(500).body("Failed to write node");
        }
    }

    @PostMapping("/browse")
    public List<? extends UaNode> browseNode(@RequestBody Map<String, Object> payload) throws Exception {
        NodeId nodeToBrowse = this.parseNodeId(payload);

        // NodeId root = Identifiers.RootFolder;
        // NodeId simulationRoot = new NodeId(3,"85/0:Simulation");
        List<? extends UaNode> nodes = opcUaClient.getAddressSpace().browseNodes(nodeToBrowse);

        for (UaNode nd : nodes) {
            //排除系统行性节点，这些系统性节点名称一般都是以"_"开头
            if (Objects.requireNonNull(nd.getBrowseName().getName()).contains("_")) {
                continue;
            }
            System.out.println("Node= " + nd.getBrowseName().getName()+"NodeId= "+nd.getNodeId());
        }
        return nodes;
    }

    private Variant getVariantFromPayload(Map<String, Object> payload) {
        Object obj = payload.get("nodeValue");

        if (obj instanceof Integer) {
            return new Variant((Integer) obj);
        } else if (obj instanceof Double) {
            return new Variant((Double) obj);
        } else if (obj instanceof Boolean) {
            return new Variant((Boolean) obj);
        } else {
            return new Variant(obj.toString());
        }
    }

    private NodeId parseNodeId(Map<String, Object> objectMap){
        String nodeId = (String)objectMap.get("nodeId");
        // Parse nodeId string
        String[] parts = nodeId.split(";");
        int namespaceIndex = Integer.parseInt(parts[0].split("=")[1]);
        String identifierType = parts[1].split("=")[0];
        String identifierVal = parts[1].split("=")[1];

        NodeId nodeToReturn = null;

        if(identifierType.equals("i")) {
            // ns=3;i=1003
            int identifier = Integer.parseInt(identifierVal);
            nodeToReturn = new NodeId(namespaceIndex, identifier);
        } else if (identifierType.equals("s")) {
            // ns=3;s=iad_mom
            String identifier = identifierVal;
            nodeToReturn = new NodeId(namespaceIndex, identifier);
        }else if (identifierType.equals("g")) {
            // ns=3;g=1e34bb86-4059-499d-a286-cf489aa35d5f
            String identifier = identifierVal;
            nodeToReturn = new NodeId(namespaceIndex, UUID.fromString(identifier));
        }
        return nodeToReturn;
    }

}
