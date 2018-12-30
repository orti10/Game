package GIS;

import Geom.Pixel;
import Geom.Point3D;

public class Myplayer {
	
	private Point3D gps;
	private double speed;
	private double radius;
	private Pixel pix;
	private int score;
	
	public Myplayer() {
		this.gps = new Point3D();
		this.speed = 0;
		this.radius = 0;
		this.pix = new Pixel();
		this.score = 0;
	}

	public Myplayer(Point3D gps, double speed, double radius, Pixel pix, int score) {
		super();
		this.gps = gps;
		this.speed = speed;
		this.radius = radius;
		this.pix = pix;
		this.score = score;
	}

	@Override
	public String toString() {
		return "Myplayer [gps=" + gps + ", speed=" + speed + ", radius=" + radius + ", pix=" + pix + ", score=" + score
				+ "]";
	}

	public Point3D getGps() {
		return gps;
	}

	public void setGps(Point3D gps) {
		this.gps = gps;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Pixel getPix() {
		return pix;
	}

	public void setPix(Pixel pix) {
		this.pix = pix;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
