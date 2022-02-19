package Usecase.PathFinder;

import Domain.Graph.IGraph;
import Usecase.PathFinder.Implementation.DirectionalPathFinder;

public interface IPathFinder {
    DirectionalPathFinder setGraph(IGraph graph);
    int findDistanceByList (String nodesPath);
    int findTrips(String origin, String target, int maximumStops);
    int getShortestPathLength(String origin, String target);
}
