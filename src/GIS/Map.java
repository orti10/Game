package GIS;

import java.io.File;
import Coords.MyCoords;
import Geom.Pixel;
import Geom.Point3D;


/**
 * @author Ortal, Tomer and Avichay
 * @note this class represent the basic calculation of coordinate on point3D and Pixels
 * convert each other and using also meters and distance.
 * to make sure the data will be represent real and clear.
 * 
 * using help from : 
 * //https://stackoverflow.com/questions/14329691/convert-latitude-longitude-point-to-a-pixels-x-y-on-mercator-projection?rq=1
	//https://stackoverflow.com/questions/35299786/draw-circle-on-jpanel-after-mouse-click/35300018
 */
public class Map {
	private File image = new File("C:\\temp\\Ariel1.png");
	private int mapWidth = 1433;
	private int mapHeight = 642;
	private double west = 35.202574; 
	private double north = 32.106046;
	private double east = 35.212405;
	private double south = 32.101858;
	/**
	 * @param latitude and longitude
	 * function to convert from coordinate to pixel
	 * @return pixel
	 */
	public Pixel getXYfromLatLon(double latitude, double longitude) {
		
		double y = (mapHeight*latitude - mapHeight*north)/(south-north);
		double x = (mapWidth*longitude - mapWidth*west)/(east-west);
		return new Pixel(x,y);
	}

	/**
	 * @param latitude and longitude
	 * function to convert from pixel to coordinate
	 * @return coordinate
	 */
	public Point3D getLatLonfromXY(Pixel p) {
		 
		double lat =((p.getY()*(south-north))/mapHeight)+north;
		double lon  = ((p.getX()*(east-west))/mapWidth)+west;
		return new Point3D(lat,lon);
	}

	/**
	 * @param latitude and longitude
	 * function to calculate angle between two pixels
	 * @return angle
	 */
	public double anglefrompixel(Pixel pixel0 ,Pixel pixel1) {
		Map m = new Map();
		Point3D p = m.getLatLonfromXY(pixel0) ;
		Point3D p2 = m.getLatLonfromXY(pixel1);
		MyCoords coord = new MyCoords();
		double angle = coord.azimuth_elevation_dist(p, p2)[0];
		return angle;
	}

	/**
	 * @param latitude and longitude
	 * function to calculate distance in meters between two pixels
	 * @return meters
	 */
	public double PixeldistanceInMeters(Pixel pixel0, Pixel pixel1) {
		Map m = new Map();
		Point3D p = m.getLatLonfromXY(pixel0) ;
		Point3D p2 = m.getLatLonfromXY(pixel1);
		MyCoords coord = new MyCoords();
		double  Pixeldistance = coord.distance3d(p, p2);
		return Pixeldistance;
	}
	
}