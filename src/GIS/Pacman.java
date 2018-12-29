package GIS;

import Geom.Pixel;
import Geom.Point3D;
import java.awt.Color;
/**
 * 
 * @author Ortal, Tomer and Avichay
 * @note This class represent a pacman with getter and setters data.
 * Pacman is YELLOW on the frame.
 */
public class Pacman {
	private String type;
	private int id;
	private Point3D gps;
	private int speed;
	private double radius;
	private Color color =Color.yellow;
	private Pixel pix;
	private Path path;
	private int score;

	public Pacman() {
		this.type ="P";
		this.id=0;
		this.gps=new Point3D();
		this.speed=1;
		this.radius=1;
		this.pix=new Pixel();
		this.path=new Path();
		this.score =0;
	}

	public Pacman(String type, int id, Point3D gps, int speed, double radius, Pixel pix, Path path, int score) {
		this.type = type;
		this.id = id;
		this.gps = gps;
		this.speed = speed;
		this.radius = radius;
		this.pix = pix;
		this.path = path;
		this.score =score;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Point3D getGps() {
		return gps;
	}

	public void setGps(Point3D gps) {
		this.gps = gps;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Color getColor() {
		return color;
	}

	public Pixel getPix() {
		return pix;
	}

	public void setPix(Pixel pix) {
		this.pix = pix;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Pacman [type=" + type + ", id=" + id + ", gps=" + gps + ", speed=" + speed + ", radius=" + radius
				+ ", color=" + color + ", pix=" + pix + ", path=" + path + ", score=" + score + "]";

	}
}