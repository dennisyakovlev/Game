package master.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import master.exceptions.ImageException;
import master.object.Object;

public class Image extends Object {	
	
	private int length;
	private String classPath;
	private FileInputStream inputStream = null;
	private BufferedImage image = null;
	private ArrayList<javafx.scene.paint.Color> pixels = new ArrayList<>();
	private ArrayList<int[]> positions = new ArrayList<>();
	private String imageName;
	
	
	public Image(String imageName, String objectName) {
		super(objectName);
		
		this.imageName = imageName;
		
		setImage();
	}

	private void setImage() {

		try {
			classPath = new File(Image.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent().toString();
			inputStream = new FileInputStream(classPath + "\\src\\image\\files\\" + imageName);
			image = ImageIO.read(inputStream);
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		
		if (image.getWidth() != 640 || image.getHeight() != 270) {
			
			classPath = "";
			inputStream = null;
			image = null;
			
			throw new ImageException("Expected image of size 640px by 270px, recieved " + image.getWidth() + "px by " + image.getHeight() + "px.");
		}

	}
	
	public void convertToUsable(Color toRemove) {
		
		if (toRemove == null) {
			
			directAdd();
			
		} else {
			
			indirectAdd(toRemove);
		
		}

		setData(pixels.toArray(new javafx.scene.paint.Color[pixels.size()]), positions.toArray(new int[positions.size()][2]));
	}
	
	
	private void directAdd() {
		
		for (int i = 0; i < 270; i++) {
			for (int j = 0; j < 640; j++) {
				
				Color color = new Color(image.getRGB(j, i));
				pixels.add(javafx.scene.paint.Color.rgb(color.getRed(), color.getGreen(), color.getBlue()));
				positions.add(new int[] {j, i});
				
			}
			
		}
		
	}
	
	private void indirectAdd(Color toRemove) {
	
		for (int i = 0; i < 270; i++) {
			for (int j = 0; j < 640; j++) {
				
				Color color = new Color(image.getRGB(j, i));

				if (Math.abs(color.getGreen() - toRemove.getGreen()) > 2 && Math.abs(color.getRed() - toRemove.getRed()) > 2 && Math.abs(color.getBlue() - toRemove.getBlue()) > 2) {
					pixels.add(javafx.scene.paint.Color.rgb(color.getRed(), color.getGreen(), color.getBlue()));
					positions.add(new int[] {j, i});
				}

			}
			
		}
		
	}

}
