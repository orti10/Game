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
		double w = 1386;//x
		double h = 732;//y
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
		}
		else {
			double[] angles =mc.azimuth_elevation_dist(myplayer.getGps(), game.getAp().get(minIndex).getGps());
			teta = angles[0];
		}

		for (int i = 0; i < game.getBoxes().size(); i++) {
			
			if(((Math.abs(myplayer.getPix().getY() - game.getBoxes().get(i).getPix1().getY())<3) ||
					(Math.abs(myplayer.getPix().getY() - game.getBoxes().get(i).getPix2().getY())<3)) 
					|| 
					((Math.abs(myplayer.getPix().getX() - game.getBoxes().get(i).getPix1().getX())<3) ||
					(Math.abs(myplayer.getPix().getX() - game.getBoxes().get(i).getPix2().getX())<3))){

				//touch with box i (moving y limit)
				if(Math.abs(myplayer.getPix().getY() - game.getBoxes().get(i).getPix1().getY())<3 ||
						(Math.abs(myplayer.getPix().getY() - game.getBoxes().get(i).getPix2().getY())<3)) {

					if(myplayer.getPix().distance(game.getBoxes().get(i).getPix1())
							>=myplayer.getPix().distance(game.getBoxes().get(i).getPix2())) {
						System.out.println("right");
						teta=90;
					}
					else {
						System.out.println("left");
						teta=270;
					}	
				}
				//touch with box i (moving x limit) 
				if(Math.abs(myplayer.getPix().getX() - game.getBoxes().get(i).getPix1().getX())<3||
						(Math.abs(myplayer.getPix().getX() - game.getBoxes().get(i).getPix2().getX())<3)) {

					if(myplayer.getPix().distance(game.getBoxes().get(i).getPix2())
							>=myplayer.getPix().distance(game.getBoxes().get(i).getPix1())) {
						System.out.println("down");
						teta=180;
					}
					else {
						System.out.println("up");
						teta=0;
					}
				}
			}
		}
		return teta;
	}
}