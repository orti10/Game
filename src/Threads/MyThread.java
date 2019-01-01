package Threads;

import GIS.Game;
import GIS.Direction;
import GUI.MyFrame;
import Robot.Play;

/**
 * @author Ortal, Tomer and Avichay
 * @note this class extends "Thread" to be able to 
 * move on the same time pacmans and creating them paths and etc. 
 */
public class MyThread extends Thread {

	private Game game;
	private MyFrame mf;
	private Play play1;
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
	/**
	 * run starts the game on the board when the user click/choose "run" option from the menu.
	 * using to find the shortest and the fastest path for each pacman on the board 
	 * and moves then on the same time.
	 */
	public void run() {
		
		play1.start();	
		Direction dir = new Direction(game);		
		while(play1.isRuning()) {
			System.out.println(play1.getBoard());
			//making sure that the pacmans are moving and the fruits are stable
			game.getAf().clear();
			game.getAp().clear();
			game.ReadBoard(play1.getBoard());
			double teta=dir.direction(play1.getBoard());
			play1.rotate(teta);
			System.out.println("the teta is: " +dir.direction(play1.getBoard()));
			mf.repaint();

			try {
				sleep(100);
				mf.repaint();
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