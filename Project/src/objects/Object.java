package objects;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import exceptions.ImageException;
import exceptions.Warn;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import variables.Constant;

public class Object {

	//in pixel 0 <= x < 640 and 0 <= y < 270
	private int x, y;
	//in pixel 0 <= x <= 128 and 0 <= y <= 54
	private int width, height;
	
	private String fileName, objectName;
	private BufferedImage image;
	private ArrayList<Color> colorsTemp = new ArrayList<>();
	private ArrayList<int[]> positionsTemp = new ArrayList<>();
	private ArrayList<int[]> positions = new ArrayList<>();
	private boolean removeBackground = false, setImage = false, process = false;
	private int importance;
	private int length;
	private int leftMostPixel, rightMostPixel, topMostPixel, bottomMostPixel;

	
	
	public Object(int x, int y, String fileName, String objectName) {
		
		if (fileName.equals("") || objectName.equals("")) {
			throw new ImageException("names cannot be empty");
		}
		
		this.x = x;
		this.y = y;
		this.fileName = fileName;
		this.objectName = objectName;
		
	}
	
	public void removeBackground(Color colorToRemove) {
		
		if (removeBackground) {
			Warn.warn("redundant operation on " + this + " removeBackground");
		}
		
		removeBackground = true;
		
		if (image == null) {
			throw new ImageException("No attempt made to get image from system.");
		}
			
		for (int i = 0; i < Constant.imageHeight; i++) {
			
			for (int j = 0; j < Constant.imageWidth; j++) {
				
				java.awt.Color pixelColor = new java.awt.Color(image.getRGB(j, i));
				
				/*
				 * set *which* squares will actually exist
				 */
				if (colorToRemove == null) {
					
					colorsTemp.add(Color.rgb(pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue()));
					positionsTemp.add(new int[] {j, i});
					
				} else {

					if (!(Math.abs(pixelColor.getGreen() - (colorToRemove.getGreen() * 256)) < 2 && Math.abs(pixelColor.getRed() - (colorToRemove.getRed() * 256)) < 2 && Math.abs(pixelColor.getBlue() - (colorToRemove.getBlue() * 256)) < 2)) {
						
						colorsTemp.add(Color.rgb(pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue()));
						positionsTemp.add(new int[] {j, i});
						
					} 
					
				}
				
				
			}
			
		}				 
		
		
		
	}
	
	public void setImage() {
		
		if (setImage) {
			Warn.warn("redundant operation on " + this + " setImage");
		}
		
		setImage = true;
		
		String classPath;
		FileInputStream inputStream;
		
		try {
			classPath = new File(Object.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent().toString();
			inputStream = new FileInputStream(classPath + "\\src\\images\\" + fileName);
			image = ImageIO.read(inputStream);
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		
		if (image.getWidth() != Constant.imageWidth || image.getHeight() != Constant.imageHeight) {
			
			classPath = "";
			inputStream = null;
			image = null;
			
			throw new ImageException("Expected image of size 128px by 54px, recieved " + image.getWidth() + "px by " + image.getHeight() + "px.");
		}
		
	}
	
	public void process() {
		
		// *** warn if method is called twice on same object ***
		if (process) {
			Warn.warn("redundant operation on " + this + " process");
		}
		
		process = true;
		// ***
		
		// *** get actual width of image after background (whether it has or hasnt been removed) is removed ***
		leftMostPixel = positionsTemp.get(0)[0];
		rightMostPixel = positionsTemp.get(0)[0];
		
		topMostPixel = positionsTemp.get(0)[1];
		bottomMostPixel = positionsTemp.get(0)[1];
		
		for (int i = 1; i < positionsTemp.size(); i++) {
			
			leftMostPixel = Math.min(leftMostPixel, positionsTemp.get(i)[0]);
			rightMostPixel = Math.max(rightMostPixel, positionsTemp.get(i)[0]);
			
			topMostPixel = Math.min(topMostPixel, positionsTemp.get(i)[1]);
			bottomMostPixel = Math.max(bottomMostPixel, positionsTemp.get(i)[1]);		
			
		}
		
		width = rightMostPixel - leftMostPixel + 1;
		height = bottomMostPixel - topMostPixel + 1;
		// ***
		
		length = positionsTemp.size();
		
		// *** change the actual positions of the pixels to the positions theyre going to be displayed
		for (int i = 0; i < positionsTemp.size(); i++) {
			
			positions.add(new int[] {((positionsTemp.get(i)[0] - leftMostPixel) * 5) + x, ((positionsTemp.get(i)[1] - topMostPixel) * 5) + y});
			
		}
		// ***
		
	}		
	
	public ArrayList<int[]> getPositions() {
		
		return positions;
		
	}
	
	public ArrayList<Color> getColors() {
		
		return colorsTemp;
		
	}
	
	public void check() {
		
		if (!removeBackground || !setImage || !process) {
			throw new ImageException("Necessary processing has not been done to " + this);
		}
		
	}
	
	public int getX() {
		
		return x;
		
	}
	
	public int getY() {
		
		return y;
		
	}

	public int getActualWidth() {
		
		/*
		 * returns the width of the image in pixel: 0 < width < 128
		*/
		return width;
		
	}
	
	public int getActualHeight() {
		
		/*
		 * returns the height of the image in pixel: 0 < height < 54
		*/
		return height;
		
	}
	
	public int getGameWidth() {
		
		/*
		 * returns the width of the image being displayed on screen in pixels 0 < width < 640
		*/
		return width * 5;
		
	}
	
	public int getGameHeight() {
		
		/*
		 * returns the width of the image being displayed on screen in pixels 0 < height < 270
		*/
		return height * 5;
		
	}
	
	public int getImportance() {
		
		return importance;
		
	}

	public int getNumberOfPixels() {
		
		return length;
		
	}
	
	public Pair<ArrayList<int[]>, ArrayList<Color>> test(int startX, int startY, int endX, int endY) {
		
		ArrayList<int[]> pos = new ArrayList<>();
		ArrayList<Color> c = new ArrayList<>();
		
		Pair<ArrayList<int[]>, ArrayList<Color>> pair = new Pair<ArrayList<int[]>, ArrayList<Color>>(pos, c);
		
		for (int i = 0; i < positions.size(); i++) {
			
			final int[] arr = positions.get(i);
			final int x = arr[0];
			final int y = arr[1];
			
			if (x >= startX + this.x && x < endX + this.x && y >= startY + this.y && y < endY+ this.y) {
				
				pos.add(new int[] {arr[0], arr[1]});
				c.add(colorsTemp.get(i));
				
			}
			
		}
		
		return pair;
		
	}
	
	public String getName() {
		
		return objectName;
		
	}
	
	public void setImportance(int importance) {
		
		this.importance = importance;
		
	}

	public void setX(int x) {
		
		this.x = x;
		
	}
	
	public void setY(int y) {
		
		this.y = y;
		
	}

	/*
	 * need translate methods that allow gameobject to move, otherwise test method will not work
	 * because positions array is the array of the initial positions only and isnt update with x y
	 */
}