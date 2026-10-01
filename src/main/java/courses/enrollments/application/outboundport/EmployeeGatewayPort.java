package courses.enrollments.application.outboundport;

import courses.enrollments.domain.enrollments.EmployeeId;

public interface EmployeeGatewayPort {

    boolean employeeHasNotJoined(EmployeeId id);
}
