package GIS;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import Geom.Point3D;
/**
 * 
 * @author Ortal, Tomer and Avichay
 * @note this class represent the game having data from two array lists
 * one of pacmans and the other of fruits
 */
public class Game implements Runnable {

	private ArrayList<Pacman> ap;
	private ArrayList<Fruit> af;

	public Game() {
		ap = new ArrayList<Pacman>();
		af = new ArrayList<Fruit>();
	}
	/**
	 * 
	 * @param csvFile
	 * @return game full of readen data
	 */
	public Game ReadCSV(String csvFile) {
		BufferedReader br = null;
		String line = "";
		Game g = new Game();

		try {
			//Reads from the csv file
			br = new BufferedReader(new FileReader(csvFile)); 
			line=br.readLine(); 

			//If the file doesn't contains one of this it is not a good file 
			if(!line.contains("Lat") || !line.contains("Lon") || !line.contains("Alt")) {   
				//do nothing
			}
			//Progressing while we have a next line to read
			while ((line = br.readLine()) != null) { 
				//Splits the line to array by the ","
				String[] s = line.split(","); 
				//if the type of the element is Pacman
				if(s[0].contains("P")) {
					Pacman pac = new Pacman();

					pac.setId(Integer.parseInt(s[1]));
					pac.setGps(new Point3D(Double.parseDouble(s[2]),Double.parseDouble(s[3]),Double.parseDouble(s[4])));
					pac.setSpeed(Integer.parseInt(s[5]));
					pac.setRadius(Double.parseDouble(s[6]));
					ap.add(pac);
				}
				//if the type of the element is Fruit
				else if (s[0].contains("F")){
					Fruit f = new Fruit();

					f.setId(Integer.parseInt(s[1]));

					f.setGps(new Point3D(Double.parseDouble(s[2]),Double.parseDouble(s[3]),Double.parseDouble(s[4])));
					f.setWeight(Integer.parseInt(s[5]));

					af.add(f);

				}
				g.af = af;
				g.ap = ap;
			}

		}

		catch (IOException er) {
			er.printStackTrace();
		}
		return g;

	}
	/**
	 * 
	 * @param location
	 * function that writes the csv file
	 */

	public void WriteCSV(String location) {
		PrintWriter pw; 

		try {
			//The destination file to the csv file we created
			pw = new PrintWriter(location ,"UTF-8");
			Iterator<Pacman> pacmans = ap.iterator();
			Iterator<Fruit> fruits = af.iterator();
			pw.append("Type,id,Lat,Lon,Alt,Speed/Weight,Radius," + ap.size() + "," + af.size() +"\n");
			int i =0;
			while(pacmans.hasNext()) {
				//running with iterator all over the Pacmans and fell the data
				Pacman temp = pacmans.next();
				pw.append(temp.getType() + "," + i+ "," +temp.getGps().x()+ "," +temp.getGps().y()+ "," 
						+temp.getGps().z()+ "," + temp.getSpeed()+ "," +temp.getRadius() + "\n");
				pw.flush();
				i++;
			}
			int j =0;
			while(fruits.hasNext()) {
				//running with iterator all over the Fruits and fell the data
				Fruit temp = fruits.next();
				pw.append(temp.getType() + "," + j+ "," +temp.getGps().x()+ "," +temp.getGps().y()+ "," 
						+temp.getGps().z()+ "," + temp.getWeight()+ "\n");
				pw.flush();
				j++;
			}

			pw.flush();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println("DONE");
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
	 * @param ap array list of Pacmans
	 * @param af array list of Fruits
	 */
	public Game(ArrayList<Pacman> ap, ArrayList<Fruit> af) {
		this.ap = ap;
		this.af = af;
	}

	@Override
	public String toString() {
		return "Game [ap=" + ap + ", af=" + af + "]";
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}