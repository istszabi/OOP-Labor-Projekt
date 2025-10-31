/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.laborprojekt;

import java.util.*;
import com.google.gson.*;
import kong.unirest.*;

public class ApiService {

    private static final String API_URL = "https://api.reccobeats.com/v1/track/recommendation";

    public static List<Song> getSongsByTempo(double tempo, List<String> seeds, int size) {
        List<Song> songs = new ArrayList<>();
        try {
            String url = API_URL + "?tempo=" + tempo + "&size=" + size + "&seeds=" + String.join(",", seeds);
            String body = Unirest.get(url).asString().getBody();
            JsonArray content = JsonParser.parseString(body).getAsJsonObject().getAsJsonArray("content");

            for (JsonElement trackElement : content) {
                JsonObject trackObject = trackElement.getAsJsonObject();

                String title = trackObject.has("trackTitle") ? trackObject.get("trackTitle").getAsString() : "Unknown Title";

                String artist = "Unknown Artist";
                if (trackObject.has("artists") && trackObject.getAsJsonArray("artists").size() > 0) {
                    try { 
                        artist = trackObject.getAsJsonArray("artists").get(0).getAsJsonObject().get("name").getAsString(); } catch(Exception ignored) {}
                }

                double songTempo = trackObject.has("tempo") ? trackObject.get("tempo").getAsDouble() : tempo;
                int popularity = trackObject.has("popularity") ? trackObject.get("popularity").getAsInt() : 50;
                float duration = trackObject.has("durationMs") ? (float)(trackObject.get("durationMs").getAsDouble() / 60000) : 0;

                songs.add(new Song(null, title, artist, duration, songTempo, popularity));
            }

        } catch (Exception ex) {
            System.out.println("Hiba az API-nal: " + ex.getMessage());
        }

        return songs;
    }
}
