package objects.gameobjects;

import java.util.ArrayList;

import objects.Object;

public class GameObject extends Object {
	
	private int totalAngleInDeg = 0;
	private ArrayList<int[]> original = new ArrayList<>();
	private ArrayList<int[]> outSide = new ArrayList<>();
	private boolean mustSet = true;
	private double angleToUse;
	
	public GameObject() {
		
	}
	
	public GameObject(int x, int y, String sectionName, String fileName, String objectName) {
		
		super(x, y, sectionName, fileName, objectName);
		
	}
	
	public void setOutside() {

		ArrayList<int[]> positions = getPositions();

		outSide.add(positions.get(0));
		
		for (int i = 1; i < positions.size() - 1; i++) {
			
			final int[] previous = positions.get(i - 1);
			final int[] current = positions.get(i);
			final int[] next = positions.get(i + 1);
			
			if (next[1] != current[1] || previous[1] != current[1] || (next[0] + previous[0]) / 2 != current[0]) {

				outSide.add(current);

			
			} else if (current[1] == positions.get(0)[1]) {
				
				outSide.add(current);

				
			}
			
		}

		outSide.add(positions.get(positions.size() - 1));
		
	
		
	}
	
	public void translateY(int val) {

		if (mustSet) {
			
			for (int[] i : getPositions()) {
				
				original.add(i);
				
			}
			
			mustSet = false;
			
		}
		
		for (int i = 0; i < getPositions().size(); i++) {
			
			getPositions().set(i, new int[] {getPositions().get(i)[0], getPositions().get(i)[1] + val});
			original.set(i, new int[] {original.get(i)[0], original.get(i)[1] + val});
			
		}
		
		for (int i = 0; i < outSide.size(); i++) {
			
			outSide.set(i, new int[] {outSide.get(i)[0], outSide.get(i)[1] + val});
			
		}
		
		setY(getY() + val);
		
	}
	
	public void translateX(int val) {
		
		if (mustSet) {
			
			for (int[] i : getPositions()) {
				
				original.add(i);
				
			}
			
			mustSet = false;
			
		}
		
		for (int i = 0; i < getPositions().size(); i++) {
			
			getPositions().set(i, new int[] {getPositions().get(i)[0] + val, getPositions().get(i)[1]});
			original.set(i, new int[] {original.get(i)[0] + val, original.get(i)[1]});
			
		}
		
		for (int i = 0; i < outSide.size(); i++) {
			
			outSide.set(i, new int[] {outSide.get(i)[0] + val, outSide.get(i)[1]});
			
		}
		
		setX(getX() + val);
		
	}
	
	public boolean collision(GameObject gameObject) {

		ArrayList<int[]> otherOutside = gameObject.getOutsidePixels();
		
		for (int i = 0; i < outSide.size(); i++) {
			
			for (int j = 0; j < otherOutside.size(); j++) {

				if ((outSide.get(i)[0] == otherOutside.get(j)[0]) && (outSide.get(i)[1] == otherOutside.get(j)[1])) {
					
					return true;
					
				}
				
			}
			
		}

		return false;
		
	}
	
	/**
	 * 
	 * @param pixelNum
	 * @param angleInDegrees
	 * 
	 * @author Dennis
	 */
	public void rotate(int pixelNum, int angleInDegrees, double maxAngle) {
		
		ArrayList<int[]> pos = getPositions();
		
		if (mustSet) {
			
			for (int[] i : getPositions()) {
				
				original.add(i);
				
			}
			
		}

		totalAngleInDeg += angleInDegrees;

		angleToUse = -(maxAngle) * (Math.sin(totalAngleInDeg * (Math.PI / 180)));
		//double angleToUse = (totalAngleInDeg * (Math.PI / 180));
		
		//System.out.println(angleToUse);
		
		//double totalAngleInRad = totalAngleInDeg * (Math.PI / 180);
		
		
		final int originX = original.get(pixelNum)[0];
		final int originY = original.get(pixelNum)[1];
		
		for (int i = 0; i < pos.size(); i++) {

			final int X = original.get(i)[0] - originX;
			final int Y = original.get(i)[1] - originY;
			
			final int x1 = (int) (X - (Y * Math.tan(angleToUse / 2)));
			final int y1 = Y;
			
			final int x2 = x1;
			final int y2 = (int) ((x1 * Math.sin(angleToUse)) + y1);
			
			final int x3 = (int) (x2 - (y2 * Math.tan(angleToUse / 2)));
			final int y3 = y2;
			
			//System.out.println(x1 + " " + y1);
			
			
				
			pos.set(i, new int[] { originX +  x3, originY + y3});
			
			
				
		}

	}

	public double getCurrentAngle() {
		
		return angleToUse;
		
	}
	
	public ArrayList<int[]> getOutsidePixels() {
		
		return outSide;
		
	}
	
}

/*


public void rotate(int pixelNum, int angleInDegrees) {
		
		ArrayList<int[]> pos = getPositions();
		
		if (original.size() == 0) {
			
			for (int[] i : getPositions()) {
				
				original.add(i);
				
			}
			
		}

		totalAngleInDeg += angleInDegrees;

		//double angleToUse = -(Math.PI / 2) * (Math.sin(totalAngleInDeg * (Math.PI / 180)));
		double angleToUse = (totalAngleInDeg * (Math.PI / 180));
		
		System.out.println(angleToUse);
		
		//double totalAngleInRad = totalAngleInDeg * (Math.PI / 180);
		
		
		final int originX = original.get(pixelNum)[0];
		final int originY = original.get(pixelNum)[1];
		
		for (int i = 0; i < pos.size(); i++) {

			final int X = original.get(i)[0] - originX;
			final int Y = original.get(i)[1] - originY;
			
			final int x1 = (int) (X - (Y * Math.tan(angleToUse / 2)));
			final int y1 = Y;
			
			final int x2 = x1;
			final int y2 = (int) ((x1 * Math.sin(angleToUse)) + y1);
			
			final int x3 = (int) (x2 - (y2 * Math.tan(angleToUse / 2)));
			final int y3 = y2;
			
			//System.out.println(x1 + " " + y1);
			
			
				
			pos.set(i, new int[] { originX +  x3, originY + y3});
			
			
				
		}

	}

*/