package GIS;

import java.util.ArrayList;
import Coords.MyCoords;
/**
 * 
 * @author Ortal, Tomer and Avichay
 * @note This class represent the length path
 * 
 */
public class Path {
	private ArrayList <Fruit> path;

	public Path () {
		this.path = new ArrayList<Fruit>();
	}

	public Path(ArrayList<Fruit> path0) {
		this.path=path0;
	}
/**
 * calculating the length of the path in coordinates
 * @return length 
 */
	public double pathLength() {
		
		double length=0;
		MyCoords mc = new MyCoords();
		for (int i = 0; i < path.size()-1 ; i++) {
			length = length + mc.distance3d(path.get(i).getGps(), path.get(i+1).getGps()) ;
		}
		return length;		
	}
	
	@Override
	public String toString() {
		return "Path [points=" + path + "]";
	}

	public ArrayList<Fruit> getPath() {
		return path;
	}

	public void setPath(ArrayList<Fruit> path) {
		this.path = path;
	}
}