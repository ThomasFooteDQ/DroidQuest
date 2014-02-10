package com.droidquest.view.swing.sound;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.droidquest.Game;
import com.droidquest.Room;
import com.droidquest.items.Item;
import com.droidquest.view.api.sound.SoundPlayer;

/**
 * The Audio player for the Swing audio interface.
 */
public class SwingSoundPlayer implements SoundPlayer {
    private static final Logger LOG = LoggerFactory.getLogger(SwingSoundPlayer.class);

    private final Game game;
    private boolean enabled = true;
    private Map<String, SoundClip> soundClips = new HashMap<String, SoundClip>();

    public SwingSoundPlayer(Game game) {
        this.game = game;
    }

    @Override
    public void load(String filename) {
        soundClips.put(filename, new SoundClip(filename));
    }

    @Override
    public void play(String soundName) {
        if (!isAudioEnabled()) {
            return;
        }

        SoundClip soundClip = soundClips.get(soundName);
        if (soundClip == null) {
            LOG.warn("Sound clip [{}] not found", soundName);
            return;
        }

        soundClip.audioClip.play();
    }

    @Override
    public void playIfInRoom(Room room, String soundName)
    {
        final Item currentViewer = game.getCurrentLevel().currentViewer;
        if (currentViewer != null && room != currentViewer.room) {
            return;
        }

        play(soundName);
    }

   /**
     * Unloads all sound clips from the player.
     */
    @Override
    public void unloadAll() {
        soundClips.clear();
    }


    @Override
    public void setAudioEnabled(boolean enabled) {
        this.enabled = enabled;
        if (!enabled) {
            for (SoundClip soundClip : soundClips.values()) {
                soundClip.audioClip.stop();
            }
        }
    }

    @Override
    public boolean isAudioEnabled() {
        return enabled;
    }
}
