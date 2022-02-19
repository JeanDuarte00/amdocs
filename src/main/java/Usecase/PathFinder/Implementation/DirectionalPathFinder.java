package Usecase.PathFinder.Implementation;

import Domain.Graph.IGraph;
import Domain.Node.Implementation.DirectionalNode;
import Domain.NodeConnection.Implementation.WeightNodeConnection;
import Usecase.PathFinder.IPathFinder;
import java.util.*;
import java.util.stream.Collectors;

public class DirectionalPathFinder implements IPathFinder {

    private IGraph graph;
    private DirectionalNode originNode;
    private DirectionalNode targetNode;

    @Override
    public DirectionalPathFinder setGraph(IGraph graph){
        this.graph = graph;
        return this;
    }

    @Override
    public int findDistanceByList (String nodesPath){
        List<String> nodes = getNodesList(nodesPath);
        int distance = 0;
        DirectionalNode node;
        boolean found;
        for (int i = 0; i < nodes.size() ; i++) {
            found = false;
            if(i == nodes.size()-1)
                return distance;

            node = (DirectionalNode)this.graph.getNode(nodes.get(i));
            for (WeightNodeConnection connection : node.getConnections()) {
                if (connection.getNode().getNodeName().equalsIgnoreCase(nodes.get(i+1))){
                    found = true;
                    distance += connection.getWeight();
                }
            }
            if(!found) return -1;
        }
        return distance;
    }

    @Override
    public int findTrips(String origin, String target, int maximumStops) {
        this.fromNode(origin);
        this.toNode(target);

        boolean skip = origin.equalsIgnoreCase(target);

        int tripsCounter = 0;
        String path = "";
        DirectionalNode node;

        Set<String> finalSet = new HashSet<>();
        Stack<DirectionalNode> stack = new Stack<>();

        stack.push(originNode);

        while(!stack.isEmpty()){
            node = stack.peek();
            stack.pop();
            if(path.length() != 0)
                path += "-";
            path += node.getNodeName();

            if(!skip && isTarget(node)){
                String[] paths = path.split("-");
                boolean reachable = findDistanceByList(path) >= 0;
                if(finalSet.add(path) && paths.length <= (maximumStops+1) && reachable)
                    tripsCounter += 1;
                path = originNode.getNodeName();
            } else {
                List<WeightNodeConnection> connections = node.getConnections().stream()
                        .sorted(Comparator.comparing(WeightNodeConnection::getWeight))
                        .collect(Collectors.toList());

                for (WeightNodeConnection connection : connections) {
                    DirectionalNode nextNode = connection.getNode();
                    stack.push(nextNode);
                }
            }
        }

        return tripsCounter;
    }

    @Override
    public int getShortestPathLength(String origin, String target){
        this.fromNode(origin);
        this.toNode(target);

        boolean skip = origin.equalsIgnoreCase(target);
        int shortestLength = Integer.MAX_VALUE;
        String path = "";
        DirectionalNode node;

        Set<String> finalSet = new HashSet<>();
        Stack<DirectionalNode> stack = new Stack<>();
        stack.push(originNode);

        while(!stack.isEmpty()){
            node = stack.peek();
            stack.pop();
            if(path.length() != 0)
                path += "-";
            path += node.getNodeName();

            if(!skip && isTarget(node)){
                finalSet.add(path);
                path = originNode.getNodeName();
            } else {
                List<WeightNodeConnection> connections = node.getConnections().stream()
                        .sorted(Comparator.comparing(WeightNodeConnection::getWeight))
                        .collect(Collectors.toList());

                for (WeightNodeConnection connection : connections) {
                    DirectionalNode nextNode = connection.getNode();
                    stack.push(nextNode);
                }
            }
        }

        for (String dataPath : finalSet) {
            int distance = findDistanceByList(dataPath);
            if (distance >= 0){ //reachable
                if (distance < shortestLength){
                    shortestLength = distance;
                }
            }
        }
        return shortestLength;
    }

    private List<String> getNodesList(String nodesPath){
        String[] nodes = nodesPath.split("-");
        List<String> listDistance = Arrays.asList(nodes);
        return listDistance;
    }

    private DirectionalPathFinder fromNode(String nodeName){

        originNode = (DirectionalNode)this.graph.getNode(nodeName);
        return this;
    }

    private DirectionalPathFinder toNode(String nodeName){

        targetNode = (DirectionalNode)this.graph.getNode(nodeName);
        return this;
    }

    private boolean isTarget(DirectionalNode actualNode){
        return actualNode.getNodeName().equalsIgnoreCase(targetNode.getNodeName());
    }
}










