package master.object;

import javafx.scene.paint.Color;

public abstract class Object {

	private int x, y;
	private int width, height;
	private Color[] pixels;
	private int[][] positions;
	private int numberPixels;
	private String name;
	
	public Object(String name) {
		this.name = name;
	}
	
	public final void setData(Color[] pixels, int[][] positions) {
		this.pixels = pixels;
		this.positions = positions;
		
		numberPixels = pixels.length;
	}
	
	public final Color[] getPixels() {
		return pixels;
	}
	
	//returns positions of pixels relative to top left of image corner
	public final int[][] getPositions() {
		return positions;
	}
	
	public final String getName() {
		return name;
	}
	
	public final void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public final void setDismensions(int width, int height) {
		this.width = width;
		this.height = height;
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
