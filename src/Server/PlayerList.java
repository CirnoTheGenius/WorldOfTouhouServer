package Server;

import java.util.ArrayList;
import java.util.Random;

import PlayerClasses.Player;
import Server.ServerLogger.Severity;

public class PlayerList extends ArrayList<Player> {

	Server server;
	
	public PlayerList(Server server) {
		this.server = server;
	}
	
	@Override
	public boolean add(Player p){
		if(server.getMaxPlayers() > this.size()){
			server.getLogger().log(p.getName() + " has joined server " + server.getServerName());
			super.add(p);
			return true;
		} else {
			server.getLogger().log(p.getName() + " has failed to join server " + server.getServerName(), Severity.NINEBALL);
			server.getLogger().log("Reason: Server too full!", Severity.NINEBALL);
			return false;
		}
	}
	
	private boolean isCommonName(String p){
		short i = 0;
		for(Player s : this){
			if(s.getName().startsWith(p)){
				i++;
			}
		}
		if(i > 1){
			return true;
		}
		return false;
	}
	
	public Player getRandom(){
		return this.get((new Random()).nextInt(this.size()));
	}
	
	public Player getPlayer(String s){
		if(!isCommonName(s)){
			for(int i=0; i < this.size(); i++){
				if(this.get(i).getName().startsWith(s)){
					return this.get(i);
				}
			}
		}
		return null;
	}
	
}
