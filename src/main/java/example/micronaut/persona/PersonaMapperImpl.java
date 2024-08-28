package example.micronaut.persona;

import example.micronaut.domain.Persona;
import jakarta.inject.Singleton;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;

@Singleton
public class PersonaMapperImpl implements PersonaMapper {

    private final SqlSessionFactory sqlSessionFactory;

    public PersonaMapperImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Persona findById(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getPersonaMapper(sqlSession).findById(id);
        }
    }

    @Override
    public void save(Persona persona) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            getPersonaMapper(sqlSession).save(persona);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteById(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            getPersonaMapper(sqlSession).deleteById(id);
            sqlSession.commit();
        }
    }

    @Override
    public void update(int id, String name, int age, String gender) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            getPersonaMapper(sqlSession).update(id, name, age, gender);
            sqlSession.commit();
        }
    }

    @Override
    public List<Persona> findAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getPersonaMapper(sqlSession).findAll();
        }
    }

    @Override
    public List<Persona> findAllBySortAndOrder(@NotNull @Pattern(regexp = "id|name") String sort,
                                               @NotNull @Pattern(regexp = "asc|ASC|desc|DESC") String order) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getPersonaMapper(sqlSession).findAllBySortAndOrder(sort, order);
        }
    }

    @Override
    public List<Persona> findAllByOffsetAndMaxAndSortAndOrder(@PositiveOrZero int offset,
                                                              @Positive int max,
                                                              @NotNull @Pattern(regexp = "id|name") String sort,
                                                              @NotNull @Pattern(regexp = "asc|ASC|desc|DESC") String order) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getPersonaMapper(sqlSession).findAllByOffsetAndMaxAndSortAndOrder(offset, max, sort, order);
        }
    }

    @Override
    public List<Persona> findAllByOffsetAndMax(@PositiveOrZero int offset,
                                               @Positive int max) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getPersonaMapper(sqlSession).findAllByOffsetAndMax(offset, max);
        }
    }

    private PersonaMapper getPersonaMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(PersonaMapper.class);
    }
}
