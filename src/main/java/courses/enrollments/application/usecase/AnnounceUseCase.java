package courses.enrollments.application.usecase;

import courses.enrollments.application.inboundport.AnnounceCommand;
import courses.enrollments.application.inboundport.CourseDto;
import courses.enrollments.application.outboundport.CourseRepositoryPort;
import courses.enrollments.application.outboundport.EmployeeGatewayPort;
import courses.enrollments.domain.enrollments.Course;
import courses.enrollments.domain.enrollments.CourseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnnounceUseCase {

    private final CourseRepositoryPort courseRepository;

    public CourseDto announce(AnnounceCommand command) {
        var course = Course.announce(new CourseCode(command.code()), command.title(), command.limit());
        course = courseRepository.save(course);
        return new CourseDto(course.getId(), course.getCourseCode().value(),
                course.getTitle(), course.getLimit());
    }
}
