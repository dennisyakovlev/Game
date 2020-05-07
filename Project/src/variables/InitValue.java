package variables;

import javafx.scene.layout.AnchorPane;

public class InitValue {
	
	private static AnchorPane mainPane = null;
	
	public static final void setMainPane(AnchorPane mainPane) {
		if (InitValue.mainPane == null) {
			InitValue.mainPane = mainPane;
		}
	}
	
	public static AnchorPane getMainPane() {
		return mainPane;
	}
	
}
