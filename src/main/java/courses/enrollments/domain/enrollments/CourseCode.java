package courses.enrollments.domain.enrollments;

public record CourseCode(String value) {

    public CourseCode {
        if (value == null || value.length() < 2) {
            throw new IllegalArgumentException("Course code invalid: " + value);
        }
    }
}
