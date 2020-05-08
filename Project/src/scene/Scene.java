package scene;

import init.Start;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import objects.Object;
import variables.Program;

/*
 * Everything you need is given to you, this should be the only class you modify along with potntially Start.
 * consult internet if needed but consult brain first "ask why isnt it doing wat i want" then trace what the
 * programs does/ doesnt
 * mode code works, you will have to trust me there. this will go vice verse soon. 
 * so it will be important that we can make sure our code does what its supposed to before we say it does do tha task
 * ill show u that later
 * update the note document with the form its given with the scene class
 * you can delete fill if its annoying
 * try creating a new background thats 128 by 54 by using some pixel art website and make sure it works
 * with all images not just this one. dont worry about what is X color is removed or it isnt 128 by 54,
 * for now it is
 * 
 * 
 * ***if the image isnt reading the right file path, which it wont be, change it like before, but i dont recommend
 * changing the actual method (objects.Object.setImage) that gets the image, but u can if want***
 * 
 * good night
 * 
 * 
 * 
 * Example rectangle: 
 * gc.setFill(Color.RED);
 * gc.fillRect(100, 100, 5, 5);
 * 
 */

public class Scene extends Canvas {
	
	private GraphicsContext gc;
	private Object child;
	
	public Scene() {
		
		gc = this.getGraphicsContext2D();
		
		setWidth(670);
		setHeight(270);



		
	}
	
	public void addObject(Object object) {

		child = object;

	}
	
	public void display() {

		int[][] positions = child.getGamePixelPositions();
		Color[] colors = child.getPixelColor();

		for (int currentPixel = 0; currentPixel < positions.length; currentPixel ++) {

			gc.setFill(colors[currentPixel]);
			gc.fillRect(positions[currentPixel][0], positions[currentPixel][1], 5, 5);

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
	
	public void add() {
		Program.mainPane.getChildren().add(this);

    }
	
	public void toFront() {
		
	}
	
	public void exitMenu() {
		
	}
	
	
}
