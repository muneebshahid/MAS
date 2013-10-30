import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MapUI extends JPanel implements Runnable
{
   int xsize,ysize;
   public ArrayList<Point2D> stations; 
   public ArrayList<Line2D> links; // connections between stations
   public ArrayList<Line2D> path; // shortest path
   public Point2D.Double start; // start station
   public Point2D.Double stop; // stop station
   private Dimension size;
   private BasicStroke basic_stroke;
   Thread t;
   UI mainWindow;
   MapUI(UI m, int x, int y)
   {
      mainWindow = m;
      xsize = x;
      ysize = y;
      stations = new ArrayList<Point2D>();
      links = new ArrayList<Line2D>();
      path = new ArrayList<Line2D>();
      start = new Point2D.Double();
      stop = new Point2D.Double();
      basic_stroke = new BasicStroke(5);
      t = new Thread(this,"mappanel");
      t.start();
   }
   
   public void run()
   {
      while(true)
      {    	 
    	 try
         {
            Thread.sleep(10);
            synchronized(this){
               while(mainWindow.pauseFlag){
                  wait(10);
                  repaint();
               }
            }
         }catch(InterruptedException ie){}
    	 repaint();
      }
   }
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      size = this.getSize();
      drawStations(g);
   }

   private void drawStations(Graphics g) {
	   Graphics2D g2 = (Graphics2D)g;
	   synchronized( links ) {
		   g2.setColor(Color.GRAY);
		   for (Line2D line : links) {
			   g2.draw(line);
		   }
	   }
	   
	   synchronized (stations) {
		   g2.setColor(Color.RED);
		   for (Point2D station : stations) {		   
			   Ellipse2D ellipse = new Ellipse2D.Double(station.getX()-2,station.getY()-2,4,4);
			   g2.draw(ellipse);
			   g2.fill(ellipse);
		   }
	   }
	  
	   
	   synchronized ( path ) {
		   g2.setColor(Color.BLUE);
		   Stroke s = g2.getStroke();
		   g2.setStroke(basic_stroke);
		   for (Line2D line : path) {
			   g2.draw(line);
		   }
		   g2.setStroke(s);
	   }
	   g2.setColor(Color.BLACK);
	   Ellipse2D ellipse = new Ellipse2D.Double(start.x-4,start.y-4,8,8);
	   g2.draw(ellipse);
	   g2.fill(ellipse);
	   g2.setColor(Color.GREEN);
	   ellipse = new Ellipse2D.Double(stop.x-4,stop.y-4,8,8);
	   g2.draw(ellipse);
	   g2.fill(ellipse);
   }
}
