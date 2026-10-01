package courses;

import courses.employees.EmployeeDto;
import courses.employees.EmployeeService;
import courses.enrollments.application.inboundport.AnnounceCommand;
import courses.enrollments.application.inboundport.CourseApplicationServicePort;
import courses.enrollments.application.inboundport.CourseQueryServicePort;
import courses.enrollments.application.inboundport.EnrollCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql(statements = {"delete from employees",
"delete from enrollments",
"delete from courses"})
public class LeaveIT {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    CourseApplicationServicePort courseApplicationService;

    @Autowired
    CourseQueryServicePort courseQueryService;

    @Test
    void leave() {
        var employee = employeeService.join(new EmployeeDto(null, "John Doe"));
        var course = courseApplicationService.announce(new AnnounceCommand("JAVAX-DDD", "DDD", 5));
        courseApplicationService.enroll(new EnrollCommand(course.id(), employee.id()));
        employeeService.leave(employee.id());

        assertThat(courseQueryService.findAllEnrollmentsForCourse(course.id())).isEmpty();
    }

}
