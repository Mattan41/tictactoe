package org.kruskopf.tictactoe;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpPublish {
    private static String messageToSend = "";

    public static void main(String[] args) throws IOException, InterruptedException {
        //Reuse same client object during our programs lifetime
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://ntfy.sh/M4TS_F4NT4STISK4_SPEL"))
                .POST(HttpRequest.BodyPublishers.ofString(messageToSend))
                .build();

        HttpResponse<Void> response =
                client.send(request,HttpResponse.BodyHandlers.discarding());

        System.out.println(response.body());
    }

    public static void sendMessageToServer(int index) {
        messageToSend = String.valueOf(index);
    }
}
