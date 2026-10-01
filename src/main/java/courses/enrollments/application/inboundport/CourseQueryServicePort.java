package courses.enrollments.application.inboundport;

import java.util.List;

public interface CourseQueryServicePort {

    List<CourseDto> findAll();

    CourseDto findById(long id);
}
