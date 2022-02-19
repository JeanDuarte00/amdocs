package Domain.Graph;

import Domain.Node.INode;

import java.util.List;

public interface IGraph {
    INode getNode(String name);
    void addNode(String nodeName);
    void populateGraph(List<String> list);
}
