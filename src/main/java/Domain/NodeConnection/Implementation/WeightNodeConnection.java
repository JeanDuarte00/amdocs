package Domain.NodeConnection.Implementation;

import Domain.Node.Implementation.DirectionalNode;
import Domain.NodeConnection.INodeConnection;

public class WeightNodeConnection implements INodeConnection {

    private DirectionalNode node;
    private Integer weight;

    public WeightNodeConnection(DirectionalNode node, Integer weight) {
        this.node = node;
        this.weight = weight;
    }

    public DirectionalNode getNode() {
        return node;
    }

    public void setNode(DirectionalNode node) {
        this.node = node;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
