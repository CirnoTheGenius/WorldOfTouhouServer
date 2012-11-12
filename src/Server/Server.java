package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import Cirno.Main;
import PlayerClasses.Player;
import Server.ServerLogger.Severity;

public class Server extends Thread {

	private PlayerList List;
	private String Name;
	private int MaxPlayers;
	private ServerLogger Logger = new ServerLogger();
	private ServerSocket serverSocket;
	private DatagramSocket dataSocket;
	ArrayList<ClientThread> clients;
	ArrayList<InetAddress> clientIPs;
	private Main m;
	
	public Server(String n, int mp, Main m){
		Name = n;
		MaxPlayers = mp;
		List = new PlayerList(this);
		this.m = m;
		super.start();
	}

	public void run(){
		clients = new ArrayList<ClientThread>();
		clientIPs = new ArrayList<InetAddress>();
		try {
			serverSocket = new ServerSocket(9999);
			dataSocket = new DatagramSocket(9999);
			while(true){
				/*
				 * Day 1: WSTFGL. I go to hell with Networking. I'm packed with supplies, such as my brain, a keybored, and some hands.
				 * Day 2: Actually, smoother then I expected.
				 * Day 9: Sending data to a client is a pain.
				 * Day 10: Suicide. Stupid game part won't display chat messages, and it keeps duping the messages.
				 * Day 11: HOORAY! Stupid chat works! Added some stoof too.
				 * Day something: Food supplies running low. I don't know if I'll make it out alive.
				 */
				Socket tmp = serverSocket.accept();
				for(ClientThread th : clients){
					if(!th.Client.isClosed()){
						clients.add(new ClientThread(tmp, this, dataSocket, m));
						clientIPs.add(tmp.getInetAddress());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			getLogger().log("Failed to bind to port 9999! Oh noes!", Severity.SEVERE);
			System.exit(-1);
		}
	}
	
	public void removePlayer(Player p, InetAddress home, String username, ClientThread cs) throws IOException{
		List.remove(p);
		clientIPs.remove(home);
		sendToClients(username + " has left.");
		m.addChatMessage(username + " has left.");
		cs.ds.close();
		cs.Client.close();
		clients.remove(cs);
		m.refreshPlayer();
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

	public void sendToClients(String s){
		for(ClientThread ct : clients){
			System.out.println("Sent data!");
			try {
				byte[] mb = s.getBytes();
				DatagramPacket pk = new DatagramPacket(mb, mb.length, ct.Client.getInetAddress(), 8494);
				dataSocket.send(pk);
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
