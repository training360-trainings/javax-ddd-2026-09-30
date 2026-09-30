package courses.enrollments.domain.enrollments;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void enroll() {
        var course = Course.announce(new CourseCode("JAVAX-DDD"), "Domain Driven Design", 5);
        course.enroll(new EmployeeId(15));
        assertThat(course.getEnrollments())
                .anyMatch(e -> e.employeeId().equals(new EmployeeId(15)));
    }

    @Test
    void alreadyEnrolled() {
        var course = Course.announce(new CourseCode("JAVAX-DDD"), "Domain Driven Design", 5);
        course.enroll(new EmployeeId(15));
        course.enroll(new EmployeeId(15));
        assertThat(course.getEnrollments()).hasSize(1);
    }

    @Test
    void full() {
        var course = Course.announce(new CourseCode("JAVAX-DDD"), "Domain Driven Design", 5);
        IntStream.range(0, 5).forEach(i -> course.enroll(new EmployeeId(i)));
        assertThrows(IllegalArgumentException.class, () -> course.enroll(new EmployeeId(15)));
    }

}