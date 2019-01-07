package Algo;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import Geom.Point3D;

//https://www.baeldung.com/java-dijkstra

public class Node {
     
    private String name;    
    private List<Node> shortestPath = new LinkedList<>();
    private double distance = Double.MAX_VALUE;
     
    private Map<Node, Integer> adjacentNodes = new HashMap<>();
 
    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }
  
    public Node(String name) {
        this.name = name;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Node> getShortestPath() {
		return shortestPath;
	}

	public void setShortestPath(List<Node> shortestPath) {
		this.shortestPath = shortestPath;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Map<Node, Integer> getAdjacentNodes() {
		return adjacentNodes;
	}

	public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
		this.adjacentNodes = adjacentNodes;
	}
}