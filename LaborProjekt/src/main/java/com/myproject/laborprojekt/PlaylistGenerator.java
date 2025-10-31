/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.laborprojekt;

import java.util.List;

public class PlaylistGenerator {

    public static Playlist generate(ExerciseType type, int size) {
        List<String> seeds = List.of("878dadea-33c5-4c08-bdb9-e2b117475a99");

        double minTempo, maxTempo;

        switch (type) {
            case RUNNING:
                minTempo = 120;
                maxTempo = 140;
                break;
            case HIIT:
                minTempo = 160;
                maxTempo = 180;
                break;
            case CARDIO:
                minTempo = 110;
                maxTempo = 130;
                break;
            case YOGA:
                minTempo = 60;
                maxTempo = 80;
                break;
            case WEIGHTLIFTING:
                minTempo = 130;
                maxTempo = 140;
                break;
            default:
                minTempo = 100;
                maxTempo = 120;
                break;
        }

        double targetTempo = minTempo + Math.random() * (maxTempo - minTempo);

        Playlist playlist = new Playlist(type.name() + " Playlist");
        List<Song> songs = ApiService.getSongsByTempo(targetTempo, seeds, size);

        if (songs.isEmpty()) {
            System.out.println("No songs found for this playlist.");
        } else {
            for (int i = 0; i < songs.size(); i++) {
                Song s = songs.get(i);
                playlist.addSong(s);
            }
        }

        return playlist;
    }
}
