package finalgame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;
// NEED TO FIGURE OUT WHREWE TO CALL REPAINT();


public class Level extends JPanel implements ActionListener, KeyListener{ // is a jpanel(?) // maybe change actionlist and keylist
	MovementManager movementManager = new MovementManager(); // maybe we feed the movement manager objects (in method not global), and it can act on those internally.
	InputManager inputManager = new InputManager();
	Timer timer;
	// those objects are the components.
	ArrayList<GameObject> objects = new ArrayList<>();// maybe we should have one for each classe. // change to gameobjects
	Player player;
	int requiredscore; // score needed to pass a level
	
	Stage stage = new Stage("sceneTest.png");
	
	
	// add or remove objects from view
	// update objects' movement
	
	public Level() { //temporary to not have issue with undefined player in below method
		
		player = new Player(0, 300, 150, new String[]{""}, new Sprite[] {new Sprite(30, 30, "playerTest.png")});
//		
//		this.setLayout(new BorderLayout()); // figure out what parameters to use if we want them later
//		this.add(stage, BorderLayout.CENTER);
//		this.add(stage);
//        this.setBackground(stage.testingColor);

		this.setPreferredSize(new Dimension(Stage.WIDTH, Stage.HEIGHT));
		objects.add(new Item(0, 300, 80, new String[]{""}, new Sprite[] {new Sprite(30, 30, "itemTest.png")}));
		objects.add(new Platform(0, 200, 50, new String[]{""}, new Sprite[] {new Sprite(200, 15, "tennis.png")}));
		objects.add(new Enemy(0, 400, 120, 150, 0, -2, 0, new String[]{""}, new Sprite[] {new Sprite(55, 55, "enemyTest.png")}));
//		objects.get(2).update();
		repaint();
		
//		timer = new Timer(30, e -> tick());
//		timer.start();
		
		timer = new Timer(16, e-> tick());
		timer.start();
	}
	
	public void tick() {
		objects.get(2).update();
		repaint();

	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
//		Graphics2D g2 = (Graphics2D) g;
		
		stage.paintComponent(g);
		for (GameObject obj : objects) {
			obj.paintComponent(g);
		}
		player.paintComponent(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
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