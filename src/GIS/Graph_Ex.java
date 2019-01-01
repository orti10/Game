package GIS;

import java.util.ArrayList;

import graph.Graph;
import graph.Graph_Algo;
import graph.Node;
import graph.Point3D;
/**
 * This main class demonstrate a use of Dijkstra algorithm for finding the shortest path
 * between a source vertex on a graph to all other vertices.
 * The main class is based on the image of p_graph_ex4.png
 * @author ben-moshe
 *
 */
public class Graph_Ex{
	
	
	
	public void ShortPath(Game game) {
		int size = game.getBoxes().size()*4;
		double[] xx = new double[size];
		double[] yy = new double[size];
		Point3D[] pp = new Point3D[size];
		for (int i = 0; i < game.getBoxes().size(); i++) {
			for (int j = 0; j < size; j=j+4) {
				xx[j] = game.getBoxes().get(i).getGps1().get_x(); //(x1,y1)
				yy[j] = game.getBoxes().get(i).getGps1().get_y();
				xx[j+1] = game.getBoxes().get(i).getGps1().get_x(); //(x1,y2)
				yy[j+1] = game.getBoxes().get(i).getGps2().get_y(); 
				xx[j+2] = game.getBoxes().get(i).getGps2().get_x(); //(x2,y1)
				yy[j+2] = game.getBoxes().get(i).getGps1().get_y();
				xx[j+3] = game.getBoxes().get(i).getGps2().get_x(); //(x2,y2)
				yy[j+3] = game.getBoxes().get(i).getGps2().get_y();
			}
			
		}
		Point3D my = new Point3D(game.getMyplayer().getGps().get_x(),game.getMyplayer().getGps().get_y());
		pp[0] = my;
		Point3D FruitPac = new Point3D(game.getMyplayer().getGps().get_x(),game.getMyplayer().getGps().get_y());
		
		for(int i=1;i<size;i++) {
			pp[i] = new Point3D(xx[i], yy[i]);
		}

		Graph G = new Graph(); 
		String source = "MyPlayer";
		String target = "Fruit/Pacman";
		G.add(new Node(source)); // Node "MyPlayer" (0)
		for(int i=1;i<size-1;i++) {
			Node d = new Node(""+i);
			G.add(d);
			pp[i] = new Point3D(xx[i], yy[i]);
		}
		G.add(new Node(target)); // Node "Fruit/Pacman" (15)

		G.addEdge("a","1",pp[0].distance2D(pp[1]));
		G.addEdge("a","2",pp[0].distance2D(pp[2]));
		G.addEdge("a","5",pp[0].distance2D(pp[5]));
		G.addEdge("a","6",pp[0].distance2D(pp[6]));

		G.addEdge("1","2",pp[1].distance2D(pp[2]));
		G.addEdge("1","3",pp[1].distance2D(pp[3]));
		G.addEdge("3","4",pp[3].distance2D(pp[4]));

		G.addEdge("5","6",pp[5].distance2D(pp[6]));

		G.addEdge("9","10",pp[9].distance2D(pp[10]));
		G.addEdge("6","9",pp[6].distance2D(pp[9]));
		G.addEdge("7","10",pp[7].distance2D(pp[10]));

		G.addEdge("7","8",pp[7].distance2D(pp[8]));
		G.addEdge("8","13",pp[8].distance2D(pp[13]));
		G.addEdge("8","14",pp[8].distance2D(pp[14]));

		G.addEdge("11","12",pp[11].distance2D(pp[12]));
		G.addEdge("11","13",pp[11].distance2D(pp[13]));
		G.addEdge("12","14",pp[12].distance2D(pp[14]));
		G.addEdge("13","14",pp[13].distance2D(pp[14]));

		G.addEdge("4","11",pp[4].distance2D(pp[11]));
		G.addEdge("4","12",pp[4].distance2D(pp[12]));
		G.addEdge("3","12",pp[3].distance2D(pp[12]));

		G.addEdge("8","b",pp[8].distance2D(pp[15]));
		G.addEdge("13","b",pp[13].distance2D(pp[15]));
		G.addEdge("11","b",pp[11].distance2D(pp[15]));

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
	}
}