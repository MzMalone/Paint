import java.awt.*;
import java.util.ArrayList;

public class Rectangle extends Shapes
{
	private int x_init, y_init, x_end, y_end;	// coordinates that user selected 
	private Color color;
	private boolean fill = false;	// flags fill
	private boolean select = false;	// flags selection
	private int xmin, xmax, ymin, ymax;	

	//Constructor
	public Rectangle(ArrayList<Integer> x, ArrayList<Integer> y, Color c)
	{				
		color = c;
		// declare variables 
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
	
	// set Rectangle color
	public void setColor(Color c) {
		color = c;
	}
	
	// get Rectangle color 
	public Color getColor() {
		return color;
	}

	// draw rectangle
	@Override
	public void draw(Graphics g) {
		color = getColor();
		g.setColor(color);
		fill = get_fill();
		select = getShape();
		
		// top left to bottom right 
		if(x_init < x_end && y_init < y_end) {
			if(fill == true) {
				g.fillRect(xmin, ymin, xmax-xmin, ymax-ymin);
			}
			else {
				g.drawRect(xmin, ymin, xmax-xmin, ymax-ymin);
			}
			if(select == true) {
				g.setColor(Color.red);
				g.drawRect(xmin, ymin, xmax-xmin, ymax-ymin);
				g.setColor(color);
			}
		}
		// bottom right to top left
		else if(x_init > x_end && y_init > y_end) {
			if(fill == true) {
				g.fillRect(xmin, ymin, xmax-xmin, ymax-ymin);
			} 
			else {
				g.drawRect(xmin, ymin, xmax-xmin, ymax-ymin);
			}
			if(select == true) {
				g.setColor(Color.red);
				g.drawRect(xmin, ymin, xmax-xmin, ymax-ymin);
				g.setColor(color);
			}
		}
		// bottom left to top right 
		else if(x_init < x_end && y_init > y_end) {
			if(fill == true) {
				g.fillRect(xmin, ymin, xmax-xmin, ymax-ymin);
			}
			else {
				g.drawRect(xmin, ymin, xmax-xmin, ymax-ymin);
			}
			if(select == true) {
				g.setColor(Color.red);
				g.drawRect(xmin, ymin, xmax-xmin, ymax-ymin);
				g.setColor(color);
			}
		}
		// top right to bottom left
		else {
			if(fill == true) {
				g.fillRect(xmin, ymin, xmax-xmin, ymax-ymin);
			} 
			else {
				g.drawRect(xmin, ymin, xmax-xmin, ymax-ymin);
			}
			if(select == true) {
				g.setColor(Color.red);
				g.drawRect(xmin, ymin, xmax-xmin, ymax-ymin);
				g.setColor(color);
			}
		}
		
	}

	// bounding box test for rectangle
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

