/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.laborprojekt;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.ArrayList;
import java.util.List;

public class ApiService {

    private static final String API_URL = "https://api.reccobeats.com/v1/track/recommendation";

    public static List<Song> getSongsByTempo(double tempo, List<String> seeds, int size) {
        List<Song> songs = new ArrayList<>();

        try {
            Unirest.config().reset();
            Unirest.config().connectTimeout(0).socketTimeout(0);

            String seedParam = String.join(",", seeds);
            String url = API_URL + "?tempo=" + tempo + "&size=" + size + "&seeds=" + seedParam;

            HttpResponse<String> response = Unirest.get(url)
                    .header("Accept", "application/json")
                    .asString();

            if (response.getStatus() == 200) {
                JsonObject json = new Gson().fromJson(response.getBody(), JsonObject.class);

                if (json.has("content") && json.get("content").isJsonArray()) {
                    JsonArray content = json.getAsJsonArray("content");

                    for (int i = 0; i < content.size(); i++) {
                        JsonObject obj = content.get(i).getAsJsonObject();

                        String title = obj.has("trackTitle") && !obj.get("trackTitle").isJsonNull()
                                ? obj.get("trackTitle").getAsString()
                                : "Unknown Title";

                        String artist = "Unknown";
                        if (obj.has("artists") && obj.get("artists").isJsonArray()) {
                            JsonArray artistsArray = obj.getAsJsonArray("artists");
                            if (artistsArray.size() > 0 && artistsArray.get(0).isJsonObject()) {
                                JsonObject artistObj = artistsArray.get(0).getAsJsonObject();
                                if (artistObj.has("name") && !artistObj.get("name").isJsonNull()) {
                                    artist = artistObj.get("name").getAsString();
                                }
                            }
                        }

                        double songTempo = tempo;
                        float energy = 0.5f; // default

                        if (obj.has("tempo") && !obj.get("tempo").isJsonNull()) {
                            songTempo = obj.get("tempo").getAsDouble();
                        }
                       int popularity = 50; // default
                        if (obj.has("popularity") && !obj.get("popularity").isJsonNull()) {
                            popularity = obj.get("popularity").getAsInt();
                        }

                        float duration = 0f;
                        if (obj.has("durationMs") && !obj.get("durationMs").isJsonNull()) {
                            duration = obj.get("durationMs").getAsFloat() / 1000f / 60f;
                        }
                        songs.add(new Song(null, title, artist, duration, songTempo, popularity));

                    }
                } else {
                    System.out.println("No 'content' array in response.");
                }
            } else {
                System.out.println("API error: " + response.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return songs;
    }
}
