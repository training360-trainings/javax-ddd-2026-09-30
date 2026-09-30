package courses.enrollments.application.inboundport;

import java.time.LocalDate;

public record EnrollmentDto(long employeeId, LocalDate enrollmentDate) {
}
