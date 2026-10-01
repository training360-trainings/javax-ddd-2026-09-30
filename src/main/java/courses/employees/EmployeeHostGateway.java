package courses.employees;

import org.springframework.stereotype.Component;

public interface EmployeeHostGateway {

    boolean hasJoined(long employeeId);
}
