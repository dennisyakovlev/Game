package objects;

import java.util.ArrayList;

public class GameObject extends Object {
	
	private ArrayList<double[]> actualValues = new ArrayList<>();
	
	public GameObject(int x, int y, String sectionName, String fileName, String objectName) {
		
		super(x, y, sectionName, fileName, objectName);
			
	}
	
	public void rotate(int pixelNum, double angle) {
		
		ArrayList<int[]> pos = getPositions();
		
		if (actualValues.size() == 0) {
			
			for (int[] i : getPositions()) {
				
				actualValues.add(new double[] {(double) i[0], (double) i[1]});
				
			}
			
		}
		
		double angleInRad = angle * (Math.PI / 180);
		
		final double originX = actualValues.get(pixelNum)[0];
		final double originY = actualValues.get(pixelNum)[1];

		for (int i = 0; i < pos.size(); i++) {

			final double diffX = actualValues.get(i)[0] - pos.get(pixelNum)[0];
			final double diffY = actualValues.get(i)[1] - pos.get(pixelNum)[1];

			final double newX = originX + (diffX * Math.cos(angleInRad)) + (diffY * Math.sin(angleInRad));
			final double newY = originY + (diffX * -Math.sin(angleInRad)) + (diffY * Math.cos(angleInRad));
			
			actualValues.set(i, new double[] {newX, newY});

			if (i != pixelNum) {
				
				pos.set(i, new int[] { (int) Math.round((newX)), (int) Math.round((newY)) });
			
			}
				
		}

	}
	
	public void translateY(int val) {

		
		
	}
	
	public void translateX(int val) {
		
		for (int i = 0; i < getPositions().size(); i++) {
			
			getPositions().set(i, new int[] {getPositions().get(i)[0] + val, getPositions().get(i)[1]});
			actualValues.set(i, new double[] {actualValues.get(i)[0] + val, actualValues.get(i)[1]});
			
		}
		
		setX(getX() + val);
		
	}
	
	public boolean collision(GameObject gameObject) {
		
		return false;
		
	}

}