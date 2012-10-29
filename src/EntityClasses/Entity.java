package EntityClasses;


public class Entity {

	private String Name;
	private int Health;
	private HealthMonitor hm;
	
	public Entity(String n, int h){
		Health = h;
		Name = n;
		hm = new HealthMonitor(this);
	}
	
	public String getName(){
		return Name;
	}
	
	public int getHealth(){
		return Health;
	}
	
	public void damage(int amount){
		Health = Health - amount;
		System.out.println(Name + " has taken damage!\nBefore Dmg: " + (Health + amount) + "\nAfter Dmg/Health: " + Health);
		hm.update();
		hm.checkHealth();
	}
	
	public void heal(int amount){
		Health = Health + amount;
		System.out.println(Name + " has been healed!\nBefore Heal: " + (Health - amount) + "\nAfter Heal/Health: " + Health);
		hm.update();
		hm.checkHealth();
	}
	
}
