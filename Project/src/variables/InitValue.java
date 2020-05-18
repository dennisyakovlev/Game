package variables;

import javafx.scene.layout.AnchorPane;

/**
 * Class for creating universal variables that cannot
 * be assigned value before startup of progrm
 * 
 * @author Dennis
 *
 */
public class InitValue {
	
	/**
	 * The {@linkplain AnchorPane} where all items in the
	 * program are drawn onto.
	 */
	private static AnchorPane mainPane = null;
	
	/**
	 * Sets value of {@linkplain InitValue#mainPane} if <i>mainPane</i>
	 * is not null.
	 * 
	 * @param mainPane The anchor pane loaded directly from the fxml loader.
	 */
	public static final void setMainPane(AnchorPane mainPane) {
		if (InitValue.mainPane == null) {
			InitValue.mainPane = mainPane;
		}
	}
	
	/**
	 * @return returns {@linkplain InitValue#mainPane}
	 */
	public static AnchorPane getMainPane() {
		return mainPane;
	}
	
}
