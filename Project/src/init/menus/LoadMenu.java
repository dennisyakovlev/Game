package init.menus;

import java.util.ArrayList;

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
			//System.out.println("clicked");
			for (int i = 0; i < children.size(); i++) {
				
				if (children.get(i).getClass().getSimpleName().equals("Button")) {
					
					if (((Button) children.get(i)).isClickedOn((int) e.getX(), (int) e.getY())) {
						
						scene.hide();
						
						switch (children.get(i).getName()) {
							
							case "play": 
								
								levels.one.CreateLevel.requestLevel();
								
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
		
		Button btn = new Button(0, 0, "loadmenu", "play.png", "play");
		btn.setImage();
		btn.removeBackground(Color.WHITE);
		btn.process();
		children.add(btn);
		scene.addObject(btn, 1);
		
	}

}
