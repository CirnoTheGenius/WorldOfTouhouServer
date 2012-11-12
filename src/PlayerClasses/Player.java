package PlayerClasses;

import EntityClasses.Entity;
import EntityClasses.HealthMonitor;
import Server.Server;

/**
 * @author Cirno
 */
public class Player extends Entity {
	
	public Player(String n, int h, int m, int b, Server s){
		super(n, h);
		new HealthMonitor(this);
	}
	
}