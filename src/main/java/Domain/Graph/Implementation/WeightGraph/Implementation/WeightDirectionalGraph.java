package Domain.Graph.Implementation.WeightGraph.Implementation;

import Domain.Graph.Implementation.Graph;
import Domain.Graph.Implementation.WeightGraph.IWeightGraph;
import Domain.Node.INode;
import Domain.Node.Implementation.DirectionalNode;

import java.util.List;

public class WeightDirectionalGraph extends Graph implements IWeightGraph {

    public WeightDirectionalGraph(List<String> graphPopulation){
        this.populateGraph(graphPopulation);
    }

    @Override
    public void addConnection(String originNodeName, String targetNodeName, Integer weight){
        DirectionalNode origin = nodes.get(originNodeName);
        DirectionalNode target = nodes.get(targetNodeName);
        origin.addConnection(target, weight);
    }

    @Override
    public INode getNode(String nodeName) {
        return nodes.get(nodeName);
    }

    @Override
    public void populateGraph(List<String> list){
        list.forEach(data -> {

            String origin = String.valueOf(data.charAt(0));
            String target = String.valueOf(data.charAt(1));
            Integer weight = Integer.valueOf(data.charAt(2) - '0');

            addNode(origin);
            addNode(target);

            addConnection(origin, target, weight);
        });
    }
}
