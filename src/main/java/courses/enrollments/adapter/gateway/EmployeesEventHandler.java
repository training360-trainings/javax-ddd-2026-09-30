package courses.enrollments.adapter.gateway;

import courses.employees.EmployeeHasLeft;
import courses.enrollments.application.inboundport.CourseApplicationServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeesEventHandler {

    private final CourseApplicationServicePort service;

    @EventListener
    public void handleEvent(EmployeeHasLeft event) {
        service.cancelAllForEmployee(event.employeeId());
    }
}
