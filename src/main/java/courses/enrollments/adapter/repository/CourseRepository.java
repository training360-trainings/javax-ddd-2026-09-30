package courses.enrollments.adapter.repository;

import courses.enrollments.application.outboundport.CourseRepositoryPort;
import courses.enrollments.domain.enrollments.Course;
import courses.enrollments.domain.enrollments.CourseCode;
import courses.enrollments.domain.enrollments.EmployeeId;
import courses.enrollments.domain.enrollments.Enrollment;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CourseRepository implements CourseRepositoryPort {

    private final CourseJpaRepository courseJpaRepository;

    @Override
    public Course save(Course course) {

        var entity = new CourseJpaEntity(course.getId(), course.getCourseCode().value(), course.getTitle(),
                course.getLimit(), new ArrayList<>());
        for (var enrollment : course.getEnrollments()) {
            entity.getEnrollments().add(new EnrollmentJpaEntity(null,
                    enrollment.employeeId().value(), enrollment.enrollmentDate(), entity));
        }
        entity = courseJpaRepository.save(entity);
        return convert(entity);
    }

    @Override
    public Optional<Course> findById(long id) {
        var entity = courseJpaRepository.findById(id);
        return entity.map(this::convert);
    }

    private @NonNull Course convert(CourseJpaEntity entity) {
        var domain = new Course(entity.getId(), new CourseCode(entity.getCode()),
                entity.getTitle(), entity.getLimit(), new ArrayList<>());
        for (var enrollment : entity.getEnrollments()) {
            domain.getEnrollments().add(new Enrollment(new EmployeeId(enrollment.getEmployeeId()), enrollment.getEnrollmentDate()));
        }
        return domain;
    }

}
