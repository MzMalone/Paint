import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Polygon extends Base
{
	// arrays which allow polygon to be draw since polygon take int arrays only
	private int[] xPoints;
	private int[] yPoints;
	
	private Color color;
	private boolean fill = false;	// flags fill
	private boolean select = false;	// flags selection
	private int xmin, xmax, ymin, ymax;	
	
	//Constructor
	public Polygon(ArrayList<Integer> x, ArrayList<Integer> y)
	{		
		xPoints = new int[x.size()];  
		yPoints = new int[y.size()];  
		// fill the arrays with the points to be drawn 
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
	
	// draw polygon
	@Override
	public void draw(Graphics g) {
		color = get_Color();
		g.setColor(color);
		fill = get_fill();
		select = get_Select();
		
		if(fill == true) {
			g.fillPolygon(xPoints, yPoints, xPoints.length);
		}
		else {
			g.drawPolygon(xPoints, yPoints, xPoints.length);
		}
		if(select == true) {
			g.setColor(Color.red);
			g.drawPolygon(xPoints, yPoints, xPoints.length);
			g.setColor(color);
		}
	}
	
	// bounding box test 
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

