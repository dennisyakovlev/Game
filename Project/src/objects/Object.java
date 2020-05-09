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
	//private int[][] positions;
	private Color[] colors;
	private boolean removeBackground = false, setImage = false, process = false;
	private int importance;
	private int length;
	private int leftMostPixel, rightMostPixel, topMostPixel, bottomMostPixel;
	private int[][] gamePositions;
	
	
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
		
		if (process) {
			Warn.warn("redundant operation on " + this + " process");
		}
		
		process = true;
		
		if (positionsTemp.size() != colorsTemp.size()) {
			throw new ImageException("yurr not supposed to see this shoot it if this appears");
		}
		
		//positions = new int[positionsTemp.size()][2];
		gamePositions = new int[positionsTemp.size()][2];
		colors = new Color[colorsTemp.size()];
		
		leftMostPixel = positionsTemp.get(0)[0];
		rightMostPixel = positionsTemp.get(0)[0];
		
		topMostPixel = positionsTemp.get(0)[1];
		bottomMostPixel = positionsTemp.get(0)[1];
		
		for (int i = 1; i < positionsTemp.size(); i++) {
			
			leftMostPixel = Math.min(leftMostPixel, positionsTemp.get(i)[0]);
			rightMostPixel = Math.max(rightMostPixel, positionsTemp.get(i)[0]);
			
			topMostPixel = Math.min(topMostPixel, positionsTemp.get(i)[1]);
			bottomMostPixel = Math.max(topMostPixel, positionsTemp.get(i)[1]);		
			
		}
		
		width = rightMostPixel - leftMostPixel + 1;
		height = bottomMostPixel - topMostPixel + 1;
		
		for (int i = 0; i < gamePositions.length; i++) {
			
			colors[i] = colorsTemp.get(i);	
			/*
			 * actually sets the positions the squares will be in
			 */
			//gamePositions[i] = new int[] {((positionsTemp.get(i)[0] - leftMostPixel) * 5) + x, ((positionsTemp.get(i)[1] - topMostPixel) * 5) + y}; actual code
			gamePositions[i] = new int[] {((positionsTemp.get(i)[0] - leftMostPixel) * 5), ((positionsTemp.get(i)[1] - topMostPixel) * 5)}; 
			
		}
		
		length = gamePositions.length;
	}		
	
	public ArrayList<int[]> getPositions() {
		
		ArrayList<int[]> temp = new ArrayList<>(Arrays.asList(gamePositions));
		
		return temp;
		
	}
	
	public ArrayList<Color> getColors() {
		
		ArrayList<Color> temp = new ArrayList<>(Arrays.asList(colors));
		
		return temp;
		
	}
	
	public int[][] getInitialGamePixelPositions() {
		
		/*
		int[][] temp = new int[positions.length][2];
		
		for (int i = 0; i < positionsTemp.size(); i++) {
			temp[i] = new int[] {(positions[i][0] * 5) +  x, (positions[i][1] * 5) + y};
		}	
		*/
		
		return gamePositions;
	}
	
	public Color[] getInitialPixelColor() {

		/*
		Color[] temp2 = new Color[colorsTemp.size()];
		
		for (int i = 0; i < colorsTemp.size(); i++) {
			temp2[i] = colorsTemp.get(i);
		}
		*/
		
		return colors;
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

	public int getPixelWidth() {
		return width;
	}
	
	public int getPixelHeight() {
		return height;
	}
	
	public int getGameWidthInPixels() {
		return width * 5;
	}
	
	public int getGameHeightInPixels() {
		return height * 5;
	}
	
	public int getImportance() {
		
		return importance;
		
	}

	public int getNumberOfPixels() {
		
		return length;
		
	}

	//something to get the necessary array from starting (x1, y1) to ending (x2, y2)
	//in pixel values - endX <= 128 endY <= 54
	public ArrayList<int[]> getSpecificPixels(int startX, int startY, int endX, int endY) {
		
		//endX -= 5;
		//endY -= 5;
		ArrayList<int[]> temp = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(gamePositions, (roundUp(startY) * width) + roundUp(startX), 1 + (roundUp(endY) * width) + roundUp(endX))));
		
		ArrayList<int[]> pos = new ArrayList<>();
		
		for (int i = roundUp(startY); i < roundUp(endY); i++) {
			
			for (int j = roundUp(startX); j < roundUp(endX); j++) {
				
				pos.add(gamePositions[(i * width) + j]);
				
			}
			
		}
		
		//System.out.println(gamePositions[gamePositions.length - 1][0]);
		//System.out.println(temp.get(temp.size() - 1)[0]);
		
		return pos;
		
	}
	
	public ArrayList<Color> getSpecificColors(int startX, int startY, int endX, int endY) { 
		
		//endX -= 5;
		//endY -= 5;
		//return new ArrayList<>(Arrays.asList(Arrays.copyOfRange(colors, (roundUp(startY) * width) + roundUp(startX), 1 + (roundUp(endY) * width) + roundUp(endX))));
		
		ArrayList<Color> c = new ArrayList<>();
		
		for (int i = roundUp(startY); i < roundUp(endY); i++) {
			
			for (int j = roundUp(startX); j < roundUp(endX); j++) {
				
				c.add(colors[(i * width) + j]);
				
			}
			
		}
		
		
		return c;
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

	private int roundUp(int num) {
		
		return ((int) Math.ceil(num / 5.0));
		
	}
}












/*

useful dont touch

					
if (Math.abs(pixelColor.getGreen() - pixelColor.getGreen()) > 2 && Math.abs(pixelColor.getRed() - colorToRemove.getRed()) > 2 && Math.abs(pixelColor.getBlue() - colorToRemove.getBlue()) > 2) {
	pixelsTemp.add(Color.rgb(pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue()));
	positionsTemp.add(new int[] {j, i});
}
					
				

*/