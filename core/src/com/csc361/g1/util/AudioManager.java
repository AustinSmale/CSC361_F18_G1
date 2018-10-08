/**
 * Allen Crigger
 * Class manages the audio for the canyon bunny game.
 */

package com.csc361.g1.util;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {
	public static final AudioManager instance = new AudioManager();
	private Music playingMusic;
	
	// singleton: prevent instantiation from other classes
	private AudioManager () { }
	
	//Plays a sound
	public void play (Sound sound) {
		play(sound, 1);
	}
	
	//Plays a sound at a set volume
	public void play (Sound sound, float volume) {
		play(sound, volume, 1);
	}
	 
	//Plays a sound at a set volume and pitch
	public void play (Sound sound, float volume, float pitch) {
		play(sound, volume, pitch, 0);
	}
	 
	//Plays a sound at a set volume, pitch and pan.
	public void play (Sound sound, float volume, float pitch, float pan) {
		if (!GamePreferences.instance.sound) 
			return;
		sound.play(GamePreferences.instance.volSound * volume, pitch, pan);
	}
	
	//Plays background music
	public void play (Music music) {
		stopMusic();
		playingMusic = music;
		if (GamePreferences.instance.music) {
			music.setLooping(true);
			music.setVolume(GamePreferences.instance.volMusic);
			music.play();
		}
	}
	
	//Stops the already playing music
	public void stopMusic () {
		if (playingMusic != null) 
			playingMusic.stop();
	}
		
	//Updates music
	public void onSettingsUpdated () {
		if (playingMusic == null)
			return;
		playingMusic.setVolume(GamePreferences.instance.volMusic);
		if (GamePreferences.instance.music) {
			if (!playingMusic.isPlaying()) 
				playingMusic.play();
		} else {
			playingMusic.pause();
		}
	}
}
