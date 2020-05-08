package objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import exceptions.ImageException;
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
	private int[][] positions;
	private Color[] colors;
	
	
	public Object(int x, int y, String fileName, String objectName) {
		
		this.x = x;
		this.y = y;
		this.fileName = fileName;
		this.objectName = objectName;
		
	}
	
	public void removeBackground(Color colorToRemove) {
		
		if (image == null) {
			throw new ImageException("No attempt made to get image from system.");
		}
			
		for (int i = 0; i < Constant.imageHeight; i++) {
			
			for (int j = 0; j < Constant.imageWidth; j++) {
				
				java.awt.Color pixelColor = new java.awt.Color(image.getRGB(j, i));
				
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
		
		if (positionsTemp.size() != colorsTemp.size()) {
			throw new ImageException("yurr not supposed to see this shoot it if this appears");
		}
		
		positions = new int[positionsTemp.size()][2];
		colors = new Color[colorsTemp.size()];
		
		int leftMostPixel = positionsTemp.get(0)[0];
		int rightMostPixel = positionsTemp.get(0)[0];
		
		int topMostPixel = positionsTemp.get(0)[1];
		int bottomMostPixel = positionsTemp.get(0)[1];
		
		for (int i = 1; i < positionsTemp.size(); i++) {
			
			leftMostPixel = Math.min(leftMostPixel, positionsTemp.get(i)[0]);
			rightMostPixel = Math.max(leftMostPixel, positionsTemp.get(i)[0]);
			
			topMostPixel = Math.min(leftMostPixel, positionsTemp.get(i)[1]);
			bottomMostPixel = Math.max(leftMostPixel, positionsTemp.get(i)[1]);		
			
		}
		
		for (int i = 0; i < positions.length; i++) {
			
			positions[i] = positionsTemp.get(i);
			colors[i] = colorsTemp.get(i);	
			
		}
		
		width = rightMostPixel - leftMostPixel + 1;
		height = bottomMostPixel - topMostPixel + 1;
		
	}		
	
	public int[][] getInitialGamePixelPositions() {
		
		int[][] temp = new int[positions.length][2];
		
		for (int i = 0; i < positionsTemp.size(); i++) {
			temp[i] = new int[] {positions[i][0] * 5, positions[i][1] * 5};
		}	
		
		return temp;
	}
	
	public Color[] getInitialPixelColor() {

		Color[] temp2 = new Color[colorsTemp.size()];
		
		for (int i = 0; i < colorsTemp.size(); i++) {
			temp2[i] = colorsTemp.get(i);
		}
		
		return temp2;
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
	
}












/*

useful dont touch

					
if (Math.abs(pixelColor.getGreen() - pixelColor.getGreen()) > 2 && Math.abs(pixelColor.getRed() - colorToRemove.getRed()) > 2 && Math.abs(pixelColor.getBlue() - colorToRemove.getBlue()) > 2) {
	pixelsTemp.add(Color.rgb(pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue()));
	positionsTemp.add(new int[] {j, i});
}
					
				

*/