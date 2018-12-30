package GIS;

import Geom.Pixel;
import Geom.Point3D;

public class Ghost {
	
	private String type;
	private int id;
	private Point3D gps;
	private Pixel pix;

	public Ghost() {
		this.type ="G";
		this.id=0;
		this.gps=new Point3D();
		this.pix=new Pixel(); 
	}

	public Ghost(String type, int id, Point3D gps, Pixel pix) {
		this.type = type;
		this.id = id;
		this.gps = gps;
		this.pix = pix;
	}

	@Override
	public String toString() {
		return "Ghost [type=" + type + ", id=" + id + ", gps=" + gps + ", pix=" + pix + "]";
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

	public Pixel getPix() {
		return pix;
	}

	public void setPix(Pixel pix) {
		this.pix = pix;
	}
	
	
}
