import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;


public class AStar {
	
	private UI ui;
	private MapUI panel;
	private double offset;
	private double scale;
	
	
	public AStar(UI ui, MapUI panel, double scale, double offset) {
		this.ui = ui;
		this.panel = panel;
		this.scale = scale;
		this.offset = offset;
	}
	
	public ArrayList<Point> run(TrainMap map, Point startPoint, Point endPoint, boolean displayUI, int update_freq) {
		// this contains the current path, and eventually the shortest path
		ArrayList<Point> p = new ArrayList<Point>();
		// nodes that are yet to be explored
		LinkedList<Node> openNodes = new LinkedList<Node>();
		// stations that have already been visited (i.e. added to openNodes)
		HashSet<Point> visitedPoints = new HashSet<Point>(); 
		
		// start node
		Node start = new Node();
		start.p = startPoint;
		start.path_cost = 0.0;
		// heuristic used here is euclidean distance
		// this is admissible since it is less than or equal
		// to the actual cost
		start.goal_cost = heuristic(startPoint, endPoint);
		start.previous = null;
		
		openNodes.add(start);
		visitedPoints.add(startPoint);
		
		while (!openNodes.isEmpty()) {
			double bestCost = Double.MAX_VALUE;
			Node bestNode = null;
			// find the node with the best f(n) = g(n) + h(n)
			for (Node node : openNodes) {
				if (node.goal_cost + node.path_cost < bestCost) {
					bestCost = node.goal_cost + node.path_cost;
					bestNode = node;					
				}
			}
			openNodes.remove(bestNode);
			
			// if we've found our goal node then stop
			// and return shortest path
			if (bestNode.p.equals(endPoint)) {
				p.clear();
				Node currentNode = bestNode;
				while(currentNode.previous != null) {
					p.add(currentNode.p);
					currentNode = currentNode.previous;
				}
				p.add(currentNode.p);
				Collections.reverse(p);
				return p;
			}
			
			// only if we want show animation of search process
			if (displayUI) {
				p.clear();
				Node currentNode = bestNode;
				while(currentNode.previous != null) {
					p.add(currentNode.p);
					currentNode = currentNode.previous;
				}
				p.add(currentNode.p);
				Collections.reverse(p);				
				displayCurrentPath(p);
			}
			
			// get all connecting stations of current station
			ArrayList<Point> children = map.getLinks(bestNode.p);
			for (Point child : children) {				
				// if station has already been visited, don't add it
				if (visitedPoints.contains(child)) {
					continue;
				}
				// add it to openNodes
				visitedPoints.add(child);
				Node childNode = new Node();
				childNode.p = child;
				childNode.path_cost = bestNode.path_cost + euclidean_distance(bestNode.p, childNode.p);
				childNode.goal_cost = heuristic(childNode.p, endPoint);
				childNode.previous = bestNode;				
				openNodes.add(childNode);
			}
			// update frequency for animation
			if(displayUI) {
				try {
					Thread.sleep(update_freq);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return p;
	}
	
	// currently heuristic is defined as euclidean distance
	private double heuristic(Point startPoint, Point endPoint) {
		return euclidean_distance(startPoint, endPoint);
	}
	
	private double euclidean_distance(Point startPoint, Point endPoint) {
		return Math.sqrt( Math.pow(startPoint.x-endPoint.x, 2) + Math.pow(startPoint.y-endPoint.y,2) );
	}
	
	private void displayCurrentPath(ArrayList<Point> path) {
		synchronized(panel.path) {			
			panel.path.clear();
			for ( int i=1;i<path.size(); i++) {
				Point cur = path.get(i-1);
				Point2D.Double curPoint = new Point2D.Double(cur.x * scale + offset, cur.y * scale + offset);
				Point next= path.get(i);
				Point2D.Double toPoint = new Point2D.Double(next.x * scale + offset, next.y * scale + offset);			
				Line2D.Double line = new Line2D.Double(curPoint, toPoint);				
				panel.path.add(line);				
			}			
		}
	}
	public static void main(String[] args) {
		

	}

}
