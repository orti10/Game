//package Algorithim;
//
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//import GIS.Box;
//import GIS.Fruit;
//import GIS.Game;
//import GIS.Ghost;
//import GIS.Myplayer;
//import GIS.Pacman;
//import Geom.Pixel;
//import Robot.Play;
//import graph.Point3D;
//
//public class ParseGame {
//
//	public static synchronized void parseBoard(Play play,Game game) {
//		synchronized (game) {
//			game=new Game();
//			ArrayList<String> board_data = play.getBoard();
//			for(int i=0;i<board_data.size();i++) {
//
//				String record = board_data.get(i);
//				StringTokenizer fields = new StringTokenizer(record, ","); 
//				char type = fields.nextToken().charAt(0);
//
////				int id = Integer.parseInt( fields.nextToken() );			
////				double lat = Double.parseDouble( fields.nextToken() );
////				double lon = Double.parseDouble( fields.nextToken() );
////				double alt = Double.parseDouble( fields.nextToken() );
////				Point3D location = new Point3D(lat,lon,alt);
//
//			//	double speed_weight_lat = Double.parseDouble( fields.nextToken() );
//				double radius_lon = 0;
//				if(fields.hasMoreTokens())//fruit case
//					radius_lon = Double.parseDouble( fields.nextToken() );
//				Myplayer my = new Myplayer();
//				Fruit fruit = new Fruit();
//				Ghost ghost = new Ghost();
//				Pacman pacman = new Pacman();
//				Box box = new Box();
//
//				switch (type) {
//
//				case 'M':
//					my = new Myplayer(my.getType(),my.getId(), my.getGps(),my.getSpeed(),my.getRadius(),my.getPix(),my.getScore());
//					game.setMyplayer(my);
//					break;
//
//				case 'F':
//					fruit = new Fruit(fruit.getType(),fruit.getId(), fruit.getGps(),fruit.getWeight(),fruit.getPix(),fruit.getCurrentTime(),fruit.getTimeMet());
//					game.getAf().add(fruit);
//					break;
//
//				case 'G':
//					ghost = new Ghost(ghost.getType(),ghost.getId(),ghost.getGps(),ghost.getPix(),ghost.getSpeed(),ghost.getRadius());
//					game.getGhosts().add(ghost);
//					break;
//
//				case 'P':
//					pacman = new Pacman(pacman.getType(),pacman.getId(),pacman.getGps(),pacman.getSpeed()
//							,pacman.getRadius(),pacman.getPix(),pacman.getScore());
//					game.getAp().add(pacman);
//					break;
//
//				case 'B':
//					box = new Box(box.getType(),box.getId(),box.getGps1(),box.getGps2(),box.getPix1(),box.getPix2());
//					game.getBoxes().add(box);
//					break;
//
//				default:
//					break;
//				}
//
//			}
//		}
//	}
//
//
//	public static synchronized void parseStatistics(Play play,Game game) {
//		String info = play.getStatistics();			
//		StringTokenizer fields = new StringTokenizer(info, ","); 
//
//		//skip date
//		fields.nextToken();
//
//		//time
//		String tamp = fields.nextToken().split(":")[1];//take after':'	
//		int time = (int)Double.parseDouble(tamp) / 100;
//
//		//score
//		tamp = fields.nextToken().split(":")[1];//take after':'	
//		int score = (int)Double.parseDouble(tamp);
//
//		//skip Time left
//		fields.nextToken();
//
//		//kill by ghosts(kbg)
//		tamp = fields.nextToken().split(":")[1];//take after':'	
//		int kbg = Integer.parseInt(tamp);
//
//		//out of box(oob)
//		tamp = fields.nextToken().split(":")[1];//take after':'	
//		int oob = Integer.parseInt(tamp);
//	}
//}