package courses.employees.internal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    <T> Optional<T> findDtoById(long id, Class<T> clazz);

    <T> List<T> findAllBy(Class<T> clazz);
}
