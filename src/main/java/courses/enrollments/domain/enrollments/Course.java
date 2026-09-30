package courses.enrollments.domain.enrollments;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jspecify.annotations.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class Course {

    private Long id;

    private CourseCode courseCode;

    private String title;

    private int limit;

    private List<Enrollment> enrollments;

    public static Course announce(CourseCode courseCode, String title, int limit) {
        Objects.requireNonNull(courseCode);
        Objects.requireNonNull(title);
        if (limit <= 0) {
            throw new IllegalArgumentException("Limit must be greater than 0");
        }
        return new Course(null, courseCode, title, limit, new ArrayList<>());
    }

    public Enrollment enroll(EmployeeId employeeId) {
        Objects.requireNonNull(employeeId);
        var found = enrollments.stream().filter(e -> e.employeeId().equals(employeeId)).findAny();
        if (found.isPresent()) {
            return found.get();
        }
        if (limit <= enrollments.size()) {
            throw new IllegalArgumentException("Already full");
        }
        var enrollment = new Enrollment(employeeId, LocalDate.now());
        enrollments.add(enrollment);
        return enrollment;
    }

}
