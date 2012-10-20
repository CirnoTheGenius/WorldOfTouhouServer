package Cirno;

/**
 * @author Cirno
 */
public class Player extends Entity{
	
	public int Health;
	public int Mana; //Yes, mana. I'm just that default.
	public int Bombs; //Have no idea how this would work. Maybe super-1337-360noscope OP mode?
	public HealthMonitor hm;
	
	public Player(String n, int h, int m, int b){
		super(n, h);
		Mana = m;
		Bombs = b;
		hm = new HealthMonitor(this);
	}

}
