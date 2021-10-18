package player;


import java.io.File;

import javazoom.jlgui.basicplayer.*;

public class MusicPlayer {
	private BasicPlayer basicPlayer = null;
	private File canSonando = null;
	
	public MusicPlayer(){
		basicPlayer = new BasicPlayer();
		setCanSonando(null);
	}
	
	public void play (File file){
		try {
			basicPlayer.open(file);
			basicPlayer.play();
			setCanSonando(file);
		}
		catch (Exception e){}
	}
	
	public void stop(){
		try {
			basicPlayer.stop();
			setCanSonando(null);
		}
		catch (BasicPlayerException e){
		}
	}
	
	public void setVolume(double vol, double volMax){
		try{
			
			basicPlayer.setGain(vol/volMax);
		}
		catch (BasicPlayerException e){
		}
	}

	public File getCanSonando() {
		return canSonando;
	}

	private void setCanSonando(File canSonando) {
		this.canSonando = canSonando;
	}
}
