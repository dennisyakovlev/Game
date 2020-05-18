package objects;

public class GameObject extends Object {

	public GameObject(int x, int y, String sectionName, String fileName, String objectName) {
		
		super(x, y, sectionName, fileName, objectName);
		
		
		
	}
	
	public void rotate() {
		
		
		
	}
	
	public void translateY(int val) {

		
		
	}
	
	public void translateX(int val) {
		
		for (int i = 0; i < getPositions().size(); i++) {
			
			getPositions().set(i, new int[] {getPositions().get(i)[0] + val, getPositions().get(i)[1]});
			
		}
		
		setX(getX() + val);
		
	}
	
	public boolean collision(GameObject gameObject) {
		
		return false;
		
	}

}
