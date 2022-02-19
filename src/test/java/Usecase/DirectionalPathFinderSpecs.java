package Usecase;

import Domain.Graph.Implementation.WeightGraph.IWeightGraph;
import Domain.Graph.Implementation.WeightGraph.Implementation.WeightDirectionalGraph;
import Usecase.PathFinder.IPathFinder;
import Usecase.PathFinder.Implementation.DirectionalPathFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class DirectionalPathFinderSpecs {

    @Test
    @DisplayName("Should return an integer representing a distance from a starting point going to each node the last one.")
    public void findDistanceByList() {

        IWeightGraph graph = new WeightDirectionalGraph(generateGraph());
        IPathFinder pathFinder = new DirectionalPathFinder().setGraph(graph);

        int distance = pathFinder.findDistanceByList("A-C-B-D");
        Assertions.assertEquals(14, distance);
    }

    @Test
    @DisplayName("Should return -1 representing a not found path distance from a starting point going to each node the last one.")
    public void cantFindDistanceByList() {

        IWeightGraph graph = new WeightDirectionalGraph(generateGraph());
        IPathFinder pathFinder = new DirectionalPathFinder().setGraph(graph);

        int distance = pathFinder.findDistanceByList("A-B-C");
        Assertions.assertEquals(-1, distance);
    }

    @Test
    @DisplayName("Should an integer representing how many ways exist from starting point to target, considering quantity of nodes between them.")
    public void getHowManyTrips() {
        int trips = 0;
        IWeightGraph graph = new WeightDirectionalGraph(generateGraph());
        IPathFinder pathFinder = new DirectionalPathFinder().setGraph(graph);

        trips = pathFinder.findTrips("A", "D", 2);
        Assertions.assertEquals(2, trips);

        trips = pathFinder.findTrips("A", "D", 1);
        Assertions.assertEquals(0, trips);
    }

    @Test
    @DisplayName("Should an integer representing the shortest path from starting point to target.")
    public void findTheShortestPathSize() {
        int shortestPathSize = 0;
        IWeightGraph graph = new WeightDirectionalGraph(generateGraph());
        IPathFinder pathFinder = new DirectionalPathFinder().setGraph(graph);

        shortestPathSize = pathFinder.getShortestPathLength("A", "D");
        Assertions.assertEquals(5, shortestPathSize);

        shortestPathSize = pathFinder.getShortestPathLength("A", "B");
        Assertions.assertEquals(3, shortestPathSize);

        shortestPathSize = pathFinder.getShortestPathLength("C", "D");
        Assertions.assertEquals(7, shortestPathSize);
    }

    private List<String> generateGraph(){
        List<String> list = new ArrayList<>();
        list.add("AB3");
        list.add("AC7");
        list.add("CB5");
        list.add("CD8");
        list.add("BD2");
        return list;
    }
}
