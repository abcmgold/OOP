package util;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	private Clip clip;
	private URL soundURL[] = new URL[30];
	private FloatControl floatC;
	private int onSound=1;
	private float volume;
	public Sound() {
		soundURL[0] = getClass().getResource("/Sound/BlueBoyAdventure.wav");
		soundURL[1] = getClass().getResource("/Sound/collect.wav");
		soundURL[2] = getClass().getResource("/Sound/hitmonster.wav");
		soundURL[3] = getClass().getResource("/Sound/gameover.wav");
		soundURL[4] = getClass().getResource("/Sound/cursor.wav");
		soundURL[5] = getClass().getResource("/Sound/win.wav");
		soundURL[6] = getClass().getResource("/Sound/bite.wav");
		soundURL[7] = getClass().getResource("/Sound/pushbutton.wav");
	}
	
	public FloatControl getFloatC() {
		return floatC;
	}

	public int getOnSound() {
		return onSound;
	}

	public void setOnSound(int onSound) {
		this.onSound = onSound;
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			floatC = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			checkVolume();
		} catch (Exception e) {
		} 
	}
	public void play() {
		clip.start();
	}
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		clip.stop();
	}
	public void reset() {
		clip.setMicrosecondPosition(0);
	}
	public void checkVolume() {
		switch(onSound) {
		case 0: volume = -80.0f;
			break;
		case 1: volume = 6.0f;
			break;
		}
		floatC.setValue(volume);
	}
}
