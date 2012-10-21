package EntityClasses;


/**
 * Health Monitor. Probably one of the most unsafe methods ever.
 * @author Cirno
 */
public class HealthMonitor {
	
	private Entity entity;
	private int Health; //Seperate integer for basis health. Volatile, will be changing.
	
	public HealthMonitor(Entity e){
		entity = e;
		Health = e.getHealth();
	}
	
	public void update(){
		Health = entity.getHealth();
	}
	
	public void checkHealth(){
		if(Health <= 0){
			System.out.println(entity.getName() + " has died!");
		}
	}
	
}
