package courses.enrollments.adapter.gateway;

import courses.employees.EmployeeHostGateway;
import courses.enrollments.application.outboundport.EmployeeGatewayPort;
import courses.enrollments.domain.enrollments.EmployeeId;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeesGateway implements EmployeeGatewayPort {

    private final EmployeeHostGateway hostGateway;

    @Override
    public boolean employeeHasNotJoined(EmployeeId id) {
        return !hostGateway.hasJoined(id.value());
    }
}
