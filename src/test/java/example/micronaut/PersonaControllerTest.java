package example.micronaut;

import example.micronaut.domain.Persona;
import example.micronaut.persona.PersonaSaveCommand;
import example.micronaut.persona.PersonaUpdateCommand;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.http.uri.UriTemplate;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.micronaut.http.HttpHeaders.LOCATION;
import static io.micronaut.http.HttpStatus.CREATED;
import static io.micronaut.http.HttpStatus.NO_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest
public class PersonaControllerTest {

    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test
    public void supplyAnInvalidOrderTriggersValidationFailure() {
        assertThrows(HttpClientResponseException.class, () ->
                getClient().retrieve(
                        HttpRequest.GET("/personas/list?order=foo"),
                        Argument.of(List.class, Persona.class)));
    }

    @Test
    public void testFindNonExistingPersonaReturns404() {
        assertThrows(HttpClientResponseException.class, () ->
                getClient().retrieve(HttpRequest.GET("/personas/999"), Argument.of(Persona.class)));
    }
/*
    @Test
    public void testPersonaCrudOperations() {
        List<Integer> personaIds = new ArrayList<>();
        HttpResponse<?> response = savePersona("DevOps", 27, "MALE");
        personaIds.add(entityId(response));
        assertEquals(CREATED, response.getStatus());

        response = savePersona("Microservices", 45, "FEMALE");
        assertEquals(CREATED, response.getStatus());

        Integer id = entityId(response);
        personaIds.add(id);
        Persona persona = show(id);
        assertEquals("Microservices", persona.getName());

        response = update(id, "Micro-services", 27, "UND");
        assertEquals(NO_CONTENT, response.getStatus());

        persona = show(id);
        assertEquals("Microservices", persona.getName());

        List<Persona> personas = listPersonas(ListingArguments.builder().build());
        assertEquals(2, personas.size());

        personas = listPersonas(ListingArguments.builder().max(1).build());
        assertEquals(1, personas.size());
        assertEquals("DevOps", personas.get(0).getName());

        personas = listPersonas(ListingArguments.builder().max(1).order("desc").sort("name").build());
        assertEquals(1, personas.size());
        assertEquals("Microservices", personas.get(0).getName());

        personas = listPersonas(ListingArguments.builder().max(1).offset(10).build());
        assertEquals(0, personas.size());

        // cleanup:
        for (int personaId : personaIds) {
            response = delete(personaId);
            assertEquals(NO_CONTENT, response.getStatus());
        }
    }*/

    private List<Persona> listPersonas(ListingArguments args) {
        URI uri = args.of(UriBuilder.of("/personas/list"));
        HttpRequest<?> request = HttpRequest.GET(uri);
        return getClient().retrieve(request, Argument.of(List.class, Persona.class));
    }

    private Persona show(Integer id) {
        String uri = UriTemplate.of("/genres/{id}").expand(Collections.singletonMap("id", id));
        HttpRequest<?> request = HttpRequest.GET(uri);
        return getClient().retrieve(request, Persona.class);
    }

    private HttpResponse<?> update(Integer id, String name, Integer age, String gender) {
        HttpRequest<?> request = HttpRequest.PUT("/personas", new PersonaUpdateCommand(id, name, age, gender));
        return getClient().exchange(request);
    }

    private HttpResponse<?> delete(Integer id) {
        HttpRequest<?> request = HttpRequest.DELETE("/personas/" + id);
        return getClient().exchange(request);
    }

    private Integer entityId(HttpResponse<?> response) {
        String value = response.header(LOCATION);
        if (value == null) {
            return null;
        }

        String path = "/personas/";
        int index = value.indexOf(path);
        if (index != -1) {
            return Integer.valueOf(value.substring(index + path.length()));
        }

        return null;
    }

    private BlockingHttpClient getClient() {
        return httpClient.toBlocking();
    }

    private HttpResponse<?> savePersona(String name, Integer age, String gender) {
        HttpRequest<?> request = HttpRequest.POST("/personas", new PersonaSaveCommand(name, age, gender));
        return getClient().exchange(request);
    }
}
