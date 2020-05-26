package levels.one;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import objects.gameobjects.GameObject;
import objects.gameobjects.Vine;
import utils.GameLoopInterface;
import scenes.LevelScene;

public class Level extends LevelScene implements GameLoopInterface {

	private ArrayList<GameObject> children = new ArrayList<>();
	private long timeInMS = 0;
	private long last;
	private double total = 0;
	private double counter = 0;
	private double counter2 = 0;
	long start;
	
	@Override
	public void collisionLogic() {
		
	}

	@Override
	public void animatations() {
		
		((Vine) children.get(1)).swing(timeInMS);
		//children.get(0).translateX(1);
		children.get(0).rotate(0, 1, Math.PI / 2);
		
		
	}

	@Override
	public void startLevel() {
		
		backgroundUpdate(1);
		show();
		
		children = getGameChildren();
		
		start = System.nanoTime();
				
		AnimationTimer anim = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				
				timeInMS = (now - start) / 1000000;
				
				/*
				total += (timeInMS - last);
				counter ++;
				
				if (children.get(0).getX() > 640) {
					
					System.out.println(1000 / (total / counter));
					stop();
					
				}
				
				last = timeInMS;
				*/
				
				collisionLogic();
				animatations();
				update();
				
				/*
				//System.out.println((now - start) / 1000000);
				if ((now - start) / 1000000 > 2500) {
					//update();
					start = System.nanoTime();
					counter ++;
					
				}
				
				if (counter > 25) {
					stop();
					//System.out.println("done");
				}
				*/
				//if (counter2 >= 1000) {
				//	start = System.nanoTime();
					////animatations();
					//counter++;
				//	counter2 = 0;
				//}
				
				//counter2 = timeInMS;
				//System.out.println(counter2);
				
				 
			}
		};

		anim.start();

	}
	
}
