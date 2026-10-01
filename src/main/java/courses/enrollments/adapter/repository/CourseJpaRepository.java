package courses.enrollments.adapter.repository;

import courses.enrollments.application.inboundport.EnrollmentDto;
import courses.enrollments.domain.enrollments.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseJpaRepository extends JpaRepository<CourseJpaEntity, Long> {

    <T> List<T> findAllBy(Class<T> clazz);

    <T> Optional<T> findDtoById(Class<T> clazz, long id);

    @Query("""
    select c from CourseJpaEntity c join fetch c.enrollments
    where c.id in (select e.course.id from EnrollmentJpaEntity e where e.employeeId = :employeeId)
""")
    List<CourseJpaEntity> findAllWithEnrolledEmployee(long employeeId);

    @Query("""
    select new courses.enrollments.application.inboundport.EnrollmentDto(e.employeeId, e.enrollmentDate)
        from EnrollmentJpaEntity e where e.course.id = :courseId
""")
    List<EnrollmentDto> findAllEnrollmentsForCourse(long courseId);

}
