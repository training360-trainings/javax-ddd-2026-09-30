package courses.enrollments.application.outboundport;

import courses.enrollments.domain.enrollments.Course;

public interface CourseRepository {

    Course save(Course course);

    Course findById(long id);
}
