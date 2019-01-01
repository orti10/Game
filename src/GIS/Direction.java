package GIS;

import java.util.ArrayList;
import Coords.MyCoords;
import Geom.Pixel;
/**
 * 
 * @author Ortal ,Tomer and Avichay
 * @note this class response to represent 
 * the shortest algorithm of each pacman's path.
 *
 */
public class Direction {
	private Game game;

	public Direction() {
		this.game = new Game();
	}
	public Direction(Game game) {
		this.game = game;
	}
	public Game getGame() {
		return game;
	}


	public double direction(ArrayList<String> board) {
		MyCoords mc = new MyCoords();
		Myplayer myplayer = game.getMyplayer();
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
		if(myplayer.getPix().getY() - game.getBoxes().get(1).getPix1().getY()<3) {
			teta = 90;
		}
		
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
//
//			}
//		}
		return teta;
	}

}