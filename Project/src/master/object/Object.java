package master.object;

public abstract class Object {

	private int x, y;
	private int width, height;
	
	public Object() {
		
	}
	
	public Object(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public final void rotate(int degrees) {
		
	}
	
	public final void translateX(int tanslation) {
		
	}
	
	public final void tanslateY(int translation) {
		
	}
	
	public final void setX(int x) {
		this.x = x;
	}
	
	public final void setY(int y) {
		this.y = y;
	}
	
	public final void setWidth(int width) {
		this.width = width;
	}
	
	public final void setHeight(int height) {
		this.height = height;
	}
	
	public final int getX() {
		return x;
	}
	
	public final int getY() {
		return y;
	}
	
	public final int getWidth() {
		return width;
	}
	
	public final int getHeight() {
		return height;
	}
	
}
