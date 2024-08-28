package example.micronaut.client_app;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.micronaut.domain.Persona;
import example.micronaut.persona.PersonaSaveCommand;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Commands {

    private static String urlString = "http://localhost:8080/personas";

    public static void addNewPersona(String name, int age, String gender) {
        try (HttpClient client = HttpClient.create(new URL(urlString))) {
            HttpRequest<?> request = HttpRequest.POST("/personas/", new PersonaSaveCommand(name, age, gender));
            client.toBlocking().exchange(request);
            System.out.printf("PERSONA \'%s\' WAS ADDED.\n", name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteById(int id) {
        try (HttpClient client = HttpClient.create(new URL(urlString))) {
            HttpRequest<?> request = HttpRequest.DELETE("/personas/" + id);
            client.toBlocking().exchange(request);
            System.out.printf("PERSONA DELETED BY ID: %d.\n", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Persona> loadDataFromDataBase () {
        List<Persona> resultData = null;
        try {
            HttpClient client = HttpClient.create(new URL(urlString + "/list"));
            HttpRequest<?> request = HttpRequest.GET(urlString + "/list");
            resultData = client.toBlocking().retrieve(request, Argument.of(List.class, Persona.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData;
    }

}
