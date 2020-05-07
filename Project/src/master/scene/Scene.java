package master.scene;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import master.exceptions.NameException;
import master.exceptions.SceneException;
import master.object.Object;
import master.variables.Program;

public class Scene extends Canvas {

	private int numberChildren = 0;
	private int[] pixels = new int[172800];
	private Color[] color = new Color[172800];
	//private Map<String, Integer> importanceValues = new HashMap<>();
	private Object[] importanceValues = new Object[100];
	private int[] test = new int[100];
	//private Object[] importanceValues = new Object[100];
	private Map<String, Object> children = new HashMap<>();
	private String sceneName, sceneID;
	private GraphicsContext gc;
	private int[][] grr = new int[172800][3];
	private Color[] grr2 = new Color[172800];
	
	public Scene(String sceneName, String objectName) {
		
		if (sceneName.equals("Canvas")) {
			throw new NameException("Cannot name 'Canvas'");
		}
		
		this.sceneName = sceneName;
		sceneID = "Canvas." + this.sceneName;
		setId(sceneID);
		
		setWidth(670);
		setHeight(270);
		
		for (int i = 0; i < test.length; i++) {
			
			test[i] = -1;
	
		}
		
		gc = this.getGraphicsContext2D();
	}
	

	//test and importance values are ordered with greatest importance first
	//cannot change importance after is set
	public final void addChild(Object child, int importance) {
		
		checkError(importance);
		
		for (Map.Entry<String, Object> entry : children.entrySet()) {
			if (entry.getValue().getName().equals(child.getName())) {
				throw new NameException("Object with same name already exists.");
			}
		}
				
		for (int i = 0; i < test.length; i++) {
			
			if (importance >= test[i]) {
			
				for (int j = test.length - 1; j > i; j--) {
					test[j] = test[j - 1];
					importanceValues[j] = importanceValues[j - 1];
				}
					
				test[i] = importance;
				importanceValues[i] = child;
				
				break;
			}
			
		}
		
		children.put(child.getName(), child);
		numberChildren ++;
	}
	
	private final void removeChild() {
		
	}
	
	public void test() {
		
		for (int i = importanceValues.length - 1; i >= 0; i--) {
			if (importanceValues[i] != null) {
				
				Color[] temp = importanceValues[i].getPixels();
				int[][] temp2 = importanceValues[i].getPositions();
				
				for (int j = 0; j < temp.length; j++) {
					grr2[(temp2[j][0]) + (temp2[j][1] * 640)] = temp[j]; //fills grr2 from 0 to 172800
					//System.out.println((temp2[j][0]) + (temp2[j][1] * 640));
				}
				
				
			}
		}
		
	}
	
	
	public void test2() {
		
		
		
		Color[] arr = grr2;
		
		long start = System.nanoTime();
		
		for (int i = 0; i < 270; i++) {
			for (int j = 0; j < 640; j++) {
				
				gc.setFill(arr[(j) + (i * 640)]); //parses grr2 linearly from 0 to 172800
				gc.fillRect(j, i, 1, 1); //sets pixel

			}
			
		}
		
		long end = System.nanoTime();
		long diff = end - start;
		System.out.println(diff + " time in nano seconds");
		System.out.println(diff / 1000000 + " time in milli seconds");
		
	}
	
	public void test3() {
		
		long start = System.nanoTime();
		
		for (int i = 0; i < 640; i++) {
			for (int j = 0; j < 270; j++) {
				
				gc.setFill(grr2[(i) + (j * 640)]); //parses grr2 linearly from 0 to 172800
				gc.fillRect(i, j, 1, 1); //sets pixel

			}
			
		}
		
		long end = System.nanoTime();
		long diff = end - start;
		System.out.println(diff + " time in nano seconds");
		System.out.println(diff / 1000000 + " time in milli seconds");
		
	}
	
	public void test4() {
		
		long start = System.nanoTime();
		
		Color[] temp = importanceValues[0].getPixels();
		int[][] temp2 = importanceValues[0].getPositions();
		
		for (int j = 0; j < temp.length; j++) {
			
			gc.setFill(Color.rgb((int) (temp[j].getRed() * 256), (int) (temp[j].getGreen() * 256), (int) (temp[j].getBlue() * 256)));
			gc.fillRect(temp2[j][0], temp2[j][1], 1, 1);

		}

		long end = System.nanoTime();
		long diff = end - start;
		System.out.println(diff + " time in nano seconds");
		System.out.println(diff / 1000000 + " time in milli seconds");
	}
	
	public void test5() {
		
		Runnable one = new Runnable(){
		    public void run(){
		    	
		    	for (int i = 0; i < 270; i++) {
					for (int j = 0; j < 640; j++) {
						
						gc.setFill(grr2[(j) + (i * 640)]); //parses grr2 linearly from 0 to 172800
						gc.fillRect(j, i, 1, 1); //sets pixel

					}
					
				}
		    	
		    }
		};
		
		Thread thread = new Thread(one);
		thread.run();
		
	}
	
	public void test6() {
		
		int num = 1;
		Thread[] arr2 = new Thread[num];

		for (int j = 0; j < num; j++) {
			
			//arr2[j] = new Thread(ayoo(j, num));
		}
		
		
		long start = System.nanoTime();
		
		for (int i = 0; i < num; i++) {
			arr2[i].run();
		}
		
		long end = System.nanoTime();
		long diff = end - start;
		System.out.println(diff + " time in nano seconds");
		System.out.println(diff / 1000000 + " time in milli seconds");
	}
	
	/*
	private Runnable ayoo(final int j, int num) {
		
		
		Runnable reee = new Runnable(){
			int grrrr = 172800 / num; //amount of pixels each thread loops through
			Color[] temp = Arrays.copyOfRange(grr2, grrrr * j, (grrrr * j) + grrrr); //array of those pixels
			
			int pos = j * grrrr; //the pixel coordinate (in 172800) 
			
		    public void run(){
		    	
		    	for (int i = 0; i < grrrr; i++) {
		    		
		    		//gc.setFill(grr2[(j * grrrr) + i]); //grab fill 
		    		gc.setFill(temp[i]); //grab fill 
					gc.fillRect((pos % 640) + i, (pos - (pos % 640)) / 640, 1, 1);  //set it
		    		
		    	}
		    	
		    }
		};
		
		return reee;
	}
	*/
	
	public void test7() {
		
		int threadsPerRow = 1;
		int totalThreads = threadsPerRow * 270; 
		int pixelsPerThread = 640 / threadsPerRow;
		Thread[] arr2 = new Thread[totalThreads];

		int counter = 0;
		for (int i = 0; i < 270; i++) {
			for (int j = 0; j < threadsPerRow; j++) {
				arr2[counter] = new Thread(ayoo(i, j, pixelsPerThread));
				counter++;
			}
		}
		
		long start = System.nanoTime();
		
		for (int i = 0; i < totalThreads; i++) {
			ExecutorService executor = Executors.newCachedThreadPool();
			executor.execute(arr2[i]);
			
		}
		
		
		
		long end = System.nanoTime();
		long diff = end - start;
		System.out.println(diff + " time in nano seconds");
		System.out.println(diff / 1000000 + " time in milli seconds");
	}
	
	private Runnable ayoo(int rowNumber, int threadNumber, int numberOfPixelsPerThread) {
		
		Runnable reee = new Runnable(){			 
			
		    public void run(){
		    	
		    	int pos = (640 * rowNumber) + (threadNumber * numberOfPixelsPerThread);
				Color[] temp = Arrays.copyOfRange(grr2, pos, pos + numberOfPixelsPerThread); 
		    	
		    	for (int i = 0; i < numberOfPixelsPerThread; i++) {
		    		//System.out.println(pos);
		    		gc.setFill(temp[i]); 
					gc.fillRect(i + (threadNumber * numberOfPixelsPerThread), rowNumber, 1, 1);  //set it
		    		
		    	}
		    	
		    }
		};
		
		return reee;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private int[] a = new int[172800];
	private Color[] b = new Color[172800];
	
	
	public void create(Object obj) {
		
		b = obj.getPixels();
		
	}
	
	public void draw() {
		
		final int numberOfThread = 2;
		final int pixelsPerThread = 172800 / numberOfThread;
		
		int[][][] threadPixels = new int[numberOfThread][pixelsPerThread][2];
		Color[][] threadColor = new Color[numberOfThread][pixelsPerThread];
		
		int c2 = 0;
		
		for (int threadNumber = 0; threadNumber < numberOfThread; threadNumber++) {
			
			int[][] pixels = new int[pixelsPerThread][2];
			Color[] color = new Color[pixelsPerThread];
			
			int counter = 0;
			
			for (int i = 0; i < pixelsPerThread; i++) {
				
				color[i] = b[(threadNumber * pixelsPerThread) + i];
				
				pixels[i][0] = i - (640 * counter);
				pixels[i][1] = c2;
				
				if (i - ((640 * (counter + 1)) - 1) == 0 && i != 0) {
					counter ++;
					c2++;
				}
				
			}
			
			//for (int i = threadNumber * pixelsPerThread; i < (threadNumber * pixelsPerThread) + pixelsPerThread; i++) {
			//	color[i] = b[i];
			//}
			
			threadColor[threadNumber] = color;
			threadPixels[threadNumber] = pixels;
			
		}
		
		/*
		int thread = 1;
		int position = 0;
		System.out.println(threadPixels[thread][position][0] + " " + threadPixels[thread][position][1]);
		
		position = 1;
		System.out.println(threadPixels[thread][position][0] + " " + threadPixels[thread][position][1]);
		
		
		position = 640;
		System.out.println(threadPixels[thread][position][0] + " " + threadPixels[thread][position][1]);
		
		position = 641;
		System.out.println(threadPixels[thread][position][0] + " " + threadPixels[thread][position][1]);
		
		position = 1280;
		System.out.println(threadPixels[thread][position][0] + " " + threadPixels[thread][position][1]);
		
		position = 1281;
		System.out.println(threadPixels[thread][position][0] + " " + threadPixels[thread][position][1]);
		*/
		
		
		
		for (int threadNumber = 0; threadNumber < numberOfThread; threadNumber++) {
			
			//final int t = threadNumber;
			Color[] colors = threadColor[threadNumber];
			int[][] positions = threadPixels[threadNumber];
			
			System.out.println(colors.length);
			System.out.println(positions.length);
			System.out.println(threadNumber);
			
			ExecutorService executor = Executors.newCachedThreadPool();
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					
					for (int i = 0; i < pixelsPerThread; i++) {
						
						gc.setFill(colors[i]);
						gc.fillRect(positions[i][0], positions[i][1], 1, 1);
						
					}
					
					//for (int i = t * (172800 / numberOfThread); i < (t * (172800 / numberOfThread)) + (172800 / numberOfThread); i++) {	
					//}
					
				}
				
			});
			
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void update() {
		
		//iterate through importancevalues end of array to front
		//because want to draw least important first
		
		long start = System.nanoTime();
		
		for (int i = importanceValues.length - 1; i >= 0; i--) {
			
			if (importanceValues[i] != null) {

				Color[] temp = importanceValues[i].getPixels();
				int[][] temp2 = importanceValues[i].getPositions();
				
				for (int j = 0; j < temp.length; j++) {
					
					gc.setFill(Color.rgb((int) (temp[j].getRed() * 256), (int) (temp[j].getGreen() * 256), (int) (temp[j].getBlue() * 256)));
					gc.fillRect(temp2[j][0], temp2[j][1], 1, 1);

				}

			}
			
		}
		
		long end = System.nanoTime();
		long diff = end - start;
		System.out.println(diff + " time in nano seconds");
		System.out.println(diff / 1000000 + " time in milli seconds");
		
	}
	
	public void fill(Color color) {
		
		gc.setFill(color);
		
		for (int i = 0; i < 270; i++) {
			for (int j = 0; j < 640; j++) {
				
				gc.fillRect(j, i, 1, 1);

			}
			
		}
		
	}
	
	public final void hide() {
		
		Program.mainPane.getChildren().remove(this);
		
	}
	
	public final void show() {
		
		Program.mainPane.getChildren().add(this);
		
	}

	private void checkError(int importance) {
		
		if (importance < 0 || importance > 100) {
			throw new SceneException("Importance must be between 0 (inclusive) and 100 (inclusive)");
		}
		
		if (numberChildren + 1 > 100) {
			throw new SceneException("Too many children, maximum 100.");
		}
		
	}
	
}

/* USEFUL do not touch


ObservableList<Node> nodes = Program.mainPane.getChildrenUnmodifiable();

		if (nodes != null) {
			
			for (Node node : nodes) {

				if (node.getId() != null) {
					
					if (node.getId().equals(sceneID)) {
			
						super.toBack();
						break;
			
					}
					
				}
				
			}
			
		}



*/