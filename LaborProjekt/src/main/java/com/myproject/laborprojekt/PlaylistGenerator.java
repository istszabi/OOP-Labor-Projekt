/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.laborprojekt;

import java.util.List;

/**
 *
 * @author istsz
 */
public class PlaylistGenerator {

    public static Playlist generate(ExerciseType type) {
        double targetBpm = 120;

        switch (type) {
            case RUNNING:
                targetBpm = 130;
                break;
            case HIIT:
                targetBpm = 170;
                break;
            case CARDIO:
                targetBpm = 120;
                break;
            case YOGA:
                targetBpm = 80;
                break;
            case WEIGHTLIFTING:
                targetBpm = 110;
                break;

        }
        List <Song> songs = ApiService.getSongsByBpm(targetBpm);
        
        Playlist playlist = new Playlist(type.name() + " Playlist");
        for (Song s : songs) playlist.addSong(s);
        return playlist;
    }

}
