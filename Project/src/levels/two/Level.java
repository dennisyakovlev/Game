package levels.two;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import objects.GameObject;
import utils.GameLoopInterface;
import scenes.LevelScene;

public class Level extends LevelScene implements GameLoopInterface {

	private ArrayList<GameObject> children = new ArrayList<>();
	
	@Override
	public void collisionLogic() {
		
	}

	@Override
	public void animatations() {
		
		
		for (int i = 0; i < children.size(); i++) {
			
			children.get(i).translateX(1);
			
		}
		
	}

	@Override
	public void startLevel() {
		
		children = getGameChildren();
		
		AnimationTimer anim = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				
				collisionLogic();
				animatations();
				update();
				
			}
		};

		anim.start();

	}
	
}
