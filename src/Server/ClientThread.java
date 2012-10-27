package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

import Cirno.Main;
import PlayerClasses.Player;


public class ClientThread extends Thread {

	public Socket Client;
	private DatagramSocket ds;
	private Server server;
	private Player p;
	
	public ClientThread(ServerSocket s, Server s2, DatagramSocket d){
		try {
			Client = s.accept();
			ds = d;
			s2.getLogger().log("Client from "  + Client.getInetAddress().getHostAddress());
			server = s2;
			super.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
					server.sendToClients("Welcome " + content.split("user/")[1] + "!");
				} else if(content.startsWith("chat/")){
					server.sendToClients(content.split("chat/")[1]);
				} else if(content.startsWith("disconnect/")){
					server.getList().remove(p);
					server.sendToClients(content.split("disconnect/")[1] + " has left.");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
