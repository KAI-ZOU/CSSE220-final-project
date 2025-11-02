package finalgame;

public class MovementManager {
	// may contain inputmanager? will probably get passed something from inputmanager for the player, otherwise not. will get passed entities. might also do updates in here

	
	public boolean checkOverlap (Player player, GameObject obj) {
		if (player.usedSprite.boundingBox.intersects(obj.usedSprite.boundingBox)) {
			return true;
		}
		else {
			return false;
		}
//		return false;
	}
	
	
}

//handles collisions, updates in velocity, and updates in position.