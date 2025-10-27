/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.laborprojekt;

import java.util.List;

public class PlaylistGenerator {

    public static Playlist generate(ExerciseType type) {
        double targetTempo;
        int size = 10;
        List<String> seeds = List.of("878dadea-33c5-4c08-bdb9-e2b117475a99"); // your valid seed

        int minPopularity = 0;
        int maxPopularity = 100;

        switch (type) {
            case RUNNING:
                targetTempo = 130;
                break;
            case HIIT:
                targetTempo = 170;
                break;
            case CARDIO:
                targetTempo = 120;
                break;
            case YOGA:
                targetTempo = 80;
                break;
            case WEIGHTLIFTING:
                targetTempo = 110;
                break;
            default:
                targetTempo = 120;
                break;
        }

        Playlist playlist = new Playlist(type.name() + " Playlist");
        List<Song> songs = ApiService.getSongsByTempo(targetTempo, seeds, size);

        if (songs.isEmpty()) {
            System.out.println("No songs found for this playlist.");
        } else {
            
            for (Song s : songs) {

                playlist.addSong(s);
            }
        }

        return playlist;
    }
}
