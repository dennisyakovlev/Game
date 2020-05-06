package master.variables;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;

public class InitValue {
	
	private static AnchorPane mainPane = null;
	public static Canvas canvas = null;
	//private static Scene scene = null;
	
	//method for any type of variable
	//public static final void test() {
	//	Field[] f = InitValue.class.getDeclaredFields();
	//	System.out.println(f[0].getType());
	//}

	public static final void setMainPane(AnchorPane mainPane) {
		if (InitValue.mainPane == null) {
			InitValue.mainPane = mainPane;
		}
	}
	
	public static final void setCanvas(Canvas canvas) {
		if (InitValue.canvas == null) {
			InitValue.canvas = canvas;
		}
	}
	
	public static AnchorPane getMainPane() {
		return mainPane;
	}
	
	public static Canvas getCanvas() {
		return canvas;
	}
}
