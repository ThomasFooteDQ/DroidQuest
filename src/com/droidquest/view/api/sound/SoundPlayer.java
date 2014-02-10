package com.droidquest.view.api.sound;

import com.droidquest.Room;

/**
 * Service interface for the in-game audio player.
 */
public interface SoundPlayer {
    /**
     * Plays the given audio clip.
     * @param soundName
     */
    void play(String soundName);

    /**
     * Plays the given audio clip, if the current room matches the given room
     * @param soundName the sound to play
     * @param the room to compare with the current room
     */
    void playIfInRoom(Room room, String soundName);

    /**
     * Pre-loads the given audio clip for later playback.
     *
     * @param filename the audio clip to load
     */
    void load(String filename);

    /**
     * Unloads all audio clips from the sound player.
     */
    void unloadAll();

    /**
     * Enables/disables sound.
     * @param enabled should audio be enabled
     */
    void setAudioEnabled(boolean enabled);

    /**
     * @return true if audio is enabled
     */
    boolean isAudioEnabled();
}
