package br.unitins.service.perspectiveService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PerspectiveService {

    private static final String API_KEY = "AIzaSyD-bC9FK5G7fEZbOzA7Mc9v7FN2wP1dHEM";
    private static final String URL = "https://commentanalyzer.googleapis.com/v1alpha1/comments:analyze?key=" + API_KEY;

    public static double analisarTexto(String texto) throws Exception {
        JsonObject comment = new JsonObject();
        comment.addProperty("text", texto);

        JsonObject requestBody = new JsonObject();
        requestBody.add("comment", comment);
        requestBody.add("languages", JsonParser.parseString("[\"pt\"]").getAsJsonArray());
        requestBody.add("requestedAttributes", JsonParser.parseString("{\"TOXICITY\": {}}").getAsJsonObject());

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject result = JsonParser.parseString(response.body()).getAsJsonObject();

        double toxicidade = result
                .getAsJsonObject("attributeScores")
                .getAsJsonObject("TOXICITY")
                .getAsJsonObject("summaryScore")
                .get("value")
                .getAsDouble();

        return toxicidade;
    }
}



