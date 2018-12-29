package GUI;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.swing.filechooser.*;
import GIS.Fruit;
import GIS.Game;
import GIS.Line;
import GIS.Map;
import GIS.Pacman;
import Geom.Pixel;
import Geom.Point3D;
import Threads.MyThread;
import javax.imageio.ImageIO;

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
	public BufferedImage myImage ,packmanIcon ,fruitIcon;
	private Game game = new Game();
	private ArrayList<Line> lineArray = new ArrayList<Line>();

	private double w = 1386;
	private double h = 732;

	public MyFrame(Game g) {
		this.game = g;
	}
	public MyFrame() {
		initGUI();	
	}
/** void method
 * @note This method controls the activation on the menu and create this response
 */
	private void initGUI() 
	{
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("Game"); 
		JMenuItem clear = new JMenuItem("Clear");
		JMenuItem open = new JMenuItem("Open");
		JMenuItem saveToKML = new JMenuItem("Save To KML");
		JMenuItem saveToCSV = new JMenuItem("Save to CSV");
		JMenuItem exit = new JMenuItem("Exit");

		JMenu input = new JMenu("Input"); 
		JMenuItem pacman = new JMenuItem("Pacman");
		JMenuItem fruit = new JMenuItem("Fruit");
		JMenuItem run = new JMenuItem("Run");

		menuBar.add(file);
		file.add(clear); 
		file.add(open); 
		file.add(saveToKML); 
		file.add(saveToCSV); 
		file.add(exit); 
		menuBar.add(input);
		input.add(pacman); 
		input.add(fruit); 
		input.add(run); 
		
		//if the user wants to use a shortcuts on the keyboard
		clear.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,  ActionEvent.CTRL_MASK));
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,  ActionEvent.CTRL_MASK));
		saveToKML.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,  ActionEvent.CTRL_MASK));
		saveToCSV.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,  ActionEvent.CTRL_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,  ActionEvent.CTRL_MASK));
		run.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,  ActionEvent.CTRL_MASK));

		this.setJMenuBar(menuBar);
		try {
			myImage = ImageIO.read(new File("C:\\temp\\Ariel1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	
		saveToCSV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser(new File("c:\\"));
				fs.setDialogTitle("Save a CSV File");
				fs.setSelectedFile(new File ("CSV.csv"));
				//"result" for double checking 
				int result=fs.showSaveDialog(null);
				
				String location = fs.getSelectedFile().toString();
				game.WriteCSV(location);		
			}
		});
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
						game = game.ReadCSV(fi);
						Map m = new Map();

						ArrayList<Fruit> sf = game.getAf();
						for(int i =0; i < game.getAf().size(); i++) {
							Pixel pixel = m.getXYfromLatLon(sf.get(i).getGps().get_x(), sf.get(i).getGps().get_y());
							sf.get(i).setPix(pixel);
						}
						ArrayList<Pacman> sp = game.getAp();

						for(int i =0; i < game.getAp().size(); i++) {
							Pixel pixel = m.getXYfromLatLon(sp.get(i).getGps().get_x(), sp.get(i).getGps().get_y());
							sp.get(i).setPix(pixel);
						}			
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
				lineArray = new ArrayList<Line>();
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
		//if the user wants to create the new pacman
		pacman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg) {
						Map m = new Map();
						Pacman p = new Pacman();
						Pixel pix =new Pixel(arg.getX(), arg.getY());
						Point3D gps = new Point3D(m.getLatLonfromXY(pix));
						p.setGps(gps);
						p.setPix(pix );
						game.getAp().add(p);
						repaint();	
						removeMouseListener(this);
					}
				});
			}
		});
		//if the user wants to create the new fruit
		fruit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg) {
						Map m = new Map();
						Fruit f = new Fruit();
						Pixel pix =new Pixel(arg.getX(), arg.getY());
						Point3D gps = new Point3D(m.getLatLonfromXY(pix));
						f.setGps(gps);
						f.setPix(pix );
						game.getAf().add(f);
						repaint();	
						removeMouseListener(this);
					}
				});
			}
		});

		//if the user wants to run the game
		//using Thread to make the moves to be on the same time when the game is running
		run.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MyThread t = new MyThread(MyFrame.this,game,lineArray);
				t.start();
				//link to MyThread
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
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		try {
			packmanIcon =ImageIO.read(new File("C:\\Users\\tomer\\eclipse-workspace\\MyProject3\\pacman.png"));
			fruitIcon =ImageIO.read(new File("C:\\Users\\tomer\\eclipse-workspace\\MyProject3\\fruit.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g2.drawImage(myImage , -8,-8,this.getWidth(),this.getHeight(), null);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		for (Pacman p : game.getAp()) {
			g2.setColor(Color.yellow);
			g2.drawImage(packmanIcon,(int) p.getPix().getX(), (int) p.getPix().getY(),null);	
		}
		for (Fruit f : game.getAf()) {
			g2.setColor(Color.red);
			g2.drawImage(fruitIcon,(int) f.getPix().getX(), (int) f.getPix().getY(),null);	
		}

		for(int i=0;i<lineArray.size();i++) {
			g2.setColor(Color.red);
			g2.drawLine((int)lineArray.get(i).getStart().getX(),(int)lineArray.get(i).getStart().getY(),
					(int)lineArray.get(i).getEnd().getX(),(int)lineArray.get(i).getEnd().getY());
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

		for (Pacman p1 : game.getAp()) {
			double newX = p1.getPix().getX()*(this.getWidth()/w);
			double newY = p1.getPix().getY()*(this.getHeight()/h);
			Pixel pix =new Pixel(newX,newY);
			p1.setPix(pix);
		}
		for (Fruit f1 : game.getAf()) {
			double newX = f1.getPix().getX()*(this.getWidth()/w);
			double newY = f1.getPix().getY()*(this.getHeight()/h);
			Pixel pix =new Pixel(newX,newY);
			f1.setPix(pix);
		}
		for (Line l:lineArray ) {
			double newXstart = l.getStart().getX()*(this.getWidth()/w);
			double newYstart = l.getStart().getY()*(this.getHeight()/h);
			double newXend = l.getEnd().getX()*(this.getWidth()/w);
			double newYend = l.getEnd().getY()*(this.getHeight()/h);
			l.setStart(new Pixel(newXstart,newYstart));
			l.setEnd(new Pixel(newXend,newYend));
		}
		this.w=this.getWidth();
		this.h=this.getHeight();
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