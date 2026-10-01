package courses.enrollments.application;

import courses.enrollments.domain.enrollments.Course;
import courses.enrollments.domain.enrollments.EmployeeId;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CancelAllDomainService {

    private List<Course> courses;

    public void cancelAll(EmployeeId employeeId) {
        for (Course course : courses) {
            course.cancelFor(employeeId);
        }
    }
}
