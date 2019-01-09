package Algo;
import java.util.ArrayList;

import GIS.Box;
import GIS.Map;
import Geom.Pixel;
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
		Map m = new Map();

		ArrayList<Pixel> biggerBoxesPoints = new ArrayList<>();


		biggerBoxesPoints.add(position);
		for (int i = 0; i < boxes.size(); i++) {
			//5.6 limit of my player and dijkstra in the frame
			biggerBoxesPoints.add(new Pixel(boxes.get(i).getPix1().getX()-5.6,boxes.get(i).getPix1().getY()+5.6));
			biggerBoxesPoints.add(new Pixel(boxes.get(i).getPix2().getX()+5.6,boxes.get(i).getPix1().getY()+5.6));
			biggerBoxesPoints.add(new Pixel(boxes.get(i).getPix2().getX()+5.6,boxes.get(i).getPix2().getY()-5.6));
			biggerBoxesPoints.add(new Pixel(boxes.get(i).getPix1().getX()-5.6,boxes.get(i).getPix2().getY()-5.6));		
		}
		biggerBoxesPoints.add(destination); 
		//checking if point is inside the rectangle
		Graph G = new Graph(); 
		String source = "me";
		String target = "food";

		G.add(new Node(source)); // Node "me" (0)
		for(int i=1;i<biggerBoxesPoints.size()-1;i++) {
			Node d = new Node(""+i);
			G.add(d);
		}
		G.add(new Node(target)); // Node "food" last index
		for (int i = 0; i < biggerBoxesPoints.size()-1; i++) {
			for (int j = i+1; j < biggerBoxesPoints.size(); j++) {
				if(!m.closeBoxVertexs(boxes, biggerBoxesPoints.get(i), biggerBoxesPoints.get(j))) {
					if(i==0) {
						
						if(j==biggerBoxesPoints.size()-1) {
							G.addEdge("me", "food", m.PixeldistanceInMeters(biggerBoxesPoints.get(i), biggerBoxesPoints.get(j)));
						}
						G.addEdge("me", ""+j, m.PixeldistanceInMeters(biggerBoxesPoints.get(i), biggerBoxesPoints.get(j)));
					}
					if(j==biggerBoxesPoints.size()-1) {
						G.addEdge(""+i, "food", m.PixeldistanceInMeters(biggerBoxesPoints.get(i), biggerBoxesPoints.get(j)));
					}	
					G.addEdge(""+i, ""+j,m.PixeldistanceInMeters(biggerBoxesPoints.get(i), biggerBoxesPoints.get(j)));
				}
			}
		}
		// This is the main call for computing all the shortest path from node 0 ("a")
		Graph_Algo.dijkstra(G, source);

		Node b = G.getNodeByName(target);

		ArrayList<String> shortestPath = b.getPath();

		String s = shortestPath.get(1);
		Pixel ans = biggerBoxesPoints.get(Integer.parseInt(s));	
		return ans;		
	}

}
