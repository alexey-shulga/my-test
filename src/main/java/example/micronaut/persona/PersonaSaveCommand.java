package example.micronaut.persona;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;

@Serdeable
public class PersonaSaveCommand {

    @NotBlank
    @NonNull
    private String name;

    @Nullable
    private Integer age;

    @NotBlank
    @NonNull
    private String gender;

    public PersonaSaveCommand(@NonNull @NotBlank String name, @Nullable Integer age, @NonNull @NotBlank String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
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

    public void setAge(@Nullable Integer age) {
        this.age = age;
    }

    @NonNull
    public String getGender() {
        return gender;
    }

    public void setGender(@NonNull String gender) {
        this.gender = gender;
    }

}
