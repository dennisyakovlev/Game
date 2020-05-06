package master.exceptions;

public class ImageException extends RuntimeException {
	public ImageException(String message) {
		super(message);
		printStackTrace();
	}
}
