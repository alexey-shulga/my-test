package example.micronaut;

import example.micronaut.domain.Persona;
import example.micronaut.persona.PersonaRepository;
import example.micronaut.persona.PersonaSaveCommand;
import example.micronaut.persona.PersonaUpdateCommand;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller("/personas")
public class PersonaController {

    private final PersonaRepository personaRepository;

    public PersonaController(PersonaRepository personaRepository) { // <2>
        this.personaRepository = personaRepository;
    }

    @Get("/{id}")
    public Persona show(int id) {
        return personaRepository
                .findById(id)
                .orElse(null);
    }

    @Get("/delete/{id}")
    public String deleteUser(int id) {
        personaRepository.deleteById(id);
        return String.format("PERSONA DELETED BY ID: %d. \nINPUT .../personas/list TO SEE ALL PERSONAS.", id);
    }

    @Put
    public HttpResponse<?> update(@Body @Valid PersonaUpdateCommand command) {
        personaRepository.update(command.getId(), command.getName(), command.getAge(), command.getGender());
        return HttpResponse
                .noContent()
                .header(HttpHeaders.LOCATION, location((int) command.getId()).getPath());
    }

    @Get(value = "/list{?args*}")
    public List<Persona> list(@Valid ListingArguments args) {
        return personaRepository.findAll(args);
    }

    @Post
    public HttpResponse<Persona> save(@Body @Valid PersonaSaveCommand cmd) {
        System.out.println("... POST REQUEST ...");
        Persona persona = personaRepository.save(cmd.getName(), cmd.getAge(), cmd.getGender());
        return HttpResponse
                .created(persona)
                .headers(headers -> headers.location(location(persona.getId())));
    }

    @Delete("/{id}")
    public HttpResponse<?> delete(int id) {
        System.out.println("... DELETE REQUEST ...");
        personaRepository.deleteById(id);
        return HttpResponse.noContent();
    }

    private URI location(int id) {
        return URI.create("/personas/" + id);
    }
}
