package courses.enrollments.application;

import courses.enrollments.application.inboundport.*;
import courses.enrollments.application.outboundport.CourseRepositoryPort;
import courses.enrollments.application.outboundport.EmployeeGatewayPort;
import courses.enrollments.domain.enrollments.Course;
import courses.enrollments.domain.enrollments.CourseCode;
import courses.enrollments.domain.enrollments.EmployeeId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseApplicationService implements CourseApplicationServicePort {

    private final CourseRepositoryPort courseRepository;

    private final EmployeeGatewayPort employeeGateway;

    @Override
    public CourseDto announce(AnnounceCommand command) {
        var course = Course.announce(new CourseCode(command.code()), command.title(), command.limit());
        course = courseRepository.save(course);
        return new CourseDto(course.getId(), course.getCourseCode().value(),
                course.getTitle(), course.getLimit());
    }

    @Override
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

    @Override
    @Transactional
    public void cancelAllForEmployee(long employeeId) {
        var courses = courseRepository.findAllWithEnrolledEmployee(employeeId);
        var cancelAll = new CancelAllDomainService(courses);
        cancelAll.cancelAll(new EmployeeId(employeeId));
        for (var course : courses) {
            courseRepository.save(course);
        }
    }
}
