package Server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import Server.ServerLogger.Severity;

public class Server extends Thread {

	public static PlayerList List;
	private String Name;
	private int MaxPlayers;
	private ServerLogger Logger = new ServerLogger();
	private ServerSocket serverSocket;
	private DatagramSocket dataSocket;
	
	public Server(String n, int mp){
		Name = n;
		MaxPlayers = mp;
		List = new PlayerList(this);
		super.start();
	}

	public void run(){
		try {
			serverSocket = new ServerSocket(9999);
			dataSocket = new DatagramSocket(9999);
		} catch (IOException e) {
			e.printStackTrace();
			getLogger().log("Failed to bind to port 9999! Oh noes!", Severity.SEVERE);
			System.exit(-1);
		}
		while(true){
			/*
			 * Day 1: WSTFGL. I go to hell with Networking. I'm packed with supplies, such as my brain, a keybored, and some hands.
			 * Day 2: Actually, smoother then I expected.
			 * 
			 * 
			 */
			new ClientThread(serverSocket, this, dataSocket);
		}
	}
	
	public PlayerList getList(){
		return List;
	}
	
	public int getMaxPlayers(){
		return MaxPlayers;
	}

	public String getServerName() {
		return Name;
	}

	public ServerLogger getLogger(){
		return Logger;
	}

}
