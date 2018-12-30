package Threads;

import java.util.ArrayList;
import GIS.Game;
import GIS.ShortestPathAlgo;
import GUI.MyFrame;

/**
 * @author Ortal, Tomer and Avichay
 * @note this class extends "Thread" to be able to 
 * move on the same time pacmans and creating them paths and etc. 
 */
public class MyThread extends Thread {

	private Game game;
	private MyFrame mf;
/**
 * 
 * @param mf (MyFrame)
 * @param game
 * 
 * @note this method using the frame the game already existing.
 */
	public MyThread (MyFrame mf,Game game) {
		super();
		this.mf = mf;
		this.game = game;
	}
	/**
	 * run starts the game on the board when the user click/choose "run" option from the menu.
	 * using to find the shortest and the fastest path for each pacman on the board 
	 * and moves then on the same time.
	 */
	public void run() {
		
		ShortestPathAlgo SPA = new ShortestPathAlgo();
		//calculate when the loop starts
		long startTime =System.nanoTime();
		while(game.getAf().size()!=0) {
			try {
				sleep(1000);
				mf.repaint();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//calculate when the loop ends
		long endTime = System.nanoTime() -startTime;
		System.out.println("The algorithem runing time is: " +(endTime/1000000000) +" seconds");
		for (int i = 0; i < game.getAp().size(); i++) {
			int timeAnswer =0;
			for (int j = 0; j < game.getAp().get(i).getPath().getPath().size(); j++) {
				timeAnswer += game.getAp().get(i).getPath().getPath().get(j).getTimeMet();
			}
			System.out.println("Pacman " + (i+1) + " took " + timeAnswer + " seconds to run");
		}
		mf.repaint();
	}
}