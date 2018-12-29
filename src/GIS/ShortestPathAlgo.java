package GIS;

import java.util.ArrayList;
import Coords.MyCoords;
import Geom.Pixel;
/**
 * 
 * @author Ortal ,Tomer and Avichay
 * @note this class response to represent 
 * the shortest algorithm of each pacman's path.
 *
 */
public class ShortestPathAlgo {
	private Game game;

	public ShortestPathAlgo() {
		this.game = new Game();
	}

	public Game getGame() {
		return game;
	}
/**
 * 
 * @param p is array list of pacmans
 * @param f is array list of fruits
 * @return linePath the line who that part of the whole path of each pacman
 * also linePath is the way to figure moves of each pacman on the way to a fruit
 */
	public Line shortesPath(ArrayList<Pacman> p , ArrayList<Fruit> f) {
		//time is array list of distance between each pacman and it's close fruit
		ArrayList<Double> time = new ArrayList<>(p.size());
		ArrayList<Integer> indexPac = new ArrayList<>(p.size());
		ArrayList<Integer> indexFruit = new ArrayList<>(p.size());
		MyCoords mc = new MyCoords();
		
		for (int i = 0; i < p.size() && (f.size() !=0) ; i++) {
			double minTime = (mc.distance3d(p.get(i).getGps(), 
					f.get(0).getGps()) -p.get(i).getRadius())/p.get(i).getSpeed();
			
			int minIndex =0;
			for (int j = 1; j < f.size(); j++) {

				if((mc.distance3d(p.get(i).getGps(), 
						f.get(j).getGps())-p.get(i).getRadius())/p.get(i).getSpeed() < minTime) {
					minTime =(mc.distance3d(p.get(i).getGps(),
							f.get(j).getGps())-p.get(i).getRadius())/p.get(i).getSpeed();
					minIndex =j;
				}
			}
			time.add(minTime);
			indexFruit.add(minIndex);
			indexPac.add(i);	
		}
		//looking for the minimum time between pacman and fruit
		double minimumTime =time.get(0);
		for (int i = 1; i < time.size(); i++) {
			if(minimumTime > time.get(i)) {
				minimumTime = time.get(i);
			}
		}
		//looking for the index of the minimum time between pacman and fruit
		int minIndex =0;
		for (int i = 0; i < time.size(); i++) {
			if(time.get(i) == minimumTime) {
				minIndex =i;
			}
		}
		Pixel ansP = p.get(minIndex).getPix();
		Pixel ansF = f.get(indexFruit.get(minIndex)).getPix();
		f.get(indexFruit.get(minIndex)).setTimeMet(minimumTime);	
//		f.get(indexFruit.get(minIndex)).setCurrentTime(new DateTime().toString());
		p.get(minIndex).getPath().getPath().add(f.get(indexFruit.get(minIndex)));
		Line linePath = new Line();
		//pixel start and pixel end for the line, keeps the proportion
		linePath.setStart(ansP);
		linePath.setEnd(ansF);
		p.get(minIndex).setPix(f.get(indexFruit.get(minIndex)).getPix());
		p.get(minIndex).setGps(f.get(indexFruit.get(minIndex)).getGps());
		p.get(minIndex).setScore(f.get(indexFruit.get(minIndex)).getWeight() + p.get(minIndex).getScore());
		f.remove(f.get(indexFruit.get(minIndex)));
		
		return linePath;
	}

}