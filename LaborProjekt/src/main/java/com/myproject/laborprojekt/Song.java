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

    public Song(String id, String title, String artist, int duration, double bpm) {
        super(title, artist, duration, bpm);
        this.id = id;
    }

    public String getId() {
        return id;
    }
    
    
    @Override
    
    public void play() {
        System.out.println("Playing: " + getInfo());
    }

}
