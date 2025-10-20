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
    protected int duration;
    protected double bpm;

    public SongBase(String title, String artist, int duration, double bpm) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.bpm = bpm;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }

    public double getBpm() {
        return bpm;
    }

    public String getInfo() {
        return String.format("%s - %s (%ds, %1.f BPM)", title, artist, duration, bpm);
    }
}
