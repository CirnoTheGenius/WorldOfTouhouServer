package EntityClasses;

import PlayerClasses.Player;

public class HostileEntity extends Entity {

	private Player Target;
	
	private int Level;
	private int Strength, Defense, Hit, Agility;
	
	public HostileEntity(String n, int h, int l) {
		super(n, h);
		Level = l;
		Strength = l / 2;
	}
	
	public void setTarget(Player tar){
		
	}
	
	public void getNearestTarget(){
		
		
		
	}

}
