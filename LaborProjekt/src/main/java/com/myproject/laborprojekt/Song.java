/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.laborprojekt;

/**
 *
 * @author istsz
 */
public class Song extends SongBase {

    private String id;
    private int popularity;

    public Song(String id, String title, String artist, float duration, double bpm, int popularity) {
        super(title, artist, duration, bpm, popularity);
        this.id = id;
        this.popularity = popularity;
    }

    public String getId() {
        return id;
    }

    @Override

    public void play() {
        // System.out.println("Playing: " + getInfo());
    }

}
