package example.micronaut;

import example.micronaut.persona.PersonaRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest
public class PersonaRepositoryImplTest {

    @Inject
    PersonaRepository personaRepository;

    @Test
    public void constraintsAreValidatedForFindAll() {
        assertThrows(ConstraintViolationException.class, () -> {
            personaRepository.findAll(null);
        });
    }

    @Test
    public void constraintsAreValidatedForSave() {
        assertThrows(ConstraintViolationException.class, () -> {
            personaRepository.save(null, 0, null);
        });
    }

    @Test
    public void constraintsAreValidatedForUpdateNameIsNull() {
        assertThrows(ConstraintViolationException.class, () -> {
            personaRepository.update(12, null, 7, null);
        });
    }

    @Test
    public void constraintsAreValidatedForUpdateNameIsBlank() {
        assertThrows(ConstraintViolationException.class, () -> {
            personaRepository.update(4, "", -132, null);
        });
    }
}
