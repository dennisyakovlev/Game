package utils;

import scene.Scene;

public interface GameLoopInterface {

	public void setScene(Scene scene);
	public void collisionLogic();
	public void animatations();
	
}
