package master.scene;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import master.exceptions.NameException;
import master.exceptions.SceneException;
import master.object.Object;
import master.variables.Program;

public class Scene extends Canvas {

	private int numberChildren = 0;
	private int[] pixels = new int[172800];
	private Color[] color = new Color[172800];
	//private Map<String, Integer> importanceValues = new HashMap<>();
	private Object[] importanceValues = new Object[100];
	private int[] test = new int[100];
	//private Object[] importanceValues = new Object[100];
	private Map<String, Object> children = new HashMap<>();
	private String sceneName, sceneID;
	private GraphicsContext gc;
	
	public Scene(String sceneName, String objectName) {
		
		if (sceneName.equals("Canvas")) {
			throw new NameException("Cannot name 'Canvas'");
		}
		
		this.sceneName = sceneName;
		sceneID = "Canvas." + this.sceneName;
		setId(sceneID);
		
		setWidth(670);
		setHeight(270);
		
		for (int i = 0; i < test.length; i++) {
			
			test[i] = -1;
	
		}
		
		gc = this.getGraphicsContext2D();
	}
	

	//test and importance values are ordered with greatest importance first
	public final void addChild(Object child, int importance) {
		
		checkError(importance);
		
		for (Map.Entry<String, Object> entry : children.entrySet()) {
			if (entry.getValue().getName().equals(child.getName())) {
				throw new NameException("Object with same name already exists.");
			}
		}
				
		for (int i = 0; i < test.length; i++) {
			
			if (importance >= test[i]) {
			
				for (int j = test.length - 1; j > i; j--) {
					test[j] = test[j - 1];
					importanceValues[j] = importanceValues[j - 1];
				}
					
				test[i] = importance;
				importanceValues[i] = child;
				
				break;
			}
			
		}
		
		children.put(child.getName(), child);
		numberChildren ++;
	}
	
	private final void removeChild() {
		
	}
	
	
	public void update() {
		
		//iterate through importancevalues end of array to front
		//because want to draw least important first
		
		for (int i = importanceValues.length - 1; i >= 0; i--) {
			if (importanceValues[i] != null) {
				
				Color[] temp = importanceValues[i].getPixels();
				int[][] temp2 = importanceValues[i].getPositions();
				
				for (int j = 0; j < temp.length; j++) {
					gc.setFill(Color.rgb((int) (temp[j].getRed() * 256), (int) (temp[j].getGreen() * 256), (int) (temp[j].getBlue() * 256)));
					gc.fillRect(temp2[j][0], temp2[j][1], 1, 1);
				}
				
			}
		}
		
		
	}
	
	public void fill(Color color) {
		
		gc.setFill(color);
		
		for (int i = 0; i < 270; i++) {
			for (int j = 0; j < 640; j++) {
				
				gc.fillRect(j, i, 1, 1);

			}
			
		}
		
	}
	
	public final void hide() {
		
		Program.mainPane.getChildren().remove(this);
		
	}
	
	public final void show() {
		
		Program.mainPane.getChildren().add(this);
		
	}

	private void checkError(int importance) {
		
		if (importance < 0 || importance > 100) {
			throw new SceneException("Importance must be between 0 (inclusive) and 100 (inclusive)");
		}
		
		if (numberChildren + 1 > 100) {
			throw new SceneException("Too many children, maximum 100.");
		}
		
	}
	
}

/* USEFUL do not touch


ObservableList<Node> nodes = Program.mainPane.getChildrenUnmodifiable();

		if (nodes != null) {
			
			for (Node node : nodes) {

				if (node.getId() != null) {
					
					if (node.getId().equals(sceneID)) {
			
						super.toBack();
						break;
			
					}
					
				}
				
			}
			
		}



*/