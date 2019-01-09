package Algo;

import java.awt.Graphics;
import java.util.ArrayList;
import Algo.Node;
import Algo.ShortestPath;
import Coords.MyCoords;
import GIS.Box;
import GIS.Game;
import GIS.Map;
import GIS.Myplayer;
import GIS.Pacman;
import GIS.Path;
import GUI.MyFrame;
import Geom.Pixel;
import Robot.Play;
import Geom.Point3D;
/**
 * 
 * @author Ortal ,Tomer and Avichay
 * @note this class response to represent 
 * the shortest algorithm of each pacman's path.
 *
 */
public class Direction {
	private Game game;
	private Path path;
	private ShortestPath sp = new ShortestPath();
	private MyCoords mc=new MyCoords();
	private Map m = new Map();
	public Direction() {
		this.game = new Game();
	}
	public Direction(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

	public double direction(Game game) {
		mc = new MyCoords();
		Myplayer myplayer = game.getMyplayer();
		double w = 1386; //x
		double h = 732; //y
		int minIndex =0;
		char type ='F';
		double teta =0;
		
		double minTime= (mc.distance3d(myplayer.getGps(),game.getFruits().get(0).getGps()))/myplayer.getSpeed();
		for (int j = 0; j < game.getFruits().size(); j++) {
			if((mc.distance3d(myplayer.getGps(),game.getFruits().get(j).getGps()))/myplayer.getSpeed() < minTime) {
				minTime =(mc.distance3d(myplayer.getGps(),game.getFruits().get(j).getGps()))/myplayer.getSpeed();
				minIndex =j;
			}
		}
		for (int j = 0; j < game.getPacmans().size(); j++) {
			if((mc.distance3d(myplayer.getGps(),game.getPacmans().get(j).getGps()))/myplayer.getSpeed() < minTime &&
					(!insideBox(game.getPacmans().get(j), game.getBoxes()))) {
				type ='P';
				minTime =(mc.distance3d(myplayer.getGps(),game.getPacmans().get(j).getGps()))/myplayer.getSpeed();
				minIndex =j;
			}
		}
		//		ArrayList<Point3D> tempBoxesPoints = new ArrayList<>();
		//		for (int i = 0; i < game.getBoxes().size(); i++) {
		//			tempBoxesPoints.add(game.getBoxes().get(i).getGps1());
		//			tempBoxesPoints.add(game.getBoxes().get(i).getGps2());
		//			tempBoxesPoints.add(new Point3D(game.getBoxes().get(i).getGps1().get_x(),game.getBoxes().get(i).getGps2().get_y()));
		//			tempBoxesPoints.add(new Point3D(game.getBoxes().get(i).getGps2().get_x(),game.getBoxes().get(i).getGps1().get_y()));
		//		}

		if(type == 'F') {

			if(!m.closeBoxVertexs(game.getBoxes(), game.getMyplayer().getPix(),game.getFruits().get(minIndex).getPix())) {
				return teta = mc.azimuth_elevation_dist(game.getMyplayer().getGps(), game.getFruits().get(minIndex).getGps())[0];	
			}
			else {
				Dijkstra dijkstra = new Dijkstra();
				Pixel ansPix =dijkstra.dijkstraAlgo(game.getBoxes(),game.getMyplayer().getPix(),game.getFruits().get(minIndex).getPix());
				Point3D ansGps = m.getLatLonfromXY(ansPix);
				return teta =mc.azimuth_elevation_dist(game.getMyplayer().getGps(), ansGps)[0];
			}
		}
		else {

			if(!m.closeBoxVertexs(game.getBoxes(), game.getMyplayer().getPix(),game.getPacmans().get(minIndex).getPix())) {
				return teta = mc.azimuth_elevation_dist(game.getMyplayer().getGps(), game.getPacmans().get(minIndex).getGps())[0];	
			}
			else {
				Dijkstra dijkstra = new Dijkstra();
				Pixel ansPix =dijkstra.dijkstraAlgo(game.getBoxes(),game.getMyplayer().getPix(),game.getPacmans().get(minIndex).getPix());
				Point3D ansGps = m.getLatLonfromXY(ansPix);
				return teta =mc.azimuth_elevation_dist(game.getMyplayer().getGps(), ansGps)[0];
			}

		}
		//System.out.println("final answer: " + teta);
	}
	
	/*
	 * https://www.geeksforgeeks.org/check-whether-given-point-lies-inside-rectangle-not/
	 * using this web for insideBox check area functions
	 */
	public boolean insideBox(Pacman pacman , ArrayList<Box>boxes) {

		boolean flag = false;
		for (int i = 0; i < boxes.size(); i++) {
			int x1 = (int)boxes.get(i).getPix1().getX();
			int y1 = (int)boxes.get(i).getPix1().getY();
			int x2 = (int)boxes.get(i).getPix2().getX();
			int y2 = (int)boxes.get(i).getPix1().getY();
			int x3 = (int)boxes.get(i).getPix2().getX();
			int y3 = (int)boxes.get(i).getPix2().getY();
			int x4 = (int)boxes.get(i).getPix1().getX();
			int y4 = (int)boxes.get(i).getPix2().getY();

			int x = (int)pacman.getPix().getX();
			int y = (int)pacman.getPix().getY();

			if(check(x1, y1, x2, y2, x3, y3, x4, y4, x, y)) {
				return flag =true;
			}
		} 
		return flag;
	}

	public boolean check(int x1, int y1, int x2, int y2, 
			int x3, int y3, int x4, int y4, int x, int y) 
	{ 
		/* Calculate area of rectangle ABCD */
		float A = area(x1, y1, x2, y2, x3, y3)+  
				area(x1, y1, x4, y4, x3, y3); 

		/* Calculate area of triangle PAB */
		float A1 = area(x, y, x1, y1, x2, y2); 

		/* Calculate area of triangle PBC */
		float A2 = area(x, y, x2, y2, x3, y3); 

		/* Calculate area of triangle PCD */
		float A3 = area(x, y, x3, y3, x4, y4); 

		/* Calculate area of triangle PAD */
		float A4 = area(x, y, x1, y1, x4, y4); 

		/* Check if sum of A1, A2, A3 and A4  
is same as A */
		return (A == A1 + A2 + A3 + A4) || ((y==y1 && x>x1 && x<x2)||
				(x==x2 && y<y1 && y>y2)|| (y==y2 && x<x2 && x>x1)||
				(x==x1 && y<y1 && y>y2)) ; 
	} 
	public float area(int x1, int y1, int x2,  
			int y2, int x3, int y3) 
	{ 
		return (float)Math.abs((x1 * (y2 - y3) +  
				x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0); 
	}
}