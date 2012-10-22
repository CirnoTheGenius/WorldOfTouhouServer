package PlayerClasses;

import EntityClasses.Entity;
import EntityClasses.HealthMonitor;
import Server.Server;

/**
 * @author Cirno
 */
public class Player extends Entity {
	
	private int Mana; //Yes, mana. I'm just that default.
	private int Bombs; //Have no idea how this would work. Maybe super-1337-360noscope OP mode?
	private HealthMonitor hm;
	
	public Player(String n, int h, int m, int b, Server s){
		super(n, h);
		Mana = m;
		Bombs = b;
		hm = new HealthMonitor(this);
		s.getList().add(this);
	}
	
	public void chat(){
		
	}
	
}