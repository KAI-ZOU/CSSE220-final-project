package finalgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.management.openmbean.KeyAlreadyExistsException;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;
// NEED TO FIGURE OUT WHREWE TO CALL REPAINT();


public class Level extends JPanel{ // is a jpanel(?) // maybe change actionlist and keylist
//	MovementManager movementManager = new MovementManager(); // maybe we feed the movement manager objects (in method not global), and it can act on those internally.
//	InputManager inputManager = new InputManager();
	Timer timer;
	// those objects are the components.
	ArrayList<GameObject> objects = new ArrayList<>();// maybe we should have one for each classe. // change to gameobjects
	Player player;
	int requiredscore; // score needed to pass a level
	
	Stage stage = new Stage("sceneTest.png");
	
	
	
	private final Set<Integer> pressedKeys = new HashSet<>(); // 
	int prevX;
	int prevY;
	ArrayList<GameObject> toRemove = new ArrayList<>();
	
	long pastTime = System.currentTimeMillis();
	long iFrame = 300;
	
	
	// add or remove objects from view
	// update objects' movement
	
	public Level() { 
		
		player = new Player(0, 300, 50, new String[]{""}, new Sprite[] {new Sprite(30, 30, "playerTest.png")});
//		
//		this.setLayout(new BorderLayout()); // figure out what parameters to use if we want them later
//		this.add(stage, BorderLayout.CENTER);
//		this.add(stage);
//        this.setBackground(stage.testingColor);

		this.setPreferredSize(new Dimension(Stage.WIDTH, Stage.HEIGHT));
		this.setBackground(Color.BLACK);
//		this.add(stage);
		objects.add(new Item(0, 300, 100,0,100, 0, 1, new String[]{""}, new Sprite[] {new Sprite(30, 30, "itemTest.png")}));
		objects.add(new Platform(0, 200, 240, new String[]{""}, new Sprite[] {new Sprite(200, 15, "tennis.png")}));
		objects.add(new Platform(0, 300, 270, new String[]{""}, new Sprite[] {new Sprite(200, 15, "tennis.png")}));

		objects.add(new Platform(0, 50, 320, 0, 60, 0, 0, new String[]{""}, new Sprite[] {new Sprite(200, 15, "tennis.png")}));
		objects.add(new Platform(0, 50, 490, 0, 140, 0, 2, new String[]{""}, new Sprite[] {new Sprite(700, 15, "tennis.png")}));

		objects.add(new Platform(0, 700, 400, 0, 120, 0, 1, new String[]{""}, new Sprite[] {new Sprite(200, 15, "tennis.png")}));

		objects.add(new Platform(0, 450, 340, 0, 60, 0, 1, new String[]{""}, new Sprite[] {new Sprite(200, 15, "tennis.png")}));

		objects.add(new Enemy(0, 450, 120, 100, 20, 2, 1, new String[]{""}, new Sprite[] {new Sprite(55, 55, "enemyTest.png")}));
//		objects.get(2).update();
		repaint();
		
//		timer = new Timer(30, e -> tick());
//		timer.start();
		
		timer = new Timer(16, e-> tick());
		timer.start();
		buildKeys();
	}
	
	public void tick() {
		for (GameObject obj: objects) {
			obj.update();
		}
		if (!toRemove.isEmpty()) {
			for (GameObject obj : toRemove) {
				objects.remove(obj);
			}
		}
	    handleInput();
		player.onGround = false;
		prevX = player.xPosition;
		prevY = player.yPosition;
		player.update();
		for (GameObject obj:objects) {
			int leftmostPlayer = player.xPosition;
			int rightmostPlayer = player.xPosition + player.usedSprite.width;
			int topmostPlayer = player.yPosition;
			int bottommostPlayer = player.yPosition + player.usedSprite.height;

			int leftmostObject = obj.xPosition;
			int rightmostObject = obj.xPosition + obj.usedSprite.width;
			int topmostObject = obj.yPosition;
			int bottommostObject = obj.yPosition + obj.usedSprite.height;
			// the collision code was very annoying and not fun. probably cutting hit/hurtboxes (multiple) and just doing a single bounding box for everything. Note: remove hp from entity class if it has it, only give it to player.
			// below code checks for overlapping in both dimensions. not sure why using javax' intersects() method doesn't work the same. seems like that returns true if they touch edges, but not sure why that affects the result.
			if ((rightmostPlayer > leftmostObject && leftmostPlayer < rightmostObject) && (bottommostPlayer > topmostObject && topmostPlayer < bottommostObject)) {
//			if (player.usedSprite.boundingBox.intersects(obj.usedSprite.boundingBox)) { // NEED TO IMPLEMENT STUFF FOR ENEMIES/ITEMS THAT SHOULD NOT SHOVE THE PLAYER (JUST A SIMPLE CONDITIONAL CHECK FOR INTERACTABLE) THEN IF NOT INTERACTABLE HAVE OTHER CASES, OR SOMETHING. MAY WANT TOIMPLEMENT MULTIPLE COLLISION BOXES (BOTH HITBOXES AND HURTBOXES?)
				if (obj instanceof Platform) {
					Platform platform = (Platform) obj; // may not need this.
					// will need a check in here for hurting platforms. consider doing something since we'll have similar stuff below for normal enemies etc.

						// also only does collisions for the player, not enemy and platform/enemy and enemy/enemy and item/etc
						// assumes one rectangle for both objects

						// find overlaps
						    int overlapRight = rightmostPlayer - leftmostObject;
						    int overlapLeft = rightmostObject - leftmostPlayer;
						    int overlapBottom = bottommostPlayer - topmostObject;
						    int overlapTop = bottommostObject - topmostPlayer;
						
						    // for now, assume the smaller overlap value is the one that should be correct
						    int minOverlapX = Math.min(overlapRight, overlapLeft);
						    int minOverlapY = Math.min(overlapBottom, overlapTop);

						    // corrects x-axis if it would displace less than correcting the y-axis
						    if (minOverlapX < minOverlapY) {
						    	// corrects by moving the player to the right if the right overlap is smaller than the left (similar to above)
						        if (overlapRight < overlapLeft) {
						        	player.xPosition -= overlapRight; // note: this is the same as saying minoverlapx. just better for readability, i guess.
						        } 
						        else {
						        	player.xPosition += overlapLeft;
						        }
						        player.velocityX = 0; // velocity is set to 0 to prevent possible unnecessary corrections next cycle and to simulate what would happen in real life
						    } 
						    // corrects y-axis
						    else {
						        // corrects by moving player up if the upwards displacement is smaller than the downwards
						        if (overlapBottom < overlapTop) {
						        	player.yPosition -= overlapBottom;
						            player.onGround = true; // also sets onGround so the player can jump
						        } 
						        else {
						        	player.yPosition += overlapTop;
						        }
						        player.velocityY = 0;
						}
//						// for damaging do a thing with a translucent background to make the screen part red. also maybe control the short iframes here.
//					
				}
				
				else if (obj instanceof Enemy) {
					Enemy enemy = (Enemy) obj; // may not need this.
//					if (pressedKeys.contains(KeyEvent.VK_Q)) {
						enemy.hitPlayer = true;
						
					    if (System.currentTimeMillis()-pastTime>=iFrame) {
					    	enemy.hitPlayer = true;
					    	pastTime = System.currentTimeMillis();
							player.velocityX-=7; // update to have it work no matter what the collision direction is

					    }					
//					else {
//						enemy.hitPlayer = false;
//					}
//					enemy.hitPlayer = true;
				}
				else if (obj instanceof Item) {
					Item item = (Item) obj;
					if (pressedKeys.contains(KeyEvent.VK_E)) {
						item.collected = true;
//						objects.remove(obj); // does not work. crashes game (modifying arraylist as we iterate).
						toRemove.add(obj); // maybe add a flag for respawning per level, where whenever an item is removed, another one spawns in (between a platform's top x and 10 pixels above, accounting for movement)

					}
				}
			}
				
			}
		

		repaint();
	}
	
	private void handleInput() {
	    int accelX = 0;
	    int accelY = 0;

	    if (pressedKeys.contains(KeyEvent.VK_LEFT)) { // could try doing an interesting thing with afterimages possibly (or afterimages of eyes)
	    	accelX -= 1;
	    }
	    if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
	    	accelX += 1;
	    }
	    if ((pressedKeys.contains(KeyEvent.VK_UP) || pressedKeys.contains(KeyEvent.VK_SPACE))&&player.onGround) {
	    	accelY -= 13; // note: should be relatively high so that player immediately gains max vertical speed since we can only jump while touching a platform...?
	    }
	    else {
	    	accelY +=1;
	    }

	    player.applyAcceleration(accelX, accelY);
	}
	
private void buildKeys() {
		
	this.setFocusable(true);
    this.requestFocusInWindow();

    this.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            pressedKeys.add(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            pressedKeys.remove(e.getKeyCode());
        }
    });
		
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
}