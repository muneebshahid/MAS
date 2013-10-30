import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JFrame;


public class UI {
	
	private JFrame window;
	private MapUI mapUI;
	public boolean pauseFlag;
	private final int STATION_COUNT = 3500;
	// approximate maximum number of connections per station
	private final int CONNECTIONS_PER_STATION = 20;
	// set to true if the search process should be animated
	private final boolean animateUI = true;
	// update frequency of animation in milliseconds
	private final int update_freq = 50;
	
	public UI() {
		window = new JFrame("Train Map");
		window.setSize(1100,600);
		pauseFlag = true;
		mapUI = new MapUI(this, 1024,512);
		window.add(mapUI);		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	public void run() {
		MapGenerator mg = new MapGenerator();
		// create a map of size 200x100
		TrainMap tm = mg.getMap(200,100,STATION_COUNT,CONNECTIONS_PER_STATION);
		
		// used for UI display
		double scale = 5.0;
		double offset = 40.0;
		mapUI.stations.clear();
		mapUI.links.clear();
		
		
		// draw start point and end point 
		mapUI.start.x = tm.getStation(0).x * scale + offset;
		mapUI.start.y = tm.getStation(0).y * scale + offset;
		
//		Point startPoint = mg.getRandomPoint(200, 100);
//		mapUI.start.x = startPoint.x * scale + offset;
//		mapUI.start.y = startPoint.y * scale + offset;
		
//		tm.addStation(startPoint);
//		tm.addAllLinks(startPoint,5.0);
		
		mapUI.stop.x = tm.getStation(1).x * scale + offset;
		mapUI.stop.y = tm.getStation(1).y * scale + offset;

		
		
		int numstations = tm.getNumStations();				
		synchronized (mapUI.stations) {
			// draw all stations and connections
			for (int i=0;i<numstations;i++) {
				Point station = tm.getStation(i);
				Point2D.Double point = new Point2D.Double(station.x * scale + offset, station.y * scale + offset);			
				mapUI.stations.add(point);
				ArrayList<Point> links = tm.getLinks(station);
				if (links == null)
					continue;
				synchronized(mapUI.links) {
					for ( Point toStation : links) {				
						Point2D.Double toPoint = new Point2D.Double(toStation.x * scale + offset, toStation.y * scale + offset);
						Line2D.Double line = new Line2D.Double(point, toPoint);
						mapUI.links.add(line);
					} 		
				}
			}
		}
		
		
		// Run A* algorithm to find shortest path between first and second station
		AStar astar = new AStar(this, mapUI, scale, offset);
		ArrayList<Point> path = astar.run(tm, tm.getStation(0), tm.getStation(1), animateUI, update_freq);
		
		// draw shortest path
		synchronized (mapUI.path) {
			mapUI.path.clear();
			for ( int i=1;i<path.size(); i++) {
				Point cur = path.get(i-1);
				Point2D.Double curPoint = new Point2D.Double(cur.x * scale + offset, cur.y * scale + offset);
				Point next= path.get(i);
				Point2D.Double toPoint = new Point2D.Double(next.x * scale + offset, next.y * scale + offset);
				Line2D.Double line = new Line2D.Double(curPoint, toPoint);
				mapUI.path.add(line);
			}
		}
	}
	
	public static void main(String[] args) {
		UI u = new UI();
		u.run();
	}

}
