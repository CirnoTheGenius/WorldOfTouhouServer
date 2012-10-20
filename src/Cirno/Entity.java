package Cirno;

public class Entity {

	public String Name;
	public int Health;
	public HealthMonitor hm;
	
	public Entity(String n, int h){
		Health = h;
		Name = n;
		hm = new HealthMonitor(this);
	}
	
	public void damage(int amount){
		Health = Health - amount;
		System.out.println(Name + " has taken damage!\nBefore Dmg: " + (Health + amount) + "\nAfter Dmg/Health: " + Health);
		hm.update();
		hm.checkHealth();
	}
	
}
