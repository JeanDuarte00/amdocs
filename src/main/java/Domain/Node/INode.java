package Domain.Node;

import Domain.Node.Implementation.DirectionalNode;
import Domain.NodeConnection.Implementation.WeightNodeConnection;

import java.util.List;

public interface INode {
    String getNodeName();
    void addConnection(DirectionalNode node, Integer weight);
    List<WeightNodeConnection> getConnections();
}
