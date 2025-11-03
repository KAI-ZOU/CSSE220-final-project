package finalgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	
	private int currentLevel = 1; 
	private int requiredscore = 80; 
	private int score = 0;
	private boolean levelPassed = false;
	private boolean gameFinished = false; 
	Stage stage = new Stage("sceneTest.png");
	private final int SPAWN_X = 400;
    private final int SPAWN_Y = 100;
    private final int INITIAL_HP = 100;
    private ArrayList<Platform> platforms = new ArrayList<>();
	
	private final Set<Integer> pressedKeys = new HashSet<>();
	int prevX;
	int prevY;
	ArrayList<GameObject> toRemove = new ArrayList<>();
	
	long pastTime = System.currentTimeMillis();
	long iFrame = 1000;
	
	private static final int MIN_COIN_DISTANCE = 40;
	private static final int COIN_POINT_VALUE = 20; 

	public Level() { 
		this.setPreferredSize(new Dimension(Stage.WIDTH, Stage.HEIGHT));
		this.setBackground(Color.BLACK);
		
		player = new Player(0, SPAWN_X, SPAWN_Y, new String[]{""}, new Sprite[] {new Sprite(30, 30, "playerTest.png")});

		loadLevel(currentLevel); 
		
		repaint();
		
		
		timer = new Timer(16, e-> tick());
		timer.start();
		buildKeys();
	}
	
	private void loadLevel(int level) {
		objects.clear();
		platforms.clear();
		
		player.hp = INITIAL_HP;
        player.xPosition = SPAWN_X;
        player.yPosition = SPAWN_Y;
		
        int coinCount = 0;
		
        if (level == 1) {
            requiredscore = 80; 
            coinCount = 4;
            
            Platform p1 = new Platform(0, 200, 240, 70, 0, 1, 0, new String[]{""}, new Sprite[] {new Sprite(200, 15, "tennis.png")});
            Platform p2 = new Platform(0, 300, 270, 0, 0, 0, 0, new String[]{""}, new Sprite[] {new Sprite(200, 15, "tennis.png")});
            Platform p3 = new Platform(0, 50, 320, 0, 60, 0, 0, new String[]{""}, new Sprite[] {new Sprite(200, 15, "tennis.png")});
            Platform p4 = new Platform(0, 50, 490, 0, 140, 0, 2, new String[]{""}, new Sprite[] {new Sprite(700, 15, "tennis.png")});
            Platform p5 = new Platform(0, 700, 400, 0, 120, 0, 1, new String[]{""}, new Sprite[] {new Sprite(200, 15, "tennis.png")});
            Platform p6 = new Platform(0, 450, 340, 0, 60, 0, 1, new String[]{""}, new Sprite[] {new Sprite(200, 15, "tennis.png")});
            
            platforms.add(p1);
            platforms.add(p2);
            platforms.add(p3);
            platforms.add(p4);
            platforms.add(p5);
            platforms.add(p6);
            
            objects.add(new Enemy(0, 450, 120, 100, 100, 3, 3, new String[]{""}, new Sprite[] {new Sprite(55, 55, "enemyTest.png")}));
            objects.add(new Enemy(0, 250, 690, 100, 100, 5, 5, new String[]{""}, new Sprite[] {new Sprite(55, 55, "enemyTest.png")}));
            objects.add(new Enemy(0, 650, 400, 200, 100, 7, 7, new String[]{""}, new Sprite[] {new Sprite(55, 55, "enemyTest.png")}));
            
        } else if (level == 2) {
            requiredscore = 100; 
            coinCount = 5;

            platforms.add(new Platform(0, 100, 600, 800, 50, 0, 0, new String[]{""}, new Sprite[] {new Sprite(800, 50, "tennis.png")}));
            
            platforms.add(new Platform(0, 50, 400, 350, 0, 2, 0, new String[]{""}, new Sprite[] {new Sprite(200, 15, "tennis.png")}));
            
            platforms.add(new Platform(0, 600, 400, 200, 15, 0, 0, new String[]{""}, new Sprite[] {new Sprite(200, 15, "tennis.png")}));
            
            platforms.add(new Platform(0, 200, 250, 150, 0, 3, 0, new String[]{""}, new Sprite[] {new Sprite(150, 15, "tennis.png")}));
            
            platforms.add(new Platform(0, 800, 425, 0, 175, 0, 3, new String[]{""}, new Sprite[] {new Sprite(150, 15, "tennis.png")}));
            
            objects.add(new Enemy(0, 100, 545, 100, 100, 5, 5, new String[]{""}, new Sprite[] {new Sprite(55, 55, "enemyTest.png")}));
            objects.add(new Enemy(0, 700, 545, 100, 100, 5, 5, new String[]{""}, new Sprite[] {new Sprite(55, 55, "enemyTest.png")}));
            objects.add(new Enemy(0, 450, 200, 100, 100, 8, 8, new String[]{""}, new Sprite[] {new Sprite(55, 55, "enemyTest.png")}));
            
        } else {
            
            return; 
        }
        
        objects.addAll(platforms);
        
        spawnCoins(coinCount);
	}
	
	private void advanceLevel() {
	    currentLevel++;
	    if (currentLevel > 2) { 
	        gameFinished = true;
	        timer.stop(); 
	        levelPassed = false;
	    } else {
	        levelPassed = false;
	        score = 0;
	        loadLevel(currentLevel); 
	    }
	}

    private boolean isSafeToSpawn(int newX, int newY, int newWidth, int newHeight) {
        int newCenterX = newX + newWidth / 2;
        int newCenterY = newY + newHeight / 2;
        int minDistanceSquared = MIN_COIN_DISTANCE * MIN_COIN_DISTANCE;

        for (GameObject obj : objects) {
            if (obj instanceof Item) {
                Item existingCoin = (Item) obj;
                
                int existingCenterX = existingCoin.xPosition + existingCoin.usedSprite.width / 2;
                int existingCenterY = existingCoin.yPosition + existingCoin.usedSprite.height / 2;

                int dx = newCenterX - existingCenterX;
                int dy = newCenterY - existingCenterY;
                double distanceSquared = (dx * dx) + (dy * dy);
                
                if (distanceSquared < minDistanceSquared) {
                    return false;
                }
            }
        }
        return true;
    }
	
	private void spawnCoins(int count) {
        if (platforms.isEmpty()) return;

        Random rand = new Random();
        int coinWidth = 30; 
        int coinHeight = 30; 
        int coinScore = COIN_POINT_VALUE;

        for (int i = 0; i < count; i++) {
            Platform parentPlatform = platforms.get(rand.nextInt(platforms.size()));
            int maxAttempts = 50;
            boolean spawned = false;

            for (int attempt = 0; attempt < maxAttempts; attempt++) {
            
	            int spawnX = parentPlatform.xPosition + rand.nextInt(parentPlatform.usedSprite.width - coinWidth);
	            int spawnY = parentPlatform.yPosition - coinHeight - rand.nextInt(30) - 55; 
	            
	            int range = 30; 
	            int velocity = 1;

                if (isSafeToSpawn(spawnX, spawnY, coinWidth, coinHeight)) {
                    Item coin = new Item(0, spawnX, spawnY, range, range, velocity * (rand.nextBoolean() ? 1 : -1), velocity * (rand.nextBoolean() ? 1 : -1), new String[]{""}, new Sprite[] {new Sprite(coinWidth, coinHeight, "itemTest.png")}, coinScore );
                    objects.add(coin);
                    spawned = true;
                    break;
                }
            }
            if (!spawned) {
                
            }
        }
    }
	
	public int getCoinCount() {
		int count = 0;
		for (GameObject obj : objects) {
			if (obj instanceof Item) {
				count++;
			}
		}
		return count;
	}
	
	private void restartLevel() {
		levelPassed = false;
		score = 0; 
		loadLevel(currentLevel); 
	}

	public void tick() {
		if (!gameFinished && score >= requiredscore && !levelPassed) {
	            levelPassed = true;
	    }
		
		
		boolean fellToDeath = player.yPosition > Stage.HEIGHT;
		
		if ((fellToDeath || player.hp <= 0) && !levelPassed && !gameFinished) {
            restartLevel();
            repaint(); 
            return; 
        }
		
		
		if (player.isInvincible && System.currentTimeMillis() - pastTime >= iFrame) {
            player.isInvincible = false;
        }
		
		if (!levelPassed && !gameFinished) { 
			handleInput();
			player.onGround = false;
			prevX = player.xPosition;
			prevY = player.yPosition;
			player.update();
			
            if (player.xPosition < 0) {
                player.xPosition = 0;
                player.velocityX = 0;
            } else if (player.xPosition + player.usedSprite.width > Stage.WIDTH) {
                player.xPosition = Stage.WIDTH - player.usedSprite.width;
                player.velocityX = 0;
            }
            
            if (player.yPosition < 0) {
                player.yPosition = 0;
                player.velocityY = 0;
            }
            
			
		} else if (levelPassed && pressedKeys.contains(KeyEvent.VK_N)) {
			
			advanceLevel();
		}
		
		for (GameObject obj: objects) {
			obj.update();
		}
		if (!toRemove.isEmpty()) {
			for (GameObject obj : toRemove) {
				objects.remove(obj);
			}
            toRemove.clear();
		}
		
		
		for (GameObject obj:objects) {
			int leftmostPlayer = player.xPosition;
			int rightmostPlayer = player.xPosition + player.usedSprite.width;
			int topmostPlayer = player.yPosition;
			int bottommostPlayer = player.yPosition + player.usedSprite.height;

			int leftmostObject = obj.xPosition;
			int rightmostObject = obj.xPosition + obj.usedSprite.width;
			int topmostObject = obj.yPosition;
			int bottommostObject = obj.yPosition + obj.usedSprite.height;
			
			if ((rightmostPlayer > leftmostObject && leftmostPlayer < rightmostObject) && (bottommostPlayer > topmostObject && topmostPlayer < bottommostObject)) {
				if (obj instanceof Platform) {
					Platform platform = (Platform) obj;
							player.xPosition+=platform.velocityX;

						    int overlapRight = rightmostPlayer - leftmostObject;
						    int overlapLeft = rightmostObject - leftmostPlayer;
						    int overlapBottom = bottommostPlayer - topmostObject;
						    int overlapTop = bottommostObject - topmostPlayer;
						
						    int minOverlapX = Math.min(overlapRight, overlapLeft);
						    int minOverlapY = Math.min(overlapBottom, overlapTop);

						    if (minOverlapX < minOverlapY) {
						        if (overlapRight < overlapLeft) {
						        	player.xPosition -= overlapRight;
						        } 
						        else {
						        	player.xPosition += overlapLeft;
						        }
						        player.velocityX = 0;
						    } 
						    else {
						        if (overlapBottom < overlapTop) {
						        	player.yPosition -= overlapBottom;
						            player.onGround = true;
						        } 
						        else {
						        	player.yPosition += overlapTop;
						        }
						        player.velocityY = 0;
						}
					
				}
				
				else if (obj instanceof Enemy) {
					Enemy enemy = (Enemy) obj;
						enemy.hitPlayer = true;
						
					    if (System.currentTimeMillis()-pastTime>=iFrame) {
					    	enemy.hitPlayer = true;
					    	pastTime = System.currentTimeMillis();
					    	if (player.hp > 0) { 
					    	    player.hp -= 20;
					    	    player.isInvincible = true;
					    		}
							player.velocityX-=7;

					    }					
				}
				else if (obj instanceof Item) {
					Item item = (Item) obj;
					if (pressedKeys.contains(KeyEvent.VK_E) && !levelPassed)
					{
						score += COIN_POINT_VALUE;
						toRemove.add(obj);
					}
				}
			}
				
			}
		

		repaint();
	}
	
	private void handleInput() {
	    int accelX = 0;
	    int accelY = 0;

	    if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
	    	accelX -= 1;
	    }
	    if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
	    	accelX += 1;
	    }
	    if (accelX == 0) {
	    }
	    if ((pressedKeys.contains(KeyEvent.VK_UP) || pressedKeys.contains(KeyEvent.VK_SPACE))&&player.onGround) {
	    	accelY -= 13;
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
	
	if (!gameFinished) {
		player.paintComponent(g);
	}
    
    if (!gameFinished) {
    	Font hudFont = new Font("Arial", Font.PLAIN, 20); 
        g.setFont(hudFont); 
        
	    g.setColor(Color.BLACK);
	    g.drawString("HP: " + player.hp + " / 100", 10, 20); 
	    
	    g.setColor(Color.BLACK);
	    String scoreString = "SCORE: " + score + " / " + requiredscore;
	    if (levelPassed) {
	        scoreString += " - LEVEL COMPLETE!";
	        g.setColor(Color.BLACK);
	    }
	    g.drawString(scoreString, 10, 50); 
	    
	    g.setColor(Color.BLACK);
	    String coinCountString = "COINS LEFT: " + getCoinCount();
	    g.drawString(coinCountString, 10, 80);
	    
	    g.setColor(Color.BLACK);
	    g.drawString("LEVEL " + currentLevel, Stage.WIDTH - 90, 20);
    }
    
    if (levelPassed) {
    	Graphics2D g2 = (Graphics2D) g;
    	
        g.setColor(new Color(0, 0, 0, 150)); 
        g.fillRect(0, 0, Stage.WIDTH, Stage.HEIGHT);

        g2.setFont(new Font("Arial", Font.BOLD, 48));
        g.setColor(Color.WHITE);
        String completeText = "LEVEL COMPLETE!";
        int textWidth = g.getFontMetrics().stringWidth(completeText);
        int textX = (Stage.WIDTH - textWidth) / 2;
        int textY = Stage.HEIGHT / 2 - 30;
        g.drawString(completeText, textX, textY);

        g2.setFont(new Font("Arial", Font.PLAIN, 24));
        String instructionText = currentLevel < 2 ? "Press 'N' for Level " + (currentLevel + 1) : "Press 'N' to finish the game!";
        textWidth = g.getFontMetrics().stringWidth(instructionText);
        textX = (Stage.WIDTH - textWidth) / 2;
        textY = Stage.HEIGHT / 2 + 30;
        g.drawString(instructionText, textX, textY);
    }
    
    if (gameFinished) {
    	Graphics2D g2 = (Graphics2D) g;
    	
        g.setColor(new Color(0, 0, 0, 180)); 
        g.fillRect(0, 0, Stage.WIDTH, Stage.HEIGHT);

        g2.setFont(new Font("Arial", Font.BOLD, 60));
        g.setColor(Color.YELLOW);
        String completeText = "GAME COMPLETED!";
        int textWidth = g.getFontMetrics().stringWidth(completeText);
        int textX = (Stage.WIDTH - textWidth) / 2;
        int textY = Stage.HEIGHT / 2;
        g.drawString(completeText, textX, textY);
        
        g2.setFont(new Font("Arial", Font.PLAIN, 24));
        String messageText = "You collected all the treasures!";
        textWidth = g.getFontMetrics().stringWidth(messageText);
        textX = (Stage.WIDTH - textWidth) / 2;
        textY = Stage.HEIGHT / 2 + 60;
        g.drawString(messageText, textX, textY);
    }
}
}
