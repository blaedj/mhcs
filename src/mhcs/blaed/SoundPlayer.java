package mhcs.blaed;

import com.google.gwt.dom.client.AudioElement;
import com.google.gwt.media.client.Audio;

public class SoundPlayer {
    private Audio welcomeAudio;
    private Audio accessDeniedAudio;
    private Audio errorAudio;
    private Audio saveSucceedAudio;
    private Audio modulesLoadedAudio;

    public SoundPlayer(){
	welcomeAudio = Audio.createIfSupported();
	welcomeAudio.addSource("sounds/welcomeguy.mp3", AudioElement.TYPE_MP3);
	welcomeAudio.addSource("sounds/welcomeguy.wav", AudioElement.TYPE_WAV);

	accessDeniedAudio = Audio.createIfSupported();
	accessDeniedAudio.addSource("sounds/AccessDeniedGuy.mp3", AudioElement.TYPE_MP3);
	accessDeniedAudio.addSource("sounds/AccessDeniedGuy.wav", AudioElement.TYPE_WAV);

	errorAudio = Audio.createIfSupported();
	errorAudio.addSource("sounds/ErrorOccuredGuy.mp3", AudioElement.TYPE_MP3);
	errorAudio.addSource("sounds/ErrorOccuredGuy.wav", AudioElement.TYPE_WAV);

	saveSucceedAudio = Audio.createIfSupported();
	saveSucceedAudio.addSource("sounds/SaveSuccessfulGuy.mp3", AudioElement.TYPE_MP3);
	saveSucceedAudio.addSource("sounds/SaveSuccessfulGuy.wav", AudioElement.TYPE_WAV);

	modulesLoadedAudio = Audio.createIfSupported();
	modulesLoadedAudio.addSource("sounds/ModulesLoadedGuy.mp3", AudioElement.TYPE_MP3);
	modulesLoadedAudio.addSource("sounds/ModulesLoadedGuy.wav", AudioElement.TYPE_WAV);

    }

    public void playWelcome(){
	welcomeAudio.play();
    }

    public void playAccessDenied(){
	accessDeniedAudio.play();
    }

    public void playErrorOccured() {
	errorAudio.play();
    }

    public void playSaveSuccessful(){
	saveSucceedAudio.play();
    }

    public void playModulesLoaded() {
	modulesLoadedAudio.play();
    }

}
