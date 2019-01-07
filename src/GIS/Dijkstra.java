package GIS;
import java.util.ArrayList;

import Coords.MyCoords;
import Geom.Pixel;
import Geom.Point3D;
import graph.Graph;
import graph.Graph_Algo;
import graph.Node;
/**
 * This main class demonstrate a use of Dijkstra algorithm for finding the shortest path
 * between a source vertex on a graph to all other vertices.
 * The main class is based on the image of p_graph_ex4.png
 * Note: this version includes a bug fix performed on the 6.1.2019 (v0.11) 
 * @author ben-moshe
 *
 */
public class Dijkstra {
	
	public Pixel dijkstraAlgo(ArrayList<Box> boxes, Pixel position, Pixel destination ) {
		
		ArrayList<Pixel> pp = new ArrayList<>();
		
		pp.add(position); 
		for(int i=0;i<boxes.size();i++) {
			pp.add(boxes.get(i).getPix1());
			pp.add(new Pixel(boxes.get(i).getPix2().getX(),boxes.get(i).getPix1().getY()));
			pp.add(boxes.get(i).getPix2());	
			pp.add(new Pixel(boxes.get(i).getPix1().getX(),boxes.get(i).getPix2().getY()));	
		}
		//checking if point is inside the rectangle
		pp.add(destination); 
		
		
		Graph G = new Graph(); 
		String source = "me";
		String target = "food";
		G.add(new Node(source)); // Node "me" (0)
		
		for(int i=1;i<pp.size()-1;i++) {
			Node d = new Node(""+i);
			G.add(d);
		}
		G.add(new Node(target)); // Node "food" last index
		
		Map m = new Map();
		for (int i = 0; i < pp.size()-1; i++) {
			System.out.println("inside outer loop " +i);
	
			for (int j = i+1; j < pp.size(); j++) {
				System.out.println("inside inner loop " +j);
	
				if(!m.closeBoxVertexs(boxes, pp.get(i), pp.get(j))) {
					System.out.println("im in");
	
					if(i==0) {
							G.addEdge("me", ""+j, m.PixeldistanceInMeters(pp.get(i), pp.get(j)));
						}
						G.addEdge(""+i, ""+j,m.PixeldistanceInMeters(pp.get(i), pp.get(j)));
					}
				}
		}
		System.out.println(G);
		// This is the main call for computing all the shortest path from node 0 ("a")
		Graph_Algo.dijkstra(G, source);
		
		Node b = G.getNodeByName(target);
		System.out.println("***** Graph Demo for OOP_Ex4 *****");
		System.out.println(b);
		System.out.println("Dist: "+b.getDist());
		ArrayList<String> shortestPath = b.getPath();
		for(int i=0;i<shortestPath.size();i++) {
			System.out.print(","+shortestPath.get(i));
		}
		String s = shortestPath.get(1);
		Pixel ans = pp.get(Integer.parseInt(s));	
		return ans;		
	}
	
}
