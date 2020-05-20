/* 
 * character 115 is new line automatically
 */

/*
 * The javadocs are there to help us understand what is going on, when it 
 * becomes to big to remember everything for me and so you can see what is
 * actually going on without understanding my code. To make sure there
 * is no confusion follow the rules. If you spot a javadoc where the rules
 * are broken, if it is yours fix it, if it is other persons then inform them.
 * If something is unclear in the javadoc (like what a parameter is) tell the
 * person who owns the javadoc. 
 * 
 * Some of the javadoc rules (because i picked them to make it easy to understand): 
 * 
 * - When referring to the specific instance of a class while creating
 *   the javadoc inside that class use the class name with all lower case letter.
 * - When referring to the class while creating the javadoc inside that class use 
 *   the exact class name
 * - When referring to class outside of the class, use a link
 * - When referring to method parameter in the method description use italics.
 * - Try to only link something once in a description
 *   Like don't link Object 6 times in the same explanation, its obvious
 *   which Object class you are referring to, if it is not then link it
 *   as you think needed 
 */


package init;

import java.util.ArrayList;

import init.menus.LoadMenu;
import javafx.stage.Stage;
import objects.Object;


public class Start {

	ArrayList<Object> children = new ArrayList<>();
	
	public void firstMethod(Stage primaryStage) {
		
		levels.one.CreateLevel one = new levels.one.CreateLevel();
		
		LoadMenu loadMenu = new LoadMenu();
		
		/*
		Object obj = new Object(0, 0, "loadmenu", "play.png", "bg");
		obj.setImage();
		obj.removeBackground(Color.WHITE);
		obj.process();
		
		children.add(obj);
		
		Scene scene = new Scene();
		scene.addObject(obj, 1);
		scene.specificUpdate(children);
		scene.add();
		*/

		
		primaryStage.show();
	}
	

	
}
