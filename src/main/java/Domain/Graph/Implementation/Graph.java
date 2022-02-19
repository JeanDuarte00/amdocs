package Domain.Graph.Implementation;

import Domain.Graph.IGraph;
import Domain.Node.Implementation.DirectionalNode;

import java.util.HashMap;
import java.util.Map;

public abstract class Graph implements IGraph {

    protected Map<String, DirectionalNode> nodes;

    public Graph() {
        this.nodes = new HashMap<>();
    }

    @Override
    public void addNode(String nodeName){
        DirectionalNode node = new DirectionalNode(nodeName);
        nodes.putIfAbsent(nodeName, node);
    }

}
