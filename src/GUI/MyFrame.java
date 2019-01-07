package GUI;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.swing.filechooser.*;

import Coords.MyCoords;
import GIS.Box;
import GIS.Direction;
import GIS.Fruit;
import GIS.Game;
import GIS.Ghost;
import GIS.Map;
import GIS.Myplayer;
import GIS.Pacman;
import Geom.Pixel;
import Geom.Point3D;
import Robot.Play;
import Threads.MyThread;
import javax.imageio.ImageIO;
import graph.Graph_Algo;


/**
 * @note Code taken from: https://javatutorial.net/display-text-and-graphics-java-jframe
 * 
 */
//https://coderanch.com/t/338737/java/draw-points-Java

/**
 * @author Ortal, Tomer and Avichay
 * @note This class creates the frame of the whole game
 * makes sure everything works with harmoniously
 */
public class MyFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public BufferedImage myImage ,packmanIcon ,fruitIcon,ghostIcon,MyplayerIcon;
	private Game game = new Game();
	private Play play1 = new Play();
	private Map m = new Map();
	private MyThread thread;
	private MyCoords mc = new MyCoords();
	private Direction dir = new Direction();


	private double w = 1386;
	private double h = 732;

	public MyFrame(Game g ,String file_name) {
		this.game = g;
	}
	public MyFrame() {
		initGUI();	
	}
	/** void method
	 * @note This method controls the activation on the menu and create this response
	 */
	private void initGUI() {

		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("Game"); 
		JMenuItem clear = new JMenuItem("Clear");
		JMenuItem open = new JMenuItem("Open");
		JMenuItem exit = new JMenuItem("Exit");

		JMenu input = new JMenu("Input"); 
		JMenuItem myplayer = new JMenuItem("myplayer");
		JMenuItem runAuto = new JMenuItem("RunAuto");
		JMenuItem runMouse = new JMenuItem("RunMouse");


		menuBar.add(file);
		file.add(clear); 
		file.add(open); 
		file.add(exit); 
		menuBar.add(input);
		input.add(myplayer);
		input.add(runAuto); 
		input.add(runMouse); 

		//if the user wants to use a shortcuts on the keyboard
		clear.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,  ActionEvent.CTRL_MASK));
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,  ActionEvent.CTRL_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,  ActionEvent.CTRL_MASK));

		this.setJMenuBar(menuBar);
		try {
			myImage = ImageIO.read(new File("C:\\temp\\Ariel1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		//if the user wants to open(load) the new file
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser();

				fs.setCurrentDirectory(new File("c:\\"));
				fs.setDialogTitle("Open a File");
				fs.setFileFilter(new FileNameExtensionFilter("csv file","csv"));
				int result=fs.showOpenDialog(null);

				if(result==JFileChooser.APPROVE_OPTION) {
					File f=fs.getSelectedFile();
					String fi=f.getPath();

					if(fi!=null) {
						play1  = new Play(fi);
						play1.setIDs(205672538,312485147,309612307);
						game.ReadCsv(fi);	
						repaint();
					}
				}
			}
		});
		//if the user wants to clear the new frame
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game = new Game();
				repaint();	
			}
		});
		//if the user wants to exit the frame
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		myplayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg) {

						Map m = new Map();
						Myplayer player = new Myplayer();
						Pixel pix =new Pixel(arg.getX(), arg.getY());
						Point3D gps = new Point3D(m.getLatLonfromXY(pix));
						player.setGps(gps);
						player.setPix(pix);
						game.setMyplayer(player);
						play1.setInitLocation(player.getGps().get_x(),player.getGps().get_y());
						repaint();	
						removeMouseListener(this);
					}
				});
			}
		});

		//if the user wants to run the game
		//using Thread to make the moves to be on the same time when the game is running
		runAuto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//link to MyThread	
				thread = new MyThread(MyFrame.this,game,play1);
				thread.setMouse(false);
				thread.start();	
		}
			
	});
	
		runMouse.addActionListener(new ActionListener() 
		{		
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				thread = new MyThread(MyFrame.this,game,play1);
				thread.setMouse(true);
				thread.start();	

				addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg) {
						//link to MyThread
						if(play1.isRuning()) {
							Pixel click = new Pixel(arg.getX(),arg.getY());
							Point3D mouse = new Point3D(m.getLatLonfromXY(click));
							double azimuth = mc.azimuth_elevation_dist(game.getMyplayer().getGps(),mouse)[0];
							thread.setAzimuth(azimuth);
						}
						if(!play1.isRuning()) {
							Pixel click = new Pixel(arg.getX(),arg.getY());
							MyThread t = new MyThread(MyFrame.this,game,play1);
							t.start();
						}
					}
				});
			}
		});
}
/**
 * @param Graphics g
 * @note This void method creating the frame with pacmans and fruits , 
 * the size of them and how they will be represented on the frame.
 * keeps proportion of the screen when the user moves it using "Proportion" method.
 */
public void paint(Graphics g)
{
	this.setJMenuBar(getJMenuBar());

	try {

		packmanIcon =ImageIO.read(new File("Icons/pacman.png"));
		fruitIcon =ImageIO.read(new File("Icons/fruit.png"));
		ghostIcon =ImageIO.read(new File("Icons/ghost.png"));
		MyplayerIcon =ImageIO.read(new File("Icons/MyPlayer.png"));

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	g.drawImage(myImage , -8,-8,this.getWidth(),this.getHeight(), null);

	g.drawImage(MyplayerIcon,(int)game.getMyplayer().getPix().getX(),(int) game.getMyplayer().getPix().getY(),null);	

	for (Pacman p : game.getPacmans()) {
		g.drawImage(packmanIcon,(int) p.getPix().getX(), (int) p.getPix().getY(),null);	
	}
	for (Fruit f : game.getFruits()) {
		g.drawImage(fruitIcon,(int) f.getPix().getX(), (int) f.getPix().getY(),null);	
	}
	for (Box box : game.getBoxes()) {
		g.setColor(Color.black);
		g.fillRect((int)box.getPix1().getX(),(int)box.getPix2().getY(), (int)(box.getPix2().getX()-box.getPix1().getX()), (int)(box.getPix1().getY()-box.getPix2().getY()));	
	}
	for (Ghost ghost : game.getGhosts()) {
		g.drawImage(ghostIcon,(int)ghost.getPix().getX(), (int)ghost.getPix().getY(),null);
	}

	Proportionsize ( w , h);
}

/**
 * 
 * @param w width of screen
 * @param h height of screen
 * 
 * @note this void method calculate the proportion size of the screen.
 */
public void Proportionsize ( double w , double h) {

	for (Pacman p1 : game.getPacmans()) {
		double newX = p1.getPix().getX()*(this.getWidth()/w);
		double newY = p1.getPix().getY()*(this.getHeight()/h);
		Pixel pix =new Pixel(newX,newY);
		p1.setPix(pix);
	}
	for (Fruit f1 : game.getFruits()) {
		double newX = f1.getPix().getX()*(this.getWidth()/w);
		double newY = f1.getPix().getY()*(this.getHeight()/h);
		Pixel pix =new Pixel(newX,newY);
		f1.setPix(pix);
	}
	for (Box box : game.getBoxes()) {
		double newX1 = box.getPix1().getX()*(this.getWidth()/w);
		double newY1 = box.getPix1().getY()*(this.getHeight()/h);
		double newX2= box.getPix2().getX()*(this.getWidth()/w);
		double newY2 = box.getPix2().getY()*(this.getHeight()/h);
		Pixel pix1 =new Pixel(newX1,newY1);
		Pixel pix2 =new Pixel(newX2,newY2);
		box.setPix1(pix1);
		box.setPix2(pix2);
	}
	for (Ghost ghost : game.getGhosts()) {
		double newX = ghost.getPix().getX()*(this.getWidth()/w);
		double newY = ghost.getPix().getY()*(this.getHeight()/h);
		Pixel pix =new Pixel(newX,newY);
		ghost.setPix(pix);
	}
	double newX = game.getMyplayer().getPix().getX()*(this.getWidth()/w);
	double newY = game.getMyplayer().getPix().getY()*(this.getHeight()/h);
	Pixel pix =new Pixel(newX,newY);
	this.w=this.getWidth();
	this.h=this.getHeight();
	game.getMyplayer().setPix(pix);
}

public void mouseEntered(MouseEvent arg0) {
}
public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
}
public void mousePressed(MouseEvent arg0) {
}
public void mouseReleased(MouseEvent arg0) {
}

public static void main(String[] args){

	MyFrame mf = new MyFrame();
	mf.setVisible(true);
	mf.setSize(mf.myImage.getWidth(),mf.myImage.getHeight());
	mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}