package finalgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Level extends JPanel{
	Timer timer;
	ArrayList<GameObject> objects = new ArrayList<>();
	Player player;
	private int requiredscore = 60;
	private int score = 0;
	private boolean levelPassed = false;
	Stage stage = new Stage("sceneTest.png");
	private final int SPAWN_X = 400;
    private final int SPAWN_Y = 100;
    private final int INITIAL_HP = 100;
    private ArrayList<Platform> platforms = new ArrayList<>();
	
	private final Set<Integer> pressedKeys = new HashSet<>(); // 
	int prevX;
	int prevY;
	ArrayList<GameObject> toRemove = new ArrayList<>();
	
	long pastTime = System.currentTimeMillis();
	long iFrame = 1000;
	
	public Level() { 
		
		player = new Player(0, 300, 50, new String[]{""}, new Sprite[] {new Sprite(30, 30, "playerTest.png")});

		this.setPreferredSize(new Dimension(Stage.WIDTH, Stage.HEIGHT));
		this.setBackground(Color.BLACK);
		objects.add(new Item(0, 300, 100,0,100, 0, 1, new String[]{""}, new Sprite[] {new Sprite(30, 30, "itemTest.png")}, score));
		objects.add(new Platform(0, 200, 240, 70, 0, 1, 0, new String[]{""}, new Sprite[] {new Sprite(200, 15, "tennis.png")}));
		objects.add(new Platform(0, 300, 270, new String[]{""}, new Sprite[] {new Sprite(200, 15, "tennis.png")}));

		objects.add(new Platform(0, 50, 320, 0, 60, 0, 0, new String[]{""}, new Sprite[] {new Sprite(200, 15, "tennis.png")}));
		objects.add(new Platform(0, 50, 490, 0, 140, 0, 2, new String[]{""}, new Sprite[] {new Sprite(700, 15, "tennis.png")}));

		objects.add(new Platform(0, 700, 400, 0, 120, 0, 1, new String[]{""}, new Sprite[] {new Sprite(200, 15, "tennis.png")}));

		objects.add(new Platform(0, 450, 340, 0, 60, 0, 1, new String[]{""}, new Sprite[] {new Sprite(200, 15, "tennis.png")}));

		objects.add(new Enemy(0, 450, 120, 100, 20, 2, 1, new String[]{""}, new Sprite[] {new Sprite(55, 55, "enemyTest.png")}));
		spawnCoins(3); 
		repaint();
		
		
		timer = new Timer(16, e-> tick());
		timer.start();
		buildKeys();
	}
	private void spawnCoins(int count) {
        if (platforms.isEmpty()) return;

        Random rand = new Random();
        int coinWidth = 30; 
        int coinHeight = 30; 
        int coinScore = 20;

        for (int i = 0; i < count; i++) {
            Platform parentPlatform = platforms.get(rand.nextInt(platforms.size()));
            
            int spawnX = parentPlatform.xPosition + rand.nextInt(parentPlatform.usedSprite.width - coinWidth);
            int spawnY = parentPlatform.yPosition - coinHeight - rand.nextInt(30) - 55; 
            
            int range = 30; 
            int velocity = 1;

            Item coin = new Item(0, spawnX, spawnY, range, range, velocity * (rand.nextBoolean() ? 1 : -1), velocity * (rand.nextBoolean() ? 1 : -1), new String[]{""}, new Sprite[] {new Sprite(coinWidth, coinHeight, "itemTest.png")}, coinScore );
            objects.add(coin);
        }
    }
	private void respawnPlayer() {
	    // Resets player position and full HP
	    player.xPosition = SPAWN_X; 
	    player.yPosition = SPAWN_Y; 
	    player.hp = INITIAL_HP;
	    
	    
        score = 0;
        levelPassed = false; // Reset level goal status
	}

	public void tick() {
		 if (score >= requiredscore && !levelPassed) {
	            levelPassed = true;
	        }
		if (player.hp <= 0) {
            player.xPosition = 400; 
            player.yPosition = 100; 
            player.hp = 100;
        }
		if (player.yPosition > Stage.HEIGHT) {
            respawnPlayer();
        }
		if (player.hp <= 0) {
            respawnPlayer();
        }
		if (player.isInvincible && System.currentTimeMillis() - pastTime >= iFrame) {
            player.isInvincible = false;
        }
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
							player.xPosition+=platform.velocityX;
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
					    	if (player.hp > 0) { 
					    	    player.hp -= 20;
					    	    player.isInvincible = true;
					    		}
							player.velocityX-=7; // update to have it work no matter what the collision direction is

					    }					
				}
				else if (obj instanceof Item) {
					Item item = (Item) obj;
					if (pressedKeys.contains(KeyEvent.VK_E))
					{
						score += 20;
						toRemove.add(obj); // maybe add a flag for respawning per level, where whenever an item is removed, another one spawns in (between a platform's top x and 10 pixels above, accounting for movement)
						System.out.println("Coin Collected! New Score: " + score);
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
	    if (accelX == 0) {
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
	
	stage.paintComponent(g);
	for (GameObject obj : objects) {
		obj.paintComponent(g);
	}
	player.paintComponent(g);
    
    // Draw Player HP (Top Left)
    g.setColor(Color.RED);
    g.drawString("HP: " + player.hp + " / 100", 10, 20); // X=10, Y=20
    
    // Draw Score and Level Goal (Top Left, offset to the right of HP)
    g.setColor(Color.YELLOW);
    String scoreString = "SCORE: " + score + " / " + requiredscore;
    if (levelPassed) {
        scoreString += " - LEVEL COMPLETE!";
        g.setColor(Color.GREEN); // Change color when goal is met
    }
    // Moved score to X=180, Y=20 to be on the same line as HP but clearly separate
    g.drawString(scoreString, 180, 20); 
}
}