package Domain.Graph.Implementation.WeightGraph;

import Domain.Graph.IGraph;

public interface IWeightGraph extends IGraph {
    void addConnection(String originNodeName, String targetNodeName, Integer weight);
}
