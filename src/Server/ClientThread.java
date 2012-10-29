package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

import Cirno.Main;
import PlayerClasses.Player;


public class ClientThread extends Thread {

	Socket Client;
	DatagramSocket ds;
	private Server server;
	private Main main;
	private Player p;
	
	public ClientThread(Socket s, Server s2, DatagramSocket d, Main m){
		Client = s;
		ds = d;
		server = s2;
		main = m;
		super.start();
	}

	public void run(){
		try {
			while(true){
				byte[] data = new byte[1024];
				DatagramPacket packet = new DatagramPacket(data, data.length);
				ds.receive(packet);
				String content = new String(packet.getData()).trim();
				if(content.startsWith("user/")){
					p = new Player(content.split("user/")[1], 100, 1, 1, Main.s);
					server.getList().add(p);
					main.refreshPlayer();
					server.sendToClients("Welcome " + content.split("user/")[1] + "!");
					main.addChatMessage("Player " + content.split("user/")[1] + " has joined the server from " + Client.getInetAddress().getHostAddress());
				} else if(content.startsWith("chat/")){
					server.sendToClients(content.split("chat/")[1]);
					main.addChatMessage(content.split("chat/")[1]);
				} else if(content.startsWith("disconnect/")){
					server.removePlayer(p, Client.getInetAddress(), p.getName(), this);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
