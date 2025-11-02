package finalgame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JPanel;
// NEED TO FIGURE OUT WHREWE TO CALL REPAINT();


public class Level extends JPanel{ // is a jpanel(?)
	MovementManager movementManager = new MovementManager(); // maybe we feed the movement manager objects (in method not global), and it can act on those internally.
	InputManager inputManager = new InputManager();
	// those objects are the components.
	ArrayList<Stage> objects = new ArrayList<>();// maybe we should have one for each classe. // change to gameobjects
	Player player;
	int requiredscore; // score needed to pass a level
	
	Stage stage = new Stage("tennis.png");
	
	
	// add or remove objects from view
	// update objects' movement
	
	public Level() { //temporary to not have issue with undefined player in below method
		player = new Player();
		
		this.setLayout(new BorderLayout()); // figure out what parameters to use if we want them later
		this.add(stage, BorderLayout.CENTER);
//        this.setBackground(stage.testingColor);

//		objects.add(new Item(0, 50, 80, new String[]{""}, new Sprite[] {new Sprite(30, 30, "tennis.png")}));
		objects.add(new Stage("tennis.png", 50, 50));
		
		for (Stage obj : objects) {
			this.add(obj); // need to figure out how to specity where or if it's not needed
		}
//		this.add(player);
//		this.setPreferredSize(new Dimension(700, 400));
		repaint();
//		this.add();
	}
	
//	public void periodic() { // temporary method to hold logic, we assume this will be called every frame, but have not implemented it.
//		for (GameObject object:objects) {
//			if (movementManager.checkOverlap(player, object)) {
//				
//			}
//			else {
//				object.update();
//			}
//		}
//		
//	}
}
// maybe just 