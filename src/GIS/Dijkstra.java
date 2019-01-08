package GIS;
import java.util.ArrayList;
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
<<<<<<< HEAD
		Map m = new Map();

		ArrayList<Pixel> biggerBoxesPoints = new ArrayList<>();

		
		biggerBoxesPoints.add(position);
		for (int i = 0; i < boxes.size(); i++) {
			biggerBoxesPoints.add(new Pixel(boxes.get(i).getPix1().getX()-5,boxes.get(i).getPix1().getY()+5));
			biggerBoxesPoints.add(new Pixel(boxes.get(i).getPix2().getX()+5,boxes.get(i).getPix1().getY()+5));
			biggerBoxesPoints.add(new Pixel(boxes.get(i).getPix2().getX()+5,boxes.get(i).getPix2().getY()-5));
			biggerBoxesPoints.add(new Pixel(boxes.get(i).getPix1().getX()-5,boxes.get(i).getPix2().getY()-5));		
		}
		biggerBoxesPoints.add(destination); 

		//checking if point is inside the rectangle
=======

		ArrayList<Pixel> pp = new ArrayList<>();

		pp.add(position); // source is index 0
		//checking if point is inside the rectangle
		pp.add(destination); // target is index 1
		
		for(int i=0;i<boxes.size();i++) {
			pp.add(boxes.get(i).getPix1());
			pp.add(new Pixel(boxes.get(i).getPix2().getX(),boxes.get(i).getPix1().getY()));
			pp.add(boxes.get(i).getPix2());	
			pp.add(new Pixel(boxes.get(i).getPix1().getX(),boxes.get(i).getPix2().getY()));	
		}


>>>>>>> 4c24a53f90e070d02a50cfd01ad3327e0eacbbd2
		Graph G = new Graph(); 
		String source = "me";
		String target = "food";

		G.add(new Node(source)); // Node "me" (0)
<<<<<<< HEAD
		for(int i=1;i<biggerBoxesPoints.size()-1;i++) {
=======

		for(int i=2;i<pp.size();i++) {
>>>>>>> 4c24a53f90e070d02a50cfd01ad3327e0eacbbd2
			Node d = new Node(""+i);
			G.add(d);
		}
		G.add(new Node(target)); // Node "food" last index
<<<<<<< HEAD
		for (int i = 0; i < biggerBoxesPoints.size()-1; i++) {
			for (int j = i+1; j < biggerBoxesPoints.size(); j++) {
				if(!m.closeBoxVertexs(boxes, biggerBoxesPoints.get(i), biggerBoxesPoints.get(j))) {
					
					if(i==0) {
						if(j==biggerBoxesPoints.size()-1) {
							G.addEdge("me", "food", m.PixeldistanceInMeters(biggerBoxesPoints.get(i), biggerBoxesPoints.get(j)));
						}
						G.addEdge("me", ""+j, m.PixeldistanceInMeters(biggerBoxesPoints.get(i), biggerBoxesPoints.get(j)));
=======

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
					else if(i==1) {
						G.addEdge("food", ""+j, m.PixeldistanceInMeters(pp.get(i), pp.get(j)));
					}
					else {
						G.addEdge(""+i, ""+j,m.PixeldistanceInMeters(pp.get(i), pp.get(j)));
>>>>>>> 4c24a53f90e070d02a50cfd01ad3327e0eacbbd2
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
//		System.out.println("***** Graph Demo for OOP_Ex4 *****");
//		System.out.println(b);
//		System.out.println("Dist: "+b.getDist());
		ArrayList<String> shortestPath = b.getPath();
//		for(int i=0;i<shortestPath.size();i++) {
//			System.out.println(","+shortestPath.get(i));
//		}
		String s = shortestPath.get(1);
		Pixel ans = biggerBoxesPoints.get(Integer.parseInt(s));	
		//System.out.println("the answer is" +ans);
		return ans;		
	}

}
