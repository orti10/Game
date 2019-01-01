package GIS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Geom.Pixel;
import Geom.Point3D;
/**
 * 
 * @author Ortal, Tomer and Avichay
 * @note this class represent the game having data from two array lists
 * one of pacmans and the other of fruits
 */
public class Game implements Runnable {
	private Myplayer myplayer;
	private ArrayList<Pacman> ap;
	private ArrayList<Fruit> af;
	private ArrayList<Box> boxes;
	private ArrayList<Ghost> ghosts;
	
	public Game() {
		myplayer = new Myplayer();
		ap = new ArrayList<Pacman>();
		af = new ArrayList<Fruit>();
		boxes = new ArrayList<Box>();
		ghosts = new ArrayList<Ghost>();
	}

	/**
	 * 
	 * @param csvFile
	 * @return game full of readen data
	 */
	public void ReadCsv(String csvFile) {
		BufferedReader br = null;
		String line = "";
		Map m = new Map();
		try {
			//Reads from the csv file
			br = new BufferedReader(new FileReader(csvFile)); 
			line=br.readLine(); 
			//Progressing while we have a next line to read
			while ((line = br.readLine()) != null) { 
			
				String[] s =line.split(","); 
				
				if(s[0].contains("P")) {
					Pacman pac = new Pacman();
					pac.setId(Integer.parseInt(s[1]));
					pac.setGps(new Point3D(Double.parseDouble(s[2]),Double.parseDouble(s[3]),Double.parseDouble(s[4])));
					pac.setSpeed(Double.parseDouble(s[5]));
					pac.setRadius(Double.parseDouble(s[6]));
					Pixel pixel = m.getXYfromLatLon(pac.getGps().get_x(), pac.getGps().get_y());
					pac.setPix(pixel);
					this.ap.add(pac);
				}
				else if (s[0].contains("F")){
					Fruit f = new Fruit();
					f.setId(Integer.parseInt(s[1]));
					f.setGps(new Point3D(Double.parseDouble(s[2]),Double.parseDouble(s[3]),Double.parseDouble(s[4])));
					f.setWeight(Double.parseDouble(s[5]));
					Pixel pixel = m.getXYfromLatLon(f.getGps().get_x(), f.getGps().get_y());
					f.setPix(pixel);
					this.af.add(f);
					}
				else if (s[0].contains("B")){
					Box box = new Box();
					box.setId(Integer.parseInt(s[1]));
					box.setGps1(new Point3D(Double.parseDouble(s[2]),Double.parseDouble(s[3]),Double.parseDouble(s[4])));
					box.setGps2(new Point3D(Double.parseDouble(s[5]),Double.parseDouble(s[6]),Double.parseDouble(s[7])));
					Pixel pixel1 = m.getXYfromLatLon(box.getGps1().get_x(), box.getGps1().get_y());
					Pixel pixel2 = m.getXYfromLatLon(box.getGps2().get_x(), box.getGps2().get_y());
					box.setPix1(pixel1);
					box.setPix2(pixel2);
					this.boxes.add(box);
					
				}
				else if (s[0].contains("G")){
					Ghost ghost = new Ghost();
					ghost.setId(Integer.parseInt(s[1]));
					ghost.setGps(new Point3D(Double.parseDouble(s[2]),Double.parseDouble(s[3]),Double.parseDouble(s[4])));
					Pixel pixel = m.getXYfromLatLon(ghost.getGps().get_x(), ghost.getGps().get_y());
					ghost.setPix(pixel);
					this.ghosts.add(ghost);

				}
			}
		}
			catch (IOException er) {
				er.printStackTrace();
			}

	}
	public void ReadBoard(ArrayList<String> board) {
			Map m = new Map();
			for (int i = 0; i < board.size(); i++) {
			
				String[] s =board.get(i).split(","); 
				
				if(s[0].contains("P")) {
					Pacman pac = new Pacman();
					pac.setId(Integer.parseInt(s[1]));
					pac.setGps(new Point3D(Double.parseDouble(s[2]),Double.parseDouble(s[3]),Double.parseDouble(s[4])));
					pac.setSpeed(Double.parseDouble(s[5]));
					pac.setRadius(Double.parseDouble(s[6]));
					Pixel pixel = m.getXYfromLatLon(pac.getGps().get_x(), pac.getGps().get_y());
					pac.setPix(pixel);
					this.ap.add(pac);
				}
				else if (s[0].contains("F")){
					Fruit f = new Fruit();
					f.setId(Integer.parseInt(s[1]));
					f.setGps(new Point3D(Double.parseDouble(s[2]),Double.parseDouble(s[3]),Double.parseDouble(s[4])));
					f.setWeight(Double.parseDouble(s[5]));
					Pixel pixel = m.getXYfromLatLon(f.getGps().get_x(), f.getGps().get_y());
					f.setPix(pixel);
					this.af.add(f);
				}
				else if (s[0].contains("B")){
					Box box = new Box();
					box.setId(Integer.parseInt(s[1]));
					box.setGps1(new Point3D(Double.parseDouble(s[2]),Double.parseDouble(s[3]),Double.parseDouble(s[4])));
					box.setGps2(new Point3D(Double.parseDouble(s[5]),Double.parseDouble(s[6]),Double.parseDouble(s[7])));
					Pixel pixel1 = m.getXYfromLatLon(box.getGps1().get_x(), box.getGps1().get_y());
					Pixel pixel2 = m.getXYfromLatLon(box.getGps2().get_x(), box.getGps2().get_y());
					box.setPix1(pixel1);
					box.setPix2(pixel2);
					this.boxes.add(box);
					
				}
				else if (s[0].contains("G")){
					Ghost ghost = new Ghost();
					ghost.setId(Integer.parseInt(s[1]));
					ghost.setGps(new Point3D(Double.parseDouble(s[2]),Double.parseDouble(s[3]),Double.parseDouble(s[4])));
					Pixel pixel = m.getXYfromLatLon(ghost.getGps().get_x(), ghost.getGps().get_y());
					ghost.setPix(pixel);
					this.ghosts.add(ghost);

				}
				else if (s[0].contains("M")){
					myplayer.setId(Integer.parseInt(s[1]));
					myplayer.setGps(new Point3D(Double.parseDouble(s[2]),Double.parseDouble(s[3]),Double.parseDouble(s[4])));
					Pixel pixel = m.getXYfromLatLon(myplayer.getGps().get_x(), myplayer.getGps().get_y());
					myplayer.setPix(pixel);
					myplayer.setSpeed(Double.parseDouble(s[5]));
				}
			}	
	}

	public Myplayer getMyplayer() {
		return myplayer;
	}
	public void setMyplayer(Myplayer myplayer) {
		this.myplayer = myplayer;
	}
	
	public ArrayList<Box> getBoxes() {
		return boxes;
	}
	public void setBoxes(ArrayList<Box> boxes) {
		this.boxes = boxes;
	}
	public ArrayList<Ghost> getGhosts() {
		return ghosts;
	}
	public void setGhosts(ArrayList<Ghost> ghosts) {
		this.ghosts = ghosts;
	}
	public ArrayList<Pacman> getAp() {
		return ap;
	}

	public void setAp(ArrayList<Pacman> ap) {
		this.ap = ap;
	}

	public ArrayList<Fruit> getAf() {
		return af;
	}

	public void setAf(ArrayList<Fruit> af) {
		this.af = af;
	}
	/**
	 * Constructor
	 * @param ap array list of Pacman
	 * @param af array list of Fruit
	 * @param boxes array list of Box
	 * @param ghosts array list of Ghost
	 * @param myplayer a new main player
	 * 
	 */

	public Game(Myplayer myplayer ,ArrayList<Pacman> ap, ArrayList<Fruit> af, ArrayList<Box> boxes, ArrayList<Ghost> ghosts) {
		this.myplayer = myplayer;
		this.ap = ap;
		this.af = af;
		this.boxes = boxes;
		this.ghosts = ghosts;
	}
	@Override
	public String toString() {
		return "Game [myplayer=" + myplayer + ", ap=" + ap + ", af=" + af + ", boxes=" + boxes + ", ghosts=" + ghosts
				+ "]";
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
	
}