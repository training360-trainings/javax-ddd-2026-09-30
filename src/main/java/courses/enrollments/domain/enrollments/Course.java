package courses.enrollments.domain.enrollments;

import lombok.Getter;

import java.util.List;

@Getter
public class Course {

    private Long id;

    private CourseCode courseCode;

    private String title;

    private int limit;

    private List<Enrollment> enrollments;

}
