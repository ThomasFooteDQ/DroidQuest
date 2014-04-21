package com.droidquest;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

public class SoundClip {
    public AudioClip audioClip;
    private String filename;

    public SoundClip(String f) {
        filename = f;
        try {
            URL baseURL = new URL("file:" + System.getProperty("user.dir") + "/sounds/");
            URL soundURL;
            soundURL = new URL(baseURL, filename);
            audioClip = Applet.newAudioClip(soundURL);
        }
        catch (MalformedURLException e) {
            System.err.println(e.getMessage());
        }
    }
}
