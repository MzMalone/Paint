// Source: https://gist.github.com/spencerdcarlson/3785744 by spencerdcarlson

import java.awt.*;

public class Shapes {

	private Color color;
	private boolean fill = false;
	private boolean select = false;	
	
	// constructor 
	Shapes(){
		
	}

	// set color
	public void setColor(Color c) {
		color = c;
	}
	
	// get color 
	public Color getColor() {
		return color;
	}

	// set fills 
	public void set_Fill(boolean c) {
		fill = c; 
	}
	
	// get fills
	public boolean get_fill() {
		return fill;
	}
	
	// set selected Shape 
	public void setShape(boolean s) {
		select = s;
	}
	
	// get selected Shape 
	public boolean getShape() {
		return select;
	}
	
	// draw 
	public void draw(Graphics g) {
		
	}
	// bounding box 
	public boolean boundingBox(int x, int y) {
		
		return false;
	}
		
	
}
