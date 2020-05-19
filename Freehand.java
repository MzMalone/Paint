import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Freehand extends Shapes
{
	private int[] xPoints;
	private int[] yPoints;
	private Color color;
	private int xmin, xmax, ymin, ymax;	
	private boolean select = false;	// flags selection

	//Constructor
	public Freehand(ArrayList<Integer> x, ArrayList<Integer> y)
	{		
		xPoints = new int[x.size()];  
		yPoints = new int[y.size()];  
		// insert points into an array to be used by polyline
		for(int i = 0; i < x.size(); i++) {
			xPoints[i] = x.get(i);
			yPoints[i] = y.get(i);
		}
		// find minimums and maximums for bounding box test
		// sort arrays first: minimum values will be first and maximum at end of array
		Collections.sort(x);
		Collections.sort(y);
		xmin = x.get(0);
		ymin = y.get(0);
		xmax = x.get(xPoints.length-1);
		ymax = y.get(yPoints.length-1);
	}
	// set color
	public void setColor(Color c) {
		color = c;
	}
	// get color
	public Color getColor() {
		return color;
	}
	
	// draw freehand with polyline
	@Override
	public void draw(Graphics g) {
		color = getColor();
		g.setColor(color);
		select = getShape();
		// if freehand figure is selected
		if(select == true) {
			g.setColor(Color.RED);
			g.drawPolyline(xPoints, yPoints, xPoints.length);
			g.setColor(color);
		}
		else {	
			g.drawPolyline(xPoints, yPoints, xPoints.length);
		}
	}
	
	// freehand bounding box
	@Override 
	public boolean boundingBox(int x, int y) {
		if(x > xmin && x < xmax && y > ymin && y < ymax) {
			return true;
		}
		else {
			return false;
		}
	}
}

