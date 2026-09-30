package courses.enrollments.application.outboundport;

import courses.enrollments.domain.enrollments.Course;

import java.util.Optional;

public interface CourseRepositoryPort {

    Course save(Course course);

    Optional<Course> findById(long id);
}
