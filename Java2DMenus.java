import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JColorChooser;

public class Java2DMenus extends Frame
{
	private static final long serialVersionUID = 1L;
	private Color curColor = Color.black;
	private MenuBar menuBar;
	private Menu menu, edit;
	private MenuItem exitItem, colorItem, delete, fill;
	private CheckboxMenuItem line, rect, oval, polygon, freehand, select;
	private ArrayList<Integer> x_points, y_points;
	private boolean polyState = false, freedraw = false;
	private Shapes selected = null;
	private ArrayList<Shapes> primitives = new ArrayList<Shapes>();
	
	//Constructor
	Java2DMenus()
	{
		addWindowListener(new MyFinishWindow());
		createMenu();
		// ArrayList of x, y coordinates
		x_points = new ArrayList<>();
		y_points = new ArrayList<>();

		//Enables the closing of the window.
		addWindowListener(new MyFinishWindow());
		addMouseListener(
				new MouseAdapter()
				{
					public void mousePressed(MouseEvent evt)
					{						
						// add the points to array list				
						x_points.add(evt.getX());
						y_points.add(evt.getY());

						// Line mode
						if(x_points.size() == 2 && line.getState() == true) {
							Line l = new Line(x_points, y_points);
							l.setColor(curColor);
							primitives.add(l);
							x_points.clear();
							y_points.clear();
						}
						// Rectangle mode
						else if(x_points.size() == 2 && rect.getState() == true) {
							Rectangle r = new Rectangle(x_points, y_points, curColor);
							r.setColor(curColor);
							primitives.add(r);
							x_points.clear();
							y_points.clear();
						}
						// Oval mode
						else if(x_points.size() == 2 && oval.getState() == true) {
							Oval o = new Oval(x_points, y_points);
							o.setColor(curColor);
							primitives.add(o);
							x_points.clear();
							y_points.clear();
						}
						// polygon mode
						else if(polygon.getState() == true) {
							polyState = true;	
							if(evt.getClickCount() == 2) {
								Polygon p = new Polygon(x_points, y_points);
								p.setPolyColor(curColor);
								primitives.add(p);
								x_points.clear();
								y_points.clear();
								polyState = false;
							}
						}						
					// freehand mode 
					else if(freehand.getState() == true) {
						freedraw = true;
					}
					// select mode 
					else if(select.getState() == true) {
						for(Shapes i : primitives) {
								// ---------- bounding box test -----------
							 if(i.boundingBox(evt.getX(), evt.getY()) == true) {
								selected = i;
								selected.setShape(true);	// flags that a primitive was selected 
								break;
							}
							 else {
								selected = null; 
								i.setShape(false);
							 }
						}
						// clear points from array after selection
						x_points.clear();
						y_points.clear();					
					}
						repaint();
				}
					public void mouseReleased(MouseEvent evt)
					{
						// Once user releases mouse: add last points, create freehand instance, and set freedraw to false
						if(freedraw == true) {
							x_points.add(evt.getX());
							y_points.add(evt.getY());
							Freehand f = new Freehand(x_points, y_points);
							f.setColor(curColor);
							primitives.add(f);
							x_points.clear();
							y_points.clear();
							freedraw = false;
						}

						repaint();
					}
				});		
				
		// mouse dragged
		addMouseMotionListener(
				new MouseAdapter()
				{
					public void mouseDragged(MouseEvent evt)
					{
						// while mouse is being dragged continuously add points to arrayList
						if(freedraw == true) {
							x_points.add(evt.getX());
							y_points.add(evt.getY());
							// display the lines being drawn for the user to see (control points) 
							for(int i = 1; i < x_points.size(); i++) {
								Graphics2D g = (Graphics2D)getGraphics();
								g.drawLine(x_points.get(i-1), y_points.get(i-1), x_points.get(i), y_points.get(i));
							}
						}
						repaint();
					}
			});		
	}
	// create menus 
	private void createMenu()
	{
		menuBar = new MenuBar();
		setMenuBar(menuBar);
		
		// drawing menu
		menu = new Menu("Shapes");
		menuBar.add(menu);
		
		// edit menu
		edit = new Menu("Edit");
		menuBar.add(edit);

		// line
		line = new CheckboxMenuItem("Line");
		menu.add(line);
		line.addItemListener(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent evt)
					{
						repaint();
					}
				});
		
		// rectangle
		rect = new CheckboxMenuItem("Rectangle");
		menu.add(rect);
		rect.addItemListener(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent evt)
					{
						repaint();
					}
				});
		
		// oval
		oval = new CheckboxMenuItem("Oval");
		menu.add(oval);
		oval.addItemListener(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent evt)
					{
						repaint();
					}
				});
		// polygon
		polygon = new CheckboxMenuItem("Polygon");
		menu.add(polygon);
		polygon.addItemListener(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent evt)
					{
						repaint();
					}
				});
		// freehand
		freehand = new CheckboxMenuItem("Freehand");
		menu.add(freehand);
		freehand.addItemListener(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent evt)
					{
						repaint();
					}
				});
		
		// select
		select = new CheckboxMenuItem("Select");
		edit.add(select);
		select.addItemListener(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent evt)
					{
						repaint();
					}
				});
		
		// delete
		delete = new MenuItem("Delete");
		edit.add(delete);
		delete.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{
						if(selected != null) {
							primitives.remove(selected);
						}	
						repaint();
					}
				});
		
		// color
		colorItem = new MenuItem("Color");
		edit.add(colorItem);
		colorItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{
						Color c = JColorChooser.showDialog(null, "Select drawing color", curColor);
						curColor = c;	// global color 
						if(selected != null) {
							selected.setColor(curColor);
						}
						
						repaint();
					}
				});
		// toggle fill
		fill = new MenuItem("Fill");
		edit.add(fill);
		fill.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{
						if(selected.get_fill() == false) {
							selected.set_Fill(true);
						}
						else {
							selected.set_Fill(false);
						}
						repaint();
					}
				});
		
		// exit
		exitItem = new MenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{
						System.exit(0);
					}
				});
	}
	
	// main drawing routine
	public void paint(Graphics g)
	{		
		// draw control points for figures with 4 coordinate points : line, rect, oval
		if(x_points.size() == 1 && polyState == false) {
			g.fillOval(x_points.get(0), y_points.get(0), 10, 10);
		}
		// else if polyState is true then draw the control points
		else if(polyState == true) {
			for(int k = 0; k < x_points.size(); k++) {
				g.fillOval(x_points.get(k), y_points.get(k), 10, 10);
			}
		}
		// loop through primitives
		for(Shapes i : primitives) {			
				i.draw(g);
		}
	
	}

	public static void main(String[] argv)
	{
		Java2DMenus f = new Java2DMenus();
		f.setTitle("Paint");      
		f.setSize(800, 600);
		f.setVisible(true);
	}
}