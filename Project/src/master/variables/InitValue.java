package master.variables;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;

public class InitValue {
	
	private static AnchorPane mainPane = null;
	
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
	
	public static AnchorPane getMainPane() {
		return mainPane;
	}
	
}
