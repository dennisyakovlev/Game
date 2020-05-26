package scenes;

import java.util.ArrayList;

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
 * <br> update the scene. Either using {@linkplain Scene#globalUpdate()} or {@linkplain Scene#specificUpdate(ArrayList)}.
 * <br> scene.add();
 * 
 * <br><br><STRONG>Notes</STRONG>
 * <br> 1) All children should be added before displaying scene
 * to stop unforeseen errors.
 * <br> 2) scene.hide() should be called if the scene is not wanted
 * to be displayed upon initial start up of the program.
 * <br> 3) The scene size is 300 by 150 pixels. 
 * <br> 4) {@linkplain Scene#show()} and {@linkplain Scene#hide()} are buggy for SURE
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

	
	
	public Scene() {
		
		gc = this.getGraphicsContext2D();
		
		setWidth(300);
		setHeight(150);

		setLayoutX(0);
		setLayoutY(0);
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
	 * Draws all {@linkplain Object} and each is drawn entirely onto the Scene.
	 * 
	 * @param toShow list of {@linkplain Object} to be drawn onto the scene. 
	 * <br><STRONG>Note:</STRONG> Must be in same order as {@linkplain Scene#children}
	 * <br><STRONG>Note:</STRONG> Iterates in reverse order to display
	 * higher importance children on top of lower importance.
	 */
	public void specificUpdate(ArrayList<Object> toShow) {
		//gc.clearRect(0, 0, 300, 150);
		for (int i = toShow.size() - 1; i >= 0; i--) {
			
			final Object child = toShow.get(i);

			ArrayList<int[]> positions = child.getPositions();
			ArrayList<Color> colors = child.getColors();

			for (int currentPixel = 0; currentPixel < positions.size(); currentPixel ++) {
				
				final int x = positions.get(currentPixel)[0];
				final int y = positions.get(currentPixel)[1];
				
				if (x >= 0 && x <= 299 && y >= 0 && y <= 149) {
					
					gc.setFill(colors.get(currentPixel));
					gc.fillRect(positions.get(currentPixel)[0], positions.get(currentPixel)[1], 1, 1);
					
				}

			}
						
		}
		
	}
	
	/**
	 * Draws all {@linkplain Object} onto the scene that are in {@linkplain Scene#children}.
	 */
	public void globalUpdate() {

		gc.clearRect(0,	0, 300, 150);
		
		for (int i = children.size() - 1; i >= 0; i--) {
			
			final Object child = children.get(i);

			ArrayList<int[]> positions = child.getPositions();
			ArrayList<Color> colors = child.getColors();

			for (int currentPixel = 0; currentPixel < positions.size(); currentPixel ++) {
				
				gc.setFill(colors.get(currentPixel));
				gc.fillRect(positions.get(currentPixel)[0], positions.get(currentPixel)[1], 1, 1);

			}
				
			
			
		}
		
	}
	
	

	
	
	/**
	 * Adds the scene to javafx window.
	 */
	public void add() {
		Program.mainPane.getChildren().add(this);

    }
	
	/** 
	 * @return returns {@linkplain Scene#children}
	 */
	public ArrayList<Object> getChildren() {
		
		return children;
		
	}
	
	/**
	 * Hides the scene from the view of the user.
	 */
	public void hide() {
		
		gc.clearRect(0,	0, 640, 270);
		toBack();
		
	}
	
	/**
	 * Shows the scene in the javafx window.
	 */
	public void show() {
		
		toFront();
		
	}
	
	public GraphicsContext getGraphics() {
		
		return gc;
		
	}
}
