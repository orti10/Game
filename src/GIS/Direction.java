package GIS;

import java.awt.Graphics;
import java.util.ArrayList;
import Coords.MyCoords;
import GUI.MyFrame;
import Geom.Pixel;
import Robot.Play;
import graph.Point3D;
/**
 * 
 * @author Ortal ,Tomer and Avichay
 * @note this class response to represent 
 * the shortest algorithm of each pacman's path.
 *
 */
public class Direction {
	private Game game;
	private Graph_Ex graph;

	public Direction() {
		this.game = new Game();
		this.graph = new Graph_Ex();
	}
	public Direction(Game game) {
		this.game = game;
	}
	public Direction(Graph_Ex graph) {
		super();
		this.graph = graph;
	}
	public Game getGame() {
		return game;
	}
	public Graph_Ex getGraph() {
		return graph;
	}

	public double direction(ArrayList<String> board) {
		MyCoords mc = new MyCoords();
		Myplayer myplayer = game.getMyplayer();
		double w = 1386;
		double h = 732;
		int minIndex =0;
		char type ='F';
		double teta =0;
		double minTime= (mc.distance3d(myplayer.getGps(),game.getAf().get(0).getGps()))/myplayer.getSpeed();
		for (int j = 0; j < game.getAf().size(); j++) {
			if((mc.distance3d(myplayer.getGps(),game.getAf().get(j).getGps()))/myplayer.getSpeed() < minTime) {
				minTime =(mc.distance3d(myplayer.getGps(),game.getAf().get(j).getGps()))/myplayer.getSpeed();
				minIndex =j;
			}
		}
		for (int j = 0; j < game.getAp().size(); j++) {
			if((mc.distance3d(myplayer.getGps(),game.getAp().get(j).getGps()))/myplayer.getSpeed() < minTime) {
				type ='P';
				minTime =(mc.distance3d(myplayer.getGps(),game.getAp().get(j).getGps()))/myplayer.getSpeed();
				minIndex =j;
			}
		}

		if(type == 'F') {
			double[] angles =mc.azimuth_elevation_dist(myplayer.getGps(), game.getAf().get(minIndex).getGps());
			teta = angles[0];
			//			Point3D ff= new Point3D(game.getAf().get(minIndex).getGps().get_x(), game.getAf().get(minIndex).getGps().get_y());
			//			graph.ShortPath(game,ff);
		}
		else {
			double[] angles =mc.azimuth_elevation_dist(myplayer.getGps(), game.getAp().get(minIndex).getGps());
			teta = angles[0];
			//			Point3D fp= new Point3D(game.getAp().get(minIndex).getGps().get_x(), game.getAp().get(minIndex).getGps().get_y());
			//			graph.ShortPath(game,fp);
		}
		//touch with box (limit)
		if(myplayer.getPix().getY() - game.getBoxes().get(1).getPix1().getY()<3) {
			if(myplayer.getPix().distance(game.getBoxes().get(1).getPix1())
					>myplayer.getPix().distance(game.getBoxes().get(1).getPix2())) {
				teta=90;
				if(myplayer.getPix().getX()>game.getBoxes().get(1).getPix2().getX()) {
					teta=0;
					System.out.println("mpY : "+myplayer.getPix().getY());
					System.out.println("Y--- : "+game.getBoxes().get(1).getPix2().getY());
				//err
					if(myplayer.getPix().getY()<game.getBoxes().get(1).getPix2().getY()) {
						teta=270;
					}
				}
			}
		}

		//				if(myplayer.getPix().getY() > h && myplayer.getPix().getX() > w) {
		//					
		//				}


		//		for (int i = 0; i < game.getBoxes().size() ; i++) {
		//			if(((myplayer.getPix().getX() > game.getBoxes().get(1).getPix1().getX()+50)
		//					&& (myplayer.getPix().getX() < game.getBoxes().get(1).getPix2().getX()-50)) 
		//					||
		//					(myplayer.getPix().getY() > game.getBoxes().get(1).getPix1().getY()-50) 
		//					&& (myplayer.getPix().getY() < game.getBoxes().get(1).getPix2().getY()+50)){
		//				System.out.println("First if");
		//
		//			}
		//			else {
		//
		//				System.out.println("seconde if");
		//				teta = teta +10;
		//			}
		//		}
		return teta;
	}
}