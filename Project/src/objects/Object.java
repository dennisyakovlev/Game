package objects;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import exceptions.ImageException;
import exceptions.Warn;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import scenes.Scene;
import variables.Constant;

/**
 * Is base type of child.
 * 
 * <br><br><STRONG>Usage</STRONG>
 * <br> Object object = new Object(params);
 * <br> object.setImage();
 * <br> object.removeBackground(color);
 * <br> object.process();
 * 
 * <br><br><STRONG>Notes</STRONG>
 * <br> 1) Pixel is pixel on screen of computer. GamePixel is 5 by 5
 * pixels.
 * 
 * @author Dennis
 *
 */
public class Object {

	/**
	 * The x coordinate of the left post pixel of an object relative
	 * to the left most pixel of the {@linkplain Scene} it is added to.
	 */
	private int x;
	
	/**
	 * The y coordinate of the top post pixel of an object relative
	 * to the top most pixel of the {@linkplain Scene} it is added to.
	 */
	private int y;

	/**
	 * The difference between the right most pixel and left most pixel
	 * of the object. Has value 0 <= width <= 128.
	 */
	private int width;
	
	/**
	 * The difference between the bottom most pixel and top most pixel
	 * of the object. Has value 0 <= height <= 54.
	 */
	private int height;
	
	/**
	 * The file name of picture to be converted into an object.
	 */
	private String fileName;
	
	/**
	 * The name chosen to be given to the object. Should be unique 
	 * to all other objects in the same {@linkplain Scene} (currently no check for this)
	 */
	private String objectName;
	
	/**
	 * The package in {@linkplain images} that the images are located for the 
	 * desired {@linkplain Scene}
	 */
	private String sectionName;
	
	/**
	 * Image of size 128 by 54 pixels.
	 */
	private BufferedImage image;
	
	/**
	 * An {@linkplain ArrayList} of type {@linkplain Color}. Each index contains 
	 * the color of the pixel at that index. The first index corresponds to the
	 * color of the first index in {@linkplain Object#positions}
	 */
	private ArrayList<Color> colors = new ArrayList<>();
	
	/**
	 * An {@linkplain ArrayList} of type {@linkplain Array}. Each index contains the 
	 * x and y position of the pixel associated with that index. Not for display as the
	 * positions are not scaled properly.
	 */
	private ArrayList<int[]> positionsTemp = new ArrayList<>();
	
	/**
	 * An {@linkplain ArrayList} of type {@linkplain Array}. Each index contains the 
	 * x and y position of the game pixel associated with that index. The first index
	 * would be the displayed on the very top row of pixels and be the pixel on
	 * the very left of the row.
	 */
	private ArrayList<int[]> positions = new ArrayList<>();
	private boolean removeBackground = false, setImage = false, process = false;
	
	/**
	 * The value only associated with the current Object. Determines what other Objects
	 * will be displayed in front of or behind the current Object.
	 */
	private int importance;
	
	/**
	 * The number of pixels the processed object has.
	 */
	private int length;
	
	/**
	 * The pixel of the object that is further left than any other
	 * pixel of the object.
	 */
	private int leftMostPixel;
	
	/**
	 * The pixel of the object that is further right than any other
	 * pixel of the object.
	 */
	private int rightMostPixel;
	
	/**
	 * The pixel of the object that is further upwards than any other
	 * pixel of the object.
	 */
	private int topMostPixel;
	
	/**
	 * The pixel of the object that is further downwards than any other
	 * pixel of the object.
	 */
	private int bottomMostPixel;

	
	/**
	 * Constructor 
	 * 
	 * @param x {@linkplain Object#x}
	 * @param y {@linkplain Object#y}
	 * @param sectionName {@linkplain Object#sectionName}
	 * @param fileName {@linkplain Object#fileName}
	 * @param objectName {@linkplain Object#objectName}
	 */
	public Object(int x, int y, String sectionName, String fileName, String objectName) {
		
		if (fileName.equals("") || objectName.equals("")) {
			throw new ImageException("names cannot be empty");
		}
		
		this.x = x;
		this.y = y;
		this.fileName = fileName;
		this.sectionName = sectionName;
		this.objectName = objectName;
		
	}
	
	/**
	 * 
	 * Removes all pixels of selected <i>color</i> to be not included
	 * in the object. 
	 * 
	 * @param color All pixels of specified {@linkplain Color} in the object
	 * will not be added to {@linkplain Object#colorsTemp} and {@linkplain Object#positions}.
	 */
	public void removeBackground(Color color) {
		
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
				if (color == null) {
					
					colors.add(Color.rgb(pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue()));
					positionsTemp.add(new int[] {j, i});
					
				} else {

					if (!(Math.abs(pixelColor.getGreen() - (color.getGreen() * 256)) < 2 && Math.abs(pixelColor.getRed() - (color.getRed() * 256)) < 2 && Math.abs(pixelColor.getBlue() - (color.getBlue() * 256)) < 2)) {
						
						colors.add(Color.rgb(pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue()));
						positionsTemp.add(new int[] {j, i});
						
					} 
					
				}
				
				
			}
			
		}				 
		
		
		
	}
	
	/**
	 * Get image from specified directory 
	 */
	public void setImage() {
		
		if (setImage) {
			Warn.warn("redundant operation on " + this + " setImage");
		}
		
		setImage = true;
		
		String classPath;
		FileInputStream inputStream;
		
		try {
			classPath = new File(Object.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent().toString();
			inputStream = new FileInputStream(classPath + "\\src\\images\\" + sectionName + "\\" + fileName);
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
	
	/**
	 * Complete several unrelated tasks necessary for possible usage later.
	 */
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
	
	/**
	 * @return returns {@linkplain Object#positions}
	 */
	public ArrayList<int[]> getPositions() {
		
		return positions;
		
	}
	
	/**
	 * @return returns {@linkplain Object#colors}
	 */
	public ArrayList<Color> getColors() {
		
		return colors;
		
	}
	
	/**
	 * Checks whether the object has been created correctly and
	 * the necessary methods have been called on the object.
	 */
	public void check() {
		
		if (!removeBackground || !setImage || !process) {
			throw new ImageException("Necessary processing has not been done to " + this);
		}
		
	}
	
	/**
	 * @return returns {@linkplain Object#x}
	 */
	public int getX() {
		
		return x;
		
	}
	
	/**
	 * @return returns {@linkplain Object#y}
	 */
	public int getY() {
		
		return y;
		
	}

	/**
	 * @return returns {@linkplain Object#width}
	 */
	public int getWidth() {

		return width;
		
	}

	/**
	 * @return returns {@linkplain Object#height}
	 */
	public int getHeight() {
		
		return height;
		
	}

	/**
	 * @return returns the difference between the right most and left most pixel
	 * of the object at the window display size.
	 */
	public int getGameWidth() {
		
		return width * 5;
		
	}
	
	/**
	 * @return returns the difference between the bottom most and top most pixel
	 * of the object at the window display size.
	 */
	public int getGameHeight() {
		
		return height * 5;
		
	}
	
	/**
	 * @return returns {@linkplain Object#importance}
	 */
	public int getImportance() {

		return importance;
		
	}

	/**
	 * @return returns {@linkplain Object#length}
	 */
	public int getNumberOfPixels() {
		
		return length;
		
	}
	
	/**
	 * An imaginary square defined by the parameters is the portion of the
	 * image that will be returned. If there are missing pixels in this square
	 * they will be skipped over.
	 * 
	 * @param startX the x coordinate of the left most pixel of the portion wanted
	 * @param startY the y coordinate of the top most pixel of the portion wanted
	 * @param endX the x coordinate of the right most pixel of the portion wanted
	 * @param endY the y coordinate of the bottom most pixel of the portion wanted
	 * 
	 * @return A pair of {@linkplain ArrayList}, the first being the game pixel positions
	 * of the pixels in the requested "square". The second is the corresponding colors
	 * of the pixels chosen to be returned.
	 */
	public Pair<ArrayList<int[]>, ArrayList<Color>> getSpecificPixels(int startX, int startY, int endX, int endY) {
		
		ArrayList<int[]> pos = new ArrayList<>();
		ArrayList<Color> c = new ArrayList<>();
		
		Pair<ArrayList<int[]>, ArrayList<Color>> pair = new Pair<ArrayList<int[]>, ArrayList<Color>>(pos, c);
		
		for (int i = 0; i < positions.size(); i++) {
			
			final int[] arr = positions.get(i);
			final int x = arr[0];
			final int y = arr[1];
			
			if (x >= startX + this.x && x < endX + this.x && y >= startY + this.y && y < endY+ this.y) {
				
				pos.add(new int[] {arr[0], arr[1]});
				c.add(colors.get(i));
				
			}
			
		}
		
		return pair;
		
	}
	
	/**
	 * @return returns {@linkplain Object#objectName}
	 */
	public String getName() {
		
		return objectName;
		
	}
	
	/**
	 * Sets {@linkplain Object#importance}
	 * 
	 * @param importance {@linkplain Object#importance}
	 */
	public void setImportance(int importance) {

		this.importance = importance;
		
	}

	/**
	 * Sets {@linkplain Object#x}
	 * 
	 * @param x {@linkplain Object#x}
	 */
	public void setX(int x) {
		
		this.x = x;
		
	}
	
	/**
	 * Sets {@linkplain Object#y}
	 * 
	 * @param importance {@linkplain Object#y}
	 */
	public void setY(int y) {
		
		this.y = y;
		
	}

}