package scenes;

import java.util.ArrayList;

import com.sun.javafx.geom.AreaOp.AddOp;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import objects.Object;
import variables.Program;

/**
 * Connects application visually to javafx window. 
 * 
 * <br><br><STRONG>Usage</STRONG>
 * <br> Scene scene = new Scene();
 * <br> add children to scene
 * <br> scene.globalUpdate() or scene.specificUpdate() (idk second might be buggy)
 * <br> scene.add();
 * 
 * <br><br><STRONG>Notes</STRONG>
 * <br> 1) All children should be added before displaying scene
 * to stop unforeseen errors.
 * <br> 2) The scene size is 640 by 270 pixels. Only pixels 5 to
 * 635 (634 in code) for width and 5 to 265 (264 in code) are
 * visible.
 * 
 * @author Dennis
 *
 */
public class Scene extends Canvas {
	
	private GraphicsContext gc;
	
	/**
	 * List of the scene's children. Children with highest importance have lower index. 
	 * Children with lower importance have higher index.
	 */
	private ArrayList<Object> children = new ArrayList<>();
	
	/**
	 * Is list of children last specified in {@linkplain Scene#specificUpdate(ArrayList)}. 
	 */
	private ArrayList<Object> lastShow = new ArrayList<>();
	
	public Scene() {
		
		gc = this.getGraphicsContext2D();
		
		setWidth(640);
		setHeight(270);

		setLayoutX(-5);
		setLayoutY(-5);
	}
	
	/**
	 * Adds <i>child</i> to the scene. Does not display child.
	 * 
	 * @param child The {@linkplain Object} to be added to the scene's {@linkplain Scene#children}.
	 * 
	 * @param importance Should have value of > 0. Is the "importance" associated
	 * with the <i>child</i> being added to the scene. Children with higher importance
	 * are drawn on top of children with lower importance.
	 * <br><STRONG>Note:</STRONG> If two children have the same importance, the higher
	 * importance child will be on top.
	 */
	public void addObject(Object child, int importance) {

		child.check();
		
		child.setImportance(importance);

		boolean broken = false;
		
		if (children.size() != 0) {

			for (int i = 0; i < children.size(); i++) {
				
				if (children.get(i).getImportance() <= importance) {
					
					children.add(i, child);
					broken = true;
					break;
					
				} 
				
			}
			
		}
		
		if (broken == false) {
			
			children.add(child);
			
		}

	}
	
	/**
	 * Displays specified list of children to the scene. Used when displaying
	 * all children is not needed.
	 * 
	 * @param toShow list of {@linkplain Object} to be drawn onto the scene. 
	 * <br><STRONG>Note:</STRONG> Must be in same order as {@linkplain Scene#children}
	 * <br><STRONG>Note:</STRONG> Iterates in reverse order to display
	 * higher importance children on top of lower importance.
	 */
	public void specificUpdate(ArrayList<Object> toShow) {
		
		gc.clearRect(0,	0, 640, 270);
		
		lastShow = toShow;
				
		for (int i = toShow.size() - 1; i >= 0; i--) {
			
			final Object child = toShow.get(i);
			final int x = child.getX();
			final int y = child.getY();
			final int width = child.getGameWidth();
			final int height = child.getGameHeight();

			/*
			 * each variable must be independant
			 * 
			 * -1000000 is a value which no object position will ever have, thus,
			 * used to show object is fully of scene in one dimension
			 * 
			 * EITHER startX or endX will have value of -1000000 if object is
			 * off scene in X direction. Same for startY and endY
			*/
			int startX = x < 0 ? -x : (x > 640 ? -1000000 : 0);
			int endX = x + width > 640 ? 640 - x : (x + width <= 0 ? -1000000 : width);
			
			int startY = y < 0 ? -y : (y > 270 ? -1000000 : 0);
			int endY = y + height > 270 ? 270 - y : (y + height <= 0 ? -1000000 : height);
			
			if (startX != -1000000 && startY != -1000000 && endX != -1000000 && endY != -1000000) {

				ArrayList<int[]> positions = new ArrayList<>();
				ArrayList<Color> colors = new ArrayList<>();
				Pair<ArrayList<int[]>, ArrayList<Color>> p = new Pair<ArrayList<int[]>, ArrayList<Color>>(positions, colors);
				
				p = child.getSpecificPixels(startX, startY, endX, endY);
				
				positions = p.getKey();
				colors = p.getValue();

				for (int currentPixel = 0; currentPixel < positions.size(); currentPixel ++) {

					gc.setFill(colors.get(currentPixel));
					gc.fillRect(positions.get(currentPixel)[0], positions.get(currentPixel)[1], 5, 5);

				}
				
			}
			
		}

	}

	/**
	 * Displays all children from {@linkplain Scene#children}
	 * <br><STRONG>Note:</STRONG> Iterates in reverse order to display
	 * higher importance children on top of lower importance.
	 */
	public void globalUpdate() {
		
		for (int i = children.size() - 1; i >= 0; i--) {
			
			final Object child = children.get(i);
			final int x = child.getX();
			final int y = child.getY();
			final int width = child.getGameWidth();
			final int height = child.getGameHeight();

			/*
			 * each variable must be independant
			 * 
			 * -1000000 is a value which no object position will ever have, thus,
			 * used to show object is fully of scene in one dimension
			 * 
			 * EITHER startX or endX will have value of -1000000 if object is
			 * off scene in X direction. Same for startY and endY
			*/
			int startX = x < 0 ? -x : (x > 640 ? -1000000 : 0);
			int endX = x + width > 640 ? 640 - x : (x + width <= 0 ? -1000000 : width);
			
			int startY = y < 0 ? -y : (y > 270 ? -1000000 : 0);
			int endY = y + height > 270 ? 270 - y : (y + height <= 0 ? -1000000 : height);
			
			if (startX != -1000000 && startY != -1000000 && endX != -1000000 && endY != -1000000) {

				ArrayList<int[]> positions = new ArrayList<>();
				ArrayList<Color> colors = new ArrayList<>();
				Pair<ArrayList<int[]>, ArrayList<Color>> p = new Pair<ArrayList<int[]>, ArrayList<Color>>(positions, colors);
				
				p = child.getSpecificPixels(startX, startY, endX, endY);
				
				positions = p.getKey();
				colors = p.getValue();

				for (int currentPixel = 0; currentPixel < positions.size(); currentPixel ++) {
					
					/*
					 * update the x and y here so that the positions array dont have to be updated in a seperate loop
					*/
					gc.setFill(colors.get(currentPixel));
					gc.fillRect(positions.get(currentPixel)[0], positions.get(currentPixel)[1], 5, 5);

				}
				
			}
			
		}
		
	}
	
	/**
	 * Adds the scene to javafx window.
	 */
	public void add() {
		Program.mainPane.getChildren().add(this);

    }
	
	public void toFront() {
		
	}
	
	/**
	 * Hides the scene from view on the javafx window. 
	 */
	public void hide() {
		
		gc.clearRect(0,	0, 640, 270);
		
	}
	
	/**
	 * POTENTIALLY BUGGY
	 * <br> Displays the scene if it is currently hidden.
	 * 
	 * @param global Whether to display the scene globally (true) or only
	 * a specific portion (false). If false, will display last portion selected 
	 * in {@linkplain Scene#specificUpdate(ArrayList)}.
	 * <br><STRONG>Note:</STRONG> If specific show is requested and {@linkplain Scene#lastShow}
	 * has not been assigned value, {@linkplain NullPointerException} will happen.
	 */
	public void show(boolean global) {
		
		if (global) {
			
			globalUpdate();
			
		} else {
			
			specificUpdate(lastShow);
			
		}
		
		
	}
	
	/** 
	 * @return returns {@linkplain Scene#children}
	 */
	public ArrayList<Object> getChildren() {
		
		return children;
		
	}
}
