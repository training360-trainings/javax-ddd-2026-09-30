package courses.enrollments.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseJpaRepository extends JpaRepository<CourseJpaEntity, Long> {

    <T> List<T> findAllBy(Class<T> clazz);

    <T> Optional<T> findDtoById(Class<T> clazz, long id);
}
