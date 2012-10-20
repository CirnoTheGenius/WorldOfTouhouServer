package engine;

/**
 * Health Monitor. Probably one of the most unsafe methods ever.
 * @author Cirno
 */
public class HealthMonitor {
	
	Entity entity;
	private int Health; //Seperate integer for basis health. Volatile, will be changing.
	
	public HealthMonitor(Entity e){
		entity = e;
		Health = e.Health;
	}
	
	public void update(){
		Health = entity.Health;
	}
	
	public void checkHealth(){
		if(Health <= 0){
			System.out.println(entity.Name + " has died!");
		}
	}
	
}
