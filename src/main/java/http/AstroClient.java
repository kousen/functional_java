package http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class AstroClient {

    public String getJsonResponse() {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(2))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.open-notify.org/astros.json"))
                .GET() // default (could leave that out)
                .build();

        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status code: " + response.statusCode());
            System.out.println("Headers: " + response.headers());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

    public HttpResponse<Void> getResponseToHeadRequest(String site) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(site))
                .method("HEAD", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<Void> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.discarding());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    public AstroResponse getAstroResponse() {
        // Jackson mapper (Gson doesn't work with records)
        JsonMapper jsonMapper = new JsonMapper();
        try {
            return jsonMapper.readValue(getJsonResponse(), AstroResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
