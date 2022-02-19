package Entrypoint;

import Domain.Graph.Implementation.WeightGraph.IWeightGraph;
import Domain.Graph.Implementation.WeightGraph.Implementation.WeightDirectionalGraph;
import Infraestructure.DataImporter.Implementation.FileDataImporter;
import Usecase.PathFinder.IPathFinder;
import Usecase.PathFinder.Implementation.DirectionalPathFinder;

import java.util.List;

public class Application {

    public static void Run(String[] args){
        final String distance = "DISTANCE";
        final String trips = "TRIPS";
        final String shortest = "SHORTEST";

        String origin;
        String target;

        FileDataImporter importer = new FileDataImporter();
        List<String> list = importer.Read();
        IWeightGraph graph = new WeightDirectionalGraph(list);
        IPathFinder finder = new DirectionalPathFinder().setGraph(graph);

        if(args == null || args.length != 2) {
            System.out.println("Invalid arguments. It requires two arguments.");
            System.out.println("You can use ["+distance+"(param:A-B-C), "+trips+"(param:AC3), "+shortest+"(param:AC)]");
            System.out.println("For example: DISTANCE A-B-C");
            return;
        }

        String whatToRun = args[0].toUpperCase();
        String params = args[1].toUpperCase();

        System.out.println("Running Challenge Application");

        switch (whatToRun){

            case distance:
                String nodesPath = params; // A-B-C
                int dist = finder.findDistanceByList(nodesPath);
                System.out.println("DISTANCE:" +(dist<0? "NO SUCH ROUTE": dist));
                break;

            case trips:
                origin = String.valueOf(params.charAt(0)); // "A"
                target = String.valueOf(params.charAt(1)); // "C"
                Integer stops = Integer.valueOf(params.charAt(2) - '0'); // 3

                int trip = finder.findTrips(origin, target, stops );
                System.out.println("NUM OF TRIPS:" +trip);
                break;

            case shortest:
                origin = String.valueOf(params.charAt(0)); // "A"
                target = String.valueOf(params.charAt(1)); // "C"

                int pathLength = finder.getShortestPathLength(origin, target);
                System.out.println("PATH LENGTH:" +pathLength);
                break;

            default:
                System.out.println("No option found!!\nYou can use (" + distance + "," + trips + "," + shortest + ") as first argument.");
                break;
        }
    }
}
