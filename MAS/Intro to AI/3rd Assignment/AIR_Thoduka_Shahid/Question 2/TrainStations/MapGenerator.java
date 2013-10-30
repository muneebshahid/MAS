import java.awt.Point;
import java.util.Random;

public class MapGenerator {
	Random random;
	public MapGenerator() {
		random = new Random();
	}
	// x and y are the map size
	// num_stations = number of stations
	// max_connections = approximate number for maximum number of connections from one station
	public TrainMap getMap(int x, int y, int num_stations, int max_connections) {
		TrainMap tm = new TrainMap(x,y);
		// add stations at random points
		while (tm.getNumStations() < num_stations) {
			tm.addStation(new Point(random.nextInt(x), random.nextInt(y)));
		}
		
		// for each station
		for (int i = 0; i < tm.getNumStations(); i++) {
			Point start = tm.getStation(i);
			// random number of connections maxed at max_connections/2
			int num_conn = random.nextInt(max_connections/2) + 1;
			// add link to random stations
			for (int j = 0; j < num_conn; j++) {
				// this can change later to random probabilities
				// right now set to 1.0
				tm.addLink(start, tm.getStation(random.nextInt(tm.getNumStations())), 1.0);
			}
		}
		return tm;
	}
	
	public Point getRandomPoint(int x, int y) {
		return new Point(random.nextInt(x), random.nextInt(y));
	}
	public static void main(String[] args) {
		MapGenerator mg = new MapGenerator();
		TrainMap tm = mg.getMap(10, 10, 20, 5);
		tm.printLinks();
	}

}
