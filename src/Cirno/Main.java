package Cirno;

import java.applet.Applet;
import java.awt.Graphics;

import PlayerClasses.Player;
import Server.Server;

public class Main extends Applet {
	
	public boolean isRunning;
	
	public void paint(Graphics g){
		g.drawString("Hello world!", 50, 25);
		start();
	}
	
	public void start(){
		if(!isRunning){
			Server s = new Server("CirnoServer", 1);
			Player p = new Player("Cirno", 100, 1, 1, s);
			
			new Player("Rumia", 100, 1, 1, s);
			new Player("Mystia", 100, 1, 1, s);
			isRunning = true;
		}
	}
}
