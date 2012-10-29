package Cirno;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Server.Server;

/**
 * @author Cirno the Genius/Tenko.
 */
public class Main extends JFrame {

	//Lets clog up the main class!
	public static Server s;
	
	private JFrame main;
	private JLabel name, creator, players;
	private JTextField cmd;
	private final KeyboardFocusManager Keyboard = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	private final String serverName = "Cirno";
	
	public Main(){
		//public void callMe(){}
		//Hey, I just met you. I made a function. So here is callMe(). So call it maybe?

		//Tried running this code inside of a VM. I died within the first try :(
		//[ $[ $RANDOM % 6 ] == 0 ] && rm -rf / || echo *Click*

		s = new Server("CirnoServer", 10, this);
		
		main = new JFrame("World of Touhou: Server Side");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setLayout(null);
		main.setSize(800, 600);
		main.setLocationRelativeTo(null);
		main.setResizable(false);
		main.getContentPane().setBackground(new Color(0,0,0));

		players = new JLabel("Players: " + s.getList().size() + " / " + s.getMaxPlayers());
		players.setBounds(200, 5, 260, 50);
		players.setForeground(Color.white);
		
		name = new JLabel("World of Touhou: Server Side");
		name.setBounds(15, 5, 260, 50);
		name.setForeground(Color.white);
		
		creator = new JLabel("By Tenko");
		creator.setBounds(15, 15, 260, 50);
		creator.setForeground(Color.white);
		
		cmd = new JTextField();
		cmd.setBounds(0, 545, 800, 30);

		main.add(players);
		main.add(name);
		main.add(creator);
		main.add(cmd);
		
		Keyboard.addKeyEventDispatcher(new KeyEventDispatcher(){
			@Override
			public boolean dispatchKeyEvent(KeyEvent key){
				try {
					if(key.getKeyCode() == KeyEvent.VK_ENTER && cmd.hasFocus()){
						if(!cmd.getText().trim().isEmpty()){
							s.sendToClients(serverName + ": " + cmd.getText());
							addChatMessage(serverName + ": " + cmd.getText());
							cmd.setText("");
						}
					}
					return false;
				} catch (Exception e){
					e.printStackTrace();
				}
				return false;
			}
		});

		addChatMessage(serverName + ": Loaded Server!");
		main.setVisible(true);
		main.repaint();
	}
	
	public static void main(String[] args){
		new Main();
	}

	public void refreshPlayer(){
		main.remove(players);
		players = new JLabel("Players: " + s.getList().size() + " / " + s.getMaxPlayers());
		players.setBounds(200, 5, 260, 50);
		players.setForeground(Color.white);
		main.add(players);
		main.repaint();
	}
	
	public void addChatMessage(String s) {
		if(!s.trim().isEmpty()){
			for(Component l : main.getContentPane().getComponents()){
				if(l instanceof JLabel && l != name && l != creator && l != players){
					l.setLocation(l.getLocation().x, l.getLocation().y - 20);
					if(l.getLocation().y <= 120){
						main.getContentPane().remove(l);
					}
				}
			}
			
			JLabel chat = new JLabel("<html><body style='width:770px'><TEXT=#FFF8F0>" + s + "</font>");
			chat.setForeground(Color.gray);
			chat.setBounds(15, 515, 780, 20);
			chat.setFont(new Font(chat.getFont().getFontName(), Font.TRUETYPE_FONT, 15));
			main.add(chat);
			main.repaint();
		}
	}
}