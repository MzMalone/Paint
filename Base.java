import java.awt.*;

public class Base {

	private Color color;
	private boolean fill = false;
	private boolean select = false;	
	// constructor 
	Base(){
		
	}

	// set color
	public void set_Color(Color c) {
		color = c;
	}
	
	// get color 
	public Color get_Color() {
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
	
	// set select 
	public void set_select(boolean s) {
		select = s;
	}
	
	// get select 
	public boolean get_Select() {
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
