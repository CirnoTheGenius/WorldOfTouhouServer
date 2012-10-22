package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

import Cirno.Main;
import PlayerClasses.Player;


public class ClientThread extends Thread {

	private BufferedReader in;
	private Socket Client;
	private boolean isRunning;
	private DatagramSocket ds;
	private Server server;
	
	public ClientThread(ServerSocket s, Server s2, DatagramSocket d){
		try {
			Client = s.accept();
			ds = d;
			in = new BufferedReader(new InputStreamReader(Client.getInputStream()));
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
					new Player(content.split("user/")[1], 100, 1, 1, Main.s);
				} else if(content.startsWith("chat/")){
					System.out.println(content.split("chat/")[1]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendDataToClients(){
		
	}
}
