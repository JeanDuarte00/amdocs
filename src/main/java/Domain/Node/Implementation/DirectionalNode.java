package Domain.Node.Implementation;

import Domain.Node.INode;
import Domain.NodeConnection.Implementation.WeightNodeConnection;

import java.util.LinkedList;
import java.util.List;

public class DirectionalNode implements INode {

    private String nodeName;
    private List<WeightNodeConnection> connectionList;

    public DirectionalNode(String nodeName) {
        this.nodeName = nodeName;
        this.connectionList = new LinkedList<>();
    }

    public void addConnection(DirectionalNode node, Integer weight){
        this.connectionList.add(new WeightNodeConnection(node, weight));
    }

    public List<WeightNodeConnection> getConnections(){
        return this.connectionList;
    }

    public String getNodeName() {
        return nodeName;
    }
}
