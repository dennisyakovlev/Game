package master.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.scene.canvas.GraphicsContext;
import master.exceptions.ImageException;
import master.object.Object;
import master.variables.Program;

public class Image extends Object {	
	
	private int length;
	private String name;
	private String classPath;
	private FileInputStream inputStream = null;
	private BufferedImage image = null;
	private ArrayList<Color> pixels = new ArrayList<>();
	private ArrayList<int[]> positions = new ArrayList<>();
	
	
	public Image(String name) {
		this.name = name;
		
		setImage();
	}

	private void setImage() {

		try {
			classPath = new File(Image.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent().toString();
			inputStream = new FileInputStream(classPath + "\\src\\image\\files\\" + name);
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
		
		for (int i = 0; i < 270; i++) {
			for (int j = 0; j < 640; j++) {
				
				Color color = new Color(image.getRGB(j, i));

				if (Math.abs(color.getGreen() - toRemove.getGreen()) > 2 && Math.abs(color.getRed() - toRemove.getRed()) > 2 && Math.abs(color.getBlue() - toRemove.getBlue()) > 2) {
					pixels.add(color);
					positions.add(new int[] {j, i});
				}

			}
			
		}

	}
	
	public void test() {
		GraphicsContext gc = Program.canvas.getGraphicsContext2D();
		
		gc.fillRect(639, 269, 1, 1);
		for (int i = 0; i < positions.size(); i++) {
			gc.setFill(javafx.scene.paint.Color.rgb(pixels.get(i).getRed(), pixels.get(i).getGreen(), pixels.get(i).getBlue()));
			gc.fillRect(positions.get(i)[0], positions.get(i)[1], 1, 1);
		}
	}
	
}
