package example.micronaut.persona;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;

import jakarta.validation.constraints.NotBlank;

@Serdeable
public class PersonaUpdateCommand {

    private int id;

    @NotBlank
    @NonNull
    private String name;

    @Nullable
    Integer age;

    @NotBlank
    @NonNull
    private String gender;

    public PersonaUpdateCommand(int id, @NonNull @NotBlank String name, @Nullable Integer age, @NonNull @NotBlank String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @Nullable
    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public @NotBlank @NonNull String getGender() {
        return gender;
    }

    public void setGender(@NonNull @NotBlank String gender) {
        this.gender = gender;
    }
}
