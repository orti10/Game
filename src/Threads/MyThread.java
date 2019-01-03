package Threads;

import GIS.Game;

import java.awt.event.MouseEvent;

import GIS.Map;
import Coords.MyCoords;
import GIS.Direction;
import GUI.MyFrame;
import Robot.Play;
import Geom.Pixel;
import Geom.Point3D;

/**
 * @author Ortal, Tomer and Avichay
 * @note this class extends "Thread" to be able to 
 * move on the same time pacmans and creating them paths and etc. 
 */
public class MyThread extends Thread {

	private Game game;
	private MyFrame mf;
	private Play play1;
	private Point3D mouse;
	private Map m;
	private double dgree;

	/**
	 * 
	 * @param mf (MyFrame)
	 * @param game
	 * 
	 * @note this method using the frame the game already existing.
	 */
	public MyThread (MyFrame mf,Game game,Play play1) {
		super();
		this.mf = mf;
		this.game = game;
		this.play1=play1;
	}

	
	public double getDgree() {
		return dgree;
	}


	public void setDgree(double dgree) {
		this.dgree = dgree;
	}


	/**
	 * run starts the game on the board when the user click/choose "run" option from the menu.
	 * using to find the shortest and the fastest path for each pacman on the board 
	 * and moves then on the same time.
	 */

	public void run() {

		play1.start();	
		Direction dir = new Direction(game);
		
		//double azimuth = dgree;//mc.azimuth_elevation_dist(game.getMyplayer().getGps(), mouse)[0];
		double teta=dir.direction(play1.getBoard());

		while(play1.isRuning()) {
			//making sure that the pacmans are moving and the fruits are stable
//			if(mouse==null) {
//			game.getAf().clear();
//			game.getAp().clear();
//			game.getGhosts().clear();
//			game.ReadBoard(play1.getBoard());
//			play1.rotate(teta);
//			mf.repaint();
//			}	
//			else if(mouse!=null) {
				play1.rotate(getDgree());
				game.getAf().clear();
				game.getAp().clear();
				game.getGhosts().clear();
				game.ReadBoard(play1.getBoard());
				
				mf.repaint();
			//}
			try {
				sleep(100);

			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		}
	mf.repaint();
	}
}
//calculate when the loop ends
//		long endTime = System.nanoTime() -startTime;
//		System.out.println("The algorithem runing time is: " +(endTime/1000000000) +" seconds");
//		for (int i = 0; i < game.getAp().size(); i++) {
//			int timeAnswer =0;
//			for (int j = 0; j < game.getAp().get(i).getPath().getPath().size(); j++) {
//				timeAnswer += game.getAp().get(i).getPath().getPath().get(j).getTimeMet();
//			}
//			System.out.println("Pacman " + (i+1) + " took " + timeAnswer + " seconds to run");
//		}
//		mf.repaint();
//}