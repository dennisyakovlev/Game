package exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Warn {

	public static void warn(String msg) {
		
		Logger logger = Logger.getLogger(Warn.class.getName());
		logger.setLevel(Level.WARNING);
		logger.warning(msg);
		
	}
	
}
