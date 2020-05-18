package init.menus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javafx.scene.paint.Color;
import objects.Button;
import objects.Object;
import scenes.Scene;

/**
 * Menu that is visible to user when program is first opened.
 * 
 * @author Dennis
 *
 */
public class LoadMenu {

	/**
	 * The children of the menu.
	 */
	private ArrayList<Object> children = new ArrayList<>();
	
	/**
	 * The {@linkplain Scene} for the menu.
	 */
	private final Scene scene = new Scene();
	
	/**
	 * Constructor
	 */
	public LoadMenu() {
		
		createChildren();
		
		scene.globalUpdate();
		scene.add();
		
		// *** checks for mouse clicks and does necessary action if the right thing is clicked
		scene.setOnMouseClicked(e -> {
			
			for (int i = 0; i < children.size(); i++) {
				
				if (children.get(i).getClass().getSimpleName().equals("Button")) {
					
					if (((Button) children.get(i)).isClickedOn((int) e.getX(), (int) e.getY())) {
						
						scene.hide();
						
						switch (children.get(i).getName()) {
							
							case "play": 
								
								levels.one.CreateLevel.requestLevel();
								
								break;
								
							case "credits": 
								
								levels.two.CreateLevel.requestLevel();
								
								break;
		
						}
						
						
					}
					
				}
				
			}
			
		});
		// ***
	}
	
	/**
	 * Create all the children to be added to the menu.
	 */
	private void createChildren() {
		
		Button btn = new Button(200, 5, "loadmenu", "play_button.png", "play");
		btn.setImage();
		btn.removeBackground(Color.WHITE);
		btn.process();
		children.add(btn);
		scene.addObject(btn, 1);
		
		Button credits = new Button(5, 100, "loadmenu", "credits.png", "credits");
		credits.setImage();
		credits.removeBackground(Color.WHITE);
		credits.process();
		children.add(credits);
		scene.addObject(credits, 2);
		
		
		/*
		Object green = new Object(0, 50, "loadmenu", "green_circle.png", "green");
		green.setImage();
		green.removeBackground(Color.BLACK);
		green.process();
		children.add(green);
		scene.addObject(green, 2);
		toDisplay.add(2);

		Object purple = new Object(50, 50, "loadmenu", "purple_circle.png", "purple");
		purple.setImage();
		purple.removeBackground(Color.BLACK);
		purple.process();
		children.add(purple);
		scene.addObject(purple, 1);
		toDisplay.add(1);
		
		Object red = new Object(0, 0, "loadmenu", "red_circle.png", "red");
		red.setImage();
		red.removeBackground(Color.BLACK);
		red.process();
		children.add(red);
		scene.addObject(red, 3);
		toDisplay.add(3);
		
		Object blue = new Object(50, 0, "loadmenu", "blue_circle.png", "blue");
		blue.setImage();
		blue.removeBackground(Color.BLACK);
		blue.process();
		children.add(blue);
		scene.addObject(blue, 4);
		toDisplay.add(4);
		*/
	}

}
