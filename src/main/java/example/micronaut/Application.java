package example.micronaut;

import example.micronaut.client_app.NewClient;
import io.micronaut.runtime.Micronaut;

public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
        NewClient.run();
    }

}