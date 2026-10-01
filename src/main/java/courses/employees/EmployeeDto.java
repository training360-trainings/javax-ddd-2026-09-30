package courses.employees;

import courses.employees.internal.Employee;

import java.io.Serializable;

/**
 * DTO for {@link Employee}
 */
public record EmployeeDto(Long id, String name) implements Serializable {
}