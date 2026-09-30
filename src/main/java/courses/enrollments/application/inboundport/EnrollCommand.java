package courses.enrollments.application.inboundport;

public record EnrollCommand(long courseId, long employeeId) {
}
