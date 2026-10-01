package courses.enrollments.application.usecase;

import courses.enrollments.application.inboundport.EnrollCommand;
import courses.enrollments.application.inboundport.EnrollmentDto;
import courses.enrollments.application.outboundport.CourseRepositoryPort;
import courses.enrollments.application.outboundport.EmployeeGatewayPort;
import courses.enrollments.domain.enrollments.EmployeeId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnrollUseCase {

    private final CourseRepositoryPort courseRepository;

    private final EmployeeGatewayPort employeeGateway;

    public EnrollmentDto enroll(EnrollCommand enrollCommand) {
        var id = new EmployeeId(enrollCommand.employeeId());
        if (employeeGateway.employeeHasNotJoined(id)) {
            throw new IllegalArgumentException("Employee with id %d does not joined".formatted(id.value()));
        }
        var course = courseRepository.findById(enrollCommand.courseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found " + enrollCommand.courseId()));
        var enrollment = course.enroll(id);
        courseRepository.save(course);
        return new EnrollmentDto(enrollment.employeeId().value(), enrollment.enrollmentDate());
    }
}
