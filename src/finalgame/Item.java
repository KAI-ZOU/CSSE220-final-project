package finalgame;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Item extends GameObject{
	int scoreGiven;
	
	public Item(int id, int xPosition, int yPosition, String[] spriteNames, Sprite[] sprites) {
		super(id, xPosition, yPosition, spriteNames, sprites);
	}
	
	public void collect(){
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
//	@Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		Graphics2D g2 = (Graphics2D) g;
//		g2.drawRect(50, 50, 25, 25);
//	}
}
