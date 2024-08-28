package example.micronaut.persona;

import example.micronaut.domain.Persona;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;

public interface PersonaMapper {

    @Select("select * from persona where id=#{id}")
    Persona findById(int id);

    @Insert("insert into persona(name, age, gender) values(#{name}, #{age}, #{gender})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Persona persona);

    @Delete("delete from persona where id=#{id}")
    void deleteById(int id);

    @Update("update persona set name=#{name}, age=#{age}, gender=#{gender} where id=#{id}")
    void update(@Param("id") int id, @Param("name") String name, @Param("age") int age, @Param("gender") String gender);

    @Select("select * from persona")
    List<Persona> findAll();

    @Select("select * from persona order by ${sort} ${order}")
    List<Persona> findAllBySortAndOrder(@NotNull @Pattern(regexp = "id|name") String sort,
                                        @NotNull @Pattern(regexp = "asc|ASC|desc|DESC") String order);

    @Select("select * from persona order by ${sort} ${order} limit ${offset}, ${max}")
    List<Persona> findAllByOffsetAndMaxAndSortAndOrder(@PositiveOrZero int offset,
                                                       @Positive int max,
                                                       @NotNull @Pattern(regexp = "id|name") String sort,
                                                       @NotNull @Pattern(regexp = "asc|ASC|desc|DESC") String order);

    @Select("select * from persona limit ${offset}, ${max}")
    List<Persona> findAllByOffsetAndMax(@PositiveOrZero int offset, @Positive int max);
}
