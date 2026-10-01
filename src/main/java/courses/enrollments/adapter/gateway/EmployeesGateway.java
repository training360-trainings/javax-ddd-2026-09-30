package courses.enrollments.adapter.gateway;

import courses.employees.EmployeeHostGateway;
import courses.enrollments.application.outboundport.EmployeeGatewayPort;
import courses.enrollments.domain.enrollments.EmployeeId;
import infra.Gateway;
import lombok.RequiredArgsConstructor;

@Gateway
@RequiredArgsConstructor
public class EmployeesGateway implements EmployeeGatewayPort {

    private final EmployeeHostGateway hostGateway;

    @Override
    public boolean employeeHasNotJoined(EmployeeId id) {
        return !hostGateway.hasJoined(id.value());
    }
}
