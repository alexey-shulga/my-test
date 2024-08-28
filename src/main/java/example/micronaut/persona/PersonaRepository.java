package example.micronaut.persona;

import example.micronaut.ListingArguments;
import example.micronaut.domain.Persona;
import io.micronaut.core.annotation.NonNull;

import io.micronaut.core.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface PersonaRepository {

    @NonNull
    Optional<Persona> findById(int id);

    @NonNull
    Persona save(@NonNull @NotBlank String name, @Nullable Integer age, @NonNull @NotBlank String gender);

    void deleteById(int id);

    @NonNull
    List<Persona> findAll(@NonNull @NotNull ListingArguments args);

    int update(int id, @NonNull @NotBlank String name, @Nullable Integer age, @NonNull @NotBlank String gender);
}
