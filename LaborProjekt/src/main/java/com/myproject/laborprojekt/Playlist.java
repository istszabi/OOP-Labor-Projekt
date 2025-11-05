/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.laborprojekt;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author istsz
 */
public class Playlist {

    private String name;
    private List<Song> songs = new ArrayList<>();

    public Playlist(String name) {
        this.name = name;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return songs;
    }
    // Playlist output to console
    public void PrintAll() {
        System.out.println("Playlist:" + name);
        for (int i = 0; i < songs.size(); i++) {
            Song s = songs.get(i);
            System.out.println(" - " + s.getInfo());
        }
    }

}
