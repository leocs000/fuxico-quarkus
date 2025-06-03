package br.unitins.service.perspectiveService;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import jakarta.json.*;

public class PerspectiveService {
    private static final String API_KEY = "AIzaSyD-bC9FK5G7fEZbOzA7Mc9v7FN2wP1dHEM"; 

    public static double analisarTexto(String comentario) throws Exception {
        URL url = new URL("https://commentanalyzer.googleapis.com/v1alpha1/comments:analyze?key=" + API_KEY);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        JsonObject requestBody = Json.createObjectBuilder()
            .add("comment", Json.createObjectBuilder().add("text", comentario))
            .add("languages", Json.createArrayBuilder().add("pt"))
            .add("requestedAttributes", Json.createObjectBuilder().add("TOXICITY", Json.createObjectBuilder()))
            .build();

        try (OutputStream os = con.getOutputStream()) {
            os.write(requestBody.toString().getBytes());
        }

        try (InputStream is = con.getInputStream();
             JsonReader reader = Json.createReader(is)) {
            JsonObject response = reader.readObject();
            return response.getJsonObject("attributeScores")
                           .getJsonObject("TOXICITY")
                           .getJsonNumber("summaryScore")
                           .doubleValue();
        }
    }
}
