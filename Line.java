import java.awt.*;
import java.util.ArrayList;

public class Line extends Shapes
{
	private int x_init, y_init, x_end, y_end;	// coordinates that user selected 
	private Color color;
	private int xmin, xmax, ymin, ymax;	
	private boolean select = false;	// flags selection


	//Constructor
	public Line(ArrayList<Integer> x, ArrayList<Integer> y)
	{		
		x_init = x.get(0);
		y_init = y.get(0);
		x_end = x.get(1);
		y_end = y.get(1);
		
		// find minimums and maximums 
		xmin = Math.min(x_init, x_end);
		ymin = Math.min(y_init, y_end);
		xmax = Math.max(x_init, x_end);
		ymax = Math.max(y_init, y_end);
	}
	
	// draw line 
	@Override
	public void draw(Graphics g) {
		color = getColor();
		g.setColor(color);
		select = getShape();
		g.setColor(color);
		// if selection is true 
		if(select == true) {
			g.setColor(Color.RED);
			g.drawLine(x_init, y_init, x_end, y_end);
			g.setColor(color);
		}
		else {	
			g.drawLine(x_init, y_init, x_end, y_end);
		}
	}
	
	// bounding box for line 
	@Override 
	public boolean boundingBox(int x, int y) {
		if(x > xmin && x < xmax && y > ymin && y < ymax) {
			return true;
		}
		else {
			return false;
		}
	}

	// set color
	public void setColor(Color c) {
		color = c;
	}
	
	// get color 
	public Color getColor() {
		return color;
	}
}

