/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.myproject.laborprojekt;

public class LaborProjekt {

    public static void main(String[] args) {
        for (ExerciseType type : ExerciseType.values()) {
            System.out.println("=== " + type.name() + " Playlist ===");

            Playlist playlist = PlaylistGenerator.generate(type);
            playlist.PrintAll();

            System.out.println();
        }
    }
}
