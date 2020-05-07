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

	private int x, y;
	private String fileName, objectName;
	private BufferedImage image;
	private ArrayList<Color> pixelsTemp = new ArrayList<>();
	private ArrayList<int[]> positionsTemp = new ArrayList<>();
	
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
					
					pixelsTemp.add(Color.rgb(pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue()));
					positionsTemp.add(new int[] {j, i});
					
				} else {
					
					if (Math.abs(pixelColor.getGreen() - pixelColor.getGreen()) > 2 && Math.abs(pixelColor.getRed() - colorToRemove.getRed()) > 2 && Math.abs(pixelColor.getBlue() - colorToRemove.getBlue()) > 2) {
						pixelsTemp.add(Color.rgb(pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue()));
						positionsTemp.add(new int[] {j, i});
					}
					
				}
				
				pixelsTemp.add(Color.rgb(pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue()));
				positionsTemp.add(new int[] {j, i});
				
			}
			
		}				 
		
	}
	
	public void getImage() {
		
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
	
	public void resize() {
		
		
		
	}
}
