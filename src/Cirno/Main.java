package Cirno;

import java.applet.Applet;
import java.awt.Graphics;

import Server.Server;

/**
 * @author Cirno the Genius/Tenko.
 */
public class Main extends Applet {
	
	public boolean isRunning;
	public static Server s;
	
	
	public void paint(Graphics g){
		g.drawString("Hello world!", 50, 25);
		start();
	}
	
	public void start(){
		if(!isRunning){
			s = new Server("CirnoServer", 3);
			isRunning = true;
		}
	}
}
