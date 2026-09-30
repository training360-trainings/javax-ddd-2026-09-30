package courses.enrollments.application;

import courses.enrollments.application.inboundport.*;
import courses.enrollments.application.outboundport.CourseRepositoryPort;
import courses.enrollments.domain.enrollments.Course;
import courses.enrollments.domain.enrollments.CourseCode;
import courses.enrollments.domain.enrollments.EmployeeId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseApplicationService implements CourseApplicationServicePort {

    private final CourseRepositoryPort courseRepository;

    @Override
    public CourseDto announce(AnnounceCommand command) {
        var course = Course.announce(new CourseCode(command.code()), command.title(), command.limit());
        course = courseRepository.save(course);
        return new CourseDto(course.getId(), course.getCourseCode().value(),
                course.getTitle(), course.getLimit());
    }

    @Override
    public EnrollmentDto enroll(EnrollCommand enrollCommand) {
        var course = courseRepository.findById(enrollCommand.courseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found " + enrollCommand.courseId()));
        var enrollment = course.enroll(new EmployeeId(enrollCommand.employeeId()));
        courseRepository.save(course);
        return new EnrollmentDto(enrollment.employeeId().value(), enrollment.enrollmentDate());
    }
}
