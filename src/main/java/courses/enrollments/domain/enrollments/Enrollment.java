package courses.enrollments.domain.enrollments;

import java.time.LocalDate;

public record Enrollment(EmployeeId employeeId, LocalDate enrollmentDate) {
}
