/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.laborprojekt;

/**
 *
 * @author istsz
 */
public abstract class SongBase implements Playable {

    protected String title;
    protected String artist;
    protected float duration;
    protected double bpm;
    protected int popularity;

    public SongBase(String title, String artist, float duration, double bpm, int popularity) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.bpm = bpm;
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public float getDuration() {
        return duration;
    }

    public double getBpm() {
        return bpm;
    }

    public int getPopularity() {
        return popularity;
    }

    public String getInfo() {
        int totalSeconds = (int)Math.round(duration * 60);
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        double roundedBpm = Math.round(bpm);
        return String.format("%s - %s (%d:%02d min, %.1f BPM, popularity: %d)",
                title, artist, minutes, seconds, roundedBpm, popularity);
    }

}
