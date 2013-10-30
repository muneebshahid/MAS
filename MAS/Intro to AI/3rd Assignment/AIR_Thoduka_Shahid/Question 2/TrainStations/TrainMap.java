import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TrainMap {
	
	private Dimension size; // size of map
	private ArrayList<Point> stations; // list of stations
	// Point - start station, ArrayList<Point> - list of connecting stations
	private HashMap<Point, ArrayList<Point> > links;
	// stores success probability of connections - not used currently
	private HashMap<Point, ArrayList<Double>> success_prob;
		
	public TrainMap(int x, int y) {
		size = new Dimension(x,y);
		stations = new ArrayList<Point>(100);
		links = new HashMap<Point, ArrayList<Point>>(100);
		success_prob = new HashMap<Point, ArrayList<Double>>(100);
	}
	// only add a station if it's within the map and doesn't already exist
	public boolean addStation(Point station) {
		if(station.x > size.width || station.y > size.height
			|| station.x < 0 || station.y < 0) {
			return false;
		}
		if(stations.contains(station)) {
			return false;
		}
		stations.add(station);
		return true;
	}
	
	public Point getStation(int i) {
		if(i < stations.size()) {
			return stations.get(i);
		}
		return null;
	}
	
	public int getNumStations() {
		return stations.size();
	}
	
	public boolean addLink(Point start, Point stop, double probability) {
		// if either station doesn't exist, don't add a link
		if (!stations.contains(start) || !stations.contains(stop)) {
			return false;
		}
		// don't add connections to same station
		if (start.equals(stop)) {
			return false;
		}
		ArrayList<Point> connections;

		// otherwise add the link from start -> stop
		if ( (connections = links.get(start)) != null) {
			if(!connections.contains(stop)) {
				connections.add(stop);
				
				// not used currently
				ArrayList<Double> probs = success_prob.get(start);
				probs.add(probability);
			}
		} else { // if no connections exist from "start", create new ArrayList
			ArrayList<Point> temp = new ArrayList<Point>();
			temp.add(stop);
			links.put(start, temp);
			
			// not used currently
			ArrayList<Double> tempprob = new ArrayList<Double>();
			tempprob.add(probability);
			success_prob.put(start, tempprob);
		}
		// also add a link from stop -> start
		if ( (connections = links.get(stop)) != null) {
			if (!connections.contains(start)) {
				connections.add(start);
				
				// not used currently
				ArrayList<Double> probs = success_prob.get(stop);
				probs.add(probability);
			}
		} else {// if no connections exist from "stop", create new ArrayList
			ArrayList<Point> temp = new ArrayList<Point>();
			temp.add(start);
			links.put(stop, temp);
			
			// not used currently
			ArrayList<Double> tempprob = new ArrayList<Double>();
			tempprob.add(probability);
			success_prob.put(stop, tempprob);
		}
		return true;
	}
	
	// get all connections from station
	public ArrayList<Point> getLinks(Point station) {
		return links.get(station);
	}
	
	public ArrayList<Double> getProbabilities(Point station) {
		return success_prob.get(station);
	}
	
	public void printLinks() {
		for (Map.Entry<Point, ArrayList<Point>> entry: links.entrySet()) {
			 Point station = entry.getKey();
			 ArrayList<Point> connections = entry.getValue();
			 System.out.println("Station: " + station + " connects to:");
			 for (Point p : connections) {
				 System.out.println(p);
			 }			 
		}
	}
	
	public void addAllLinks(Point startPoint, double probability) {
		for (Point station : stations) {
			addLink(startPoint, station, probability);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TrainMap tm = new TrainMap(5,4);
		tm.addStation(new Point(2,2));		
		tm.addStation(new Point(2,1));
		tm.addLink(tm.getStation(0), tm.getStation(1), 1);
		tm.printLinks();
	}

}
