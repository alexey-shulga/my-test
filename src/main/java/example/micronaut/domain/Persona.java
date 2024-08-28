package example.micronaut.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;

import jakarta.validation.constraints.NotBlank;

@Serdeable
public class Persona {

    @Nullable
    private Integer id;

    @NonNull
    @NotBlank
    private String name;

    @Nullable
    private Integer age;

    @NonNull
    @NotBlank
    private String gender;

    // При использовании конструктора MyBatis пытается привести String к int и Postgresql ругается
    // Пока что использую сеттер для установления значений полям
    /*public Persona(@NonNull @NotBlank String name, @Nullable Integer age, @NonNull @NotBlank String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }*/

    @Nullable
    public Integer getId() {
        return id;
    }

    public void setId(@Nullable Integer id) {
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

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", name='" + name + '\'' +
                "age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
