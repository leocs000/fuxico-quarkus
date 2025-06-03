package br.unitins.service.perspectiveService;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import jakarta.json.*;

public class PerspectiveService {
    private static final String API_KEY = "AIzaSyD-bC9FK5G7fEZbOzA7Mc9v7FN2wP1dHEM"; // Substitua pela sua chave

    public static double analisarTexto(String comentario) throws Exception {
        URI uri = new URI("https://commentanalyzer.googleapis.com/v1alpha1/comments:analyze?key=" + API_KEY);
        URL url = uri.toURL();
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

            // Verificação segura para evitar ClassCastException
            JsonObject toxicityScore = response.getJsonObject("attributeScores").getJsonObject("TOXICITY");
            if (toxicityScore.containsKey("summaryScore") && toxicityScore.get("summaryScore") instanceof JsonNumber) {
                return toxicityScore.getJsonNumber("summaryScore").doubleValue();
            } else {
                return -1.0; // Retorna -1 caso a API não retorne um score válido
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1.0; // Retorna -1 em caso de erro na requisição
        }
    }
}

