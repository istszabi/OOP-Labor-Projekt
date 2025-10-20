/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.laborprojekt;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author istsz
 */
public class ApiService {

    private static final String API_URL = "https://api.reccobeats.com/v1/track/recommendation";
    
    public static List<Song> getSongsByBpm(double bpm) {
        List<Song> songs = new ArrayList<>();
        int bpmInt = (int) Math.round(bpm);
        try {
            String url = API_URL + "?bpm=" + bpmInt + "&limit=10";
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JsonObject json = new Gson().fromJson(response.body(), JsonObject.class);
                JsonArray data = json.getAsJsonArray("data");

                for (int i = 0; i < data.size(); i++) {
                    JsonObject obj = data.get(i).getAsJsonObject();
                    
                    String id = obj.has("id") && !obj.get("id").isJsonNull() ? obj.get("id").getAsString() : "N/A";
                    
                    String title = obj.get("trackTitle").getAsString();
                    
                    
                    JsonArray artistsArray = obj.getAsJsonArray("artists");
                    String artist = (artistsArray.size() > 0) ? artistsArray.get(0).getAsJsonObject().get("name").getAsString() : "Unknown";

                    int duration = obj.has("durationMs") ? obj.get("durationMs").getAsInt() / 1000 : 180;

                    double songBpm = obj.has("tempo") ? obj.get("tempo").getAsDouble() : bpmInt;

                    Song song = new Song(id, title, artist, duration, songBpm);
                    songs.add(song);
                }
                
            } else {
                System.out.println("API hiba:" + response.statusCode());
            }

        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return songs;
    }
}
