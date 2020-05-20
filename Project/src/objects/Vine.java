package objects;

import java.util.ArrayList;

/**
 * Is a vine that can swing.
 * 
 * <br><br><STRONG>USAGE</STRONG>
 * <br>See {@linkplain Object}
 * call {@linkplain Vine#setOriginal()} after following above instructions
 * 
 * @author Dennis
 *
 */
public class Vine extends GameObject {

	/**
	 * The difference in value between the bottom and top pixel
	 * of the vine.
	 */
	private double max;
	
	/**
	 * The positions of the pixels relative to the vine itself before
	 * any external movements are performed on the vine.
	 * (The vine standing still)
	 * Used for reference in math.
	 */
	private ArrayList<int[]> originalPositions = new ArrayList<>();
	
	public Vine(int x, int y, String sectionName, String fileName, String objectName) {

		super(x, y, sectionName, fileName, objectName);
		
	}

	/**
	 * Sways the vine back a forth horizontally.
	 * 
	 * @param time The current continuous time in milliseconds, starting from 0. 
	 */
	public void swing(double time) {
		
		final double pi = Math.PI;
		final double largestTheta = (pi / 3) * Math.sin(((2 * pi) / 4000) * time);
		
		ArrayList<int[]> positions = getPositions();
		
	
		for (int i = 0; i < positions.size(); i++) {
			
			int y = originalPositions.get(i)[1];
			double C = max - y;
			double theta = largestTheta * (1 - (C / max));
			
			int X = originalPositions.get(i)[0] + (int) ( (max - C) * Math.sin(theta) );
			int Y = getY() + (int) ( y * Math.cos(theta) );
			
			positions.set(i, new int[] {X, Y});
			
		}
		
	}
	
	/**
	 * Sets the attributes of Vine.
	 */
	public void setOriginal() {
		
		ArrayList<int[]> reee = getPositions();

		for (int i = 0; i < reee.size(); i++) {
			
			originalPositions.add(new int[] {reee.get(i)[0], reee.get(i)[1]});
			
		}
		
		max = getHeight();
		
	}
	
	/**
	 * Moves the "still" vine in the horizontal direction.
	 */
	@Override
	public void translateX(int val) {
		
		for (int i = 0; i < originalPositions.size(); i++) {
			
			originalPositions.set(i, new int[] {originalPositions.get(i)[0] + val, originalPositions.get(i)[1]});
			
		}
		
		super.translateX(val);
	}

}