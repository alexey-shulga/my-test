package example.micronaut.persona;

import example.micronaut.ListingArguments;
import example.micronaut.domain.Persona;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import jakarta.inject.Singleton;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Singleton
public class PersonaRepositoryImpl implements PersonaRepository {

    private final PersonaMapper personaMapper;

    public PersonaRepositoryImpl(PersonaMapper personaMapper) {
        this.personaMapper = personaMapper;
    }

    @Override
    @NonNull
    public Optional<Persona> findById(int id) {
        return Optional.ofNullable(personaMapper.findById(id));
    }

    @Override
    @NonNull
    public Persona save(@NonNull @NotBlank String name, @Nullable Integer age, @NonNull @NotBlank String gender) {
        Persona persona = new Persona();
        persona.setName(name); persona.setAge(age); persona.setGender(gender);
        personaMapper.save(persona);
        return persona;
    }

    @Override
    public void deleteById(int id) {
        findById(id).ifPresent(persona -> personaMapper.deleteById(id));
    }

    @NonNull
    public List<Persona> findAll(@NonNull @NotNull ListingArguments args) {

        if (args.getMax() != null && args.getSort() != null && args.getOffset() != null && args.getOrder() != null) {
            return personaMapper.findAllByOffsetAndMaxAndSortAndOrder(
                    args.getOffset(),
                    args.getMax(),
                    args.getSort(),
                    args.getOrder());
        }

        if (args.getMax() != null && args.getOffset()!= null && (args.getSort() == null || args.getOrder() == null)) {
            return personaMapper.findAllByOffsetAndMax(args.getOffset(), args.getMax());
        }

        if ((args.getMax() == null || args.getOffset() == null) && args.getSort() != null && args.getOrder() !=null) {
            return personaMapper.findAllBySortAndOrder(args.getSort(), args.getOrder());
        }

        return personaMapper.findAll();
    }

    @Override
    public int update(int id, @NonNull @NotBlank String name, @Nullable Integer age, @NonNull @NotBlank String gender) {
        personaMapper.update(id, name, age, gender);
        return -1;
    }
}
