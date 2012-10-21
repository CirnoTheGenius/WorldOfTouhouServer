package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class ClientThread extends Thread {

	BufferedReader in;
	Server server;
	
	public ClientThread(ServerSocket s, Server s2){
		try {
			in = new BufferedReader(new InputStreamReader(s.accept().getInputStream()));
			server = s2;
			super.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run(){
		try {
			server.getLogger().log("Client connected!");
			String inputLine, outputLine;
			LoginProtocol kkp = new LoginProtocol();
			
			while ((inputLine = in.readLine()) != null) {   
				outputLine = kkp.processString(inputLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
