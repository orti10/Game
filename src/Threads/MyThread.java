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
	private Map m;
	private double dgree;
	private boolean mouse;

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


	public double getAzimuth() {
		return dgree;
	}


	public void setAzimuth(double dgree) {
		this.dgree = dgree;
	}

	public boolean isMouse() {
		return mouse;
	}

	public void setMouse(boolean mouse) {
		this.mouse = mouse;
	}

	/**
	 * run starts the game on the board when the user click/choose "run" option from the menu.
	 * using to find the shortest and the fastest path for each pacman on the board 
	 * and moves then on the same time.
	 */

	public void run() {

		play1.start();	
		Direction dir = new Direction();
		double teta =0;
		while(play1.isRuning()) {

			if(mouse == true) {
				play1.rotate(getAzimuth());
				game.getFruits().clear();
				game.getPacmans().clear();
				game.getGhosts().clear();
				game.getBoxes().clear();
				game.ReadBoard(play1.getBoard());

				mf.repaint();
			}
			if(mouse ==false) {
				teta =dir.direction(game);
				play1.rotate(teta);
				game.getFruits().clear();
				game.getPacmans().clear();
				game.getGhosts().clear();
				game.getBoxes().clear();
				game.ReadBoard(play1.getBoard());
				

				mf.repaint();
			}
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