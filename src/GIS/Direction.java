package GIS;

import java.awt.Graphics;
import java.util.ArrayList;
import Algo.Node;
import Algo.ShortestPath;
import Coords.MyCoords;
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
	private Dijkstra dijkstra = new Dijkstra();
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
		double w = 1386;//x
		double h = 732;//y
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
			if((mc.distance3d(myplayer.getGps(),game.getPacmans().get(j).getGps()))/myplayer.getSpeed() < minTime) {
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
			System.out.println("in Fruit");

			if(!m.closeBoxVertexs(game.getBoxes(), game.getMyplayer().getPix(),game.getFruits().get(minIndex).getPix())) {
				return teta = mc.azimuth_elevation_dist(game.getMyplayer().getGps(), game.getFruits().get(minIndex).getGps())[0];	
			}
			else {
				Pixel ansPix =dijkstra.dijkstraAlgo(game.getBoxes(),game.getMyplayer().getPix(),game.getFruits().get(minIndex).getPix());
				Point3D ansGps = m.getLatLonfromXY(ansPix);
				return teta =mc.azimuth_elevation_dist(game.getMyplayer().getGps(), ansGps)[0];
			}
		}
		else {
				System.out.println("in pacman");

			if(!m.closeBoxVertexs(game.getBoxes(), game.getMyplayer().getPix(),game.getPacmans().get(minIndex).getPix())) {
				return teta = mc.azimuth_elevation_dist(game.getMyplayer().getGps(), game.getPacmans().get(minIndex).getGps())[0];	
			}
			else {
				Pixel ansPix =dijkstra.dijkstraAlgo(game.getBoxes(),game.getMyplayer().getPix(),game.getPacmans().get(minIndex).getPix());
				Point3D ansGps = m.getLatLonfromXY(ansPix);
				return teta =mc.azimuth_elevation_dist(game.getMyplayer().getGps(), ansGps)[0];
			}

		}
		//System.out.println("final answer: " + teta);
	}

}