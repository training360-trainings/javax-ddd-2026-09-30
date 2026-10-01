package courses.enrollments.application.outboundport;

import courses.enrollments.application.inboundport.CourseDto;
import courses.enrollments.domain.enrollments.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepositoryPort {

    Course save(Course course);

    Optional<Course> findById(long id);

    List<CourseDto> findAll();

    Optional<CourseDto> findDtoById(long id);
}
