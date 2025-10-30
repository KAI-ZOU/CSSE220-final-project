package finalgame;

import java.util.ArrayList;

public class Level {
	MovementManager movementManager = new MovementManager(); // maybe we feed the movement manager objects (in method not global), and it can act on those internally.
	InputManager inputManager = new InputManager();
	ArrayList<GameObject> objects = new ArrayList<>();// maybe we should have one for each classe.
	int requiredscore; // score needed to pass a level
	// add or remove objects from view
	// update objects' movement
}
// maybe just 