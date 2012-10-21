package Server;

public class ServerLogger {

	public enum Severity {
		NINEBALL, SEVERE, ERROR, WARNING, NORMAL
	}
	
	public void log(String s){
		System.out.println("[" + Severity.NORMAL + "] " + s);
	}
	
	public void log(String s, Severity sev){
		System.out.println("[" + sev + "] " + s);
	}
	
}
