package levels.one;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import objects.GameObject;
import objects.Object;
import utils.GameLoopInterface;
import utils.LevelScene;

public class Level extends LevelScene implements GameLoopInterface {

	private ArrayList<GameObject> test = new ArrayList<>();
	
	@Override
	public void collisionLogic() {
		
	}

	@Override
	public void animatations() {
		
		
		for (int i = 0; i < test.size(); i++) {
			
			test.get(i).translateX(1);
			
		}
		
	}

	@Override
	public void startLevel() {
		
		test = getGameChildren();
		
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
