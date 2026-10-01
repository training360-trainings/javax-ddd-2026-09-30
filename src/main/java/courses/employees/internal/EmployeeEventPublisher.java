package courses.employees.internal;

import courses.employees.EmployeeHasLeft;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void employeeHasLeft(long employeeId) {
        applicationEventPublisher.publishEvent(new EmployeeHasLeft(employeeId));
    }
}
