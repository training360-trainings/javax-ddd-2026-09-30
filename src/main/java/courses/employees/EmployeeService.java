package courses.employees;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService implements EmployeeHostGateway {

    private final EmployeeRepository employeeRepository;

    private final EmployeeEventPublisher employeeEventPublisher;

    public EmployeeDto join(EmployeeDto employee) {
        var entity = new  Employee(null, employee.name());
        entity = employeeRepository.save(entity);
        return new EmployeeDto(entity.getId(), entity.getName());
    }

    public List<EmployeeDto> findAllBy() {
        return employeeRepository.findAllBy(EmployeeDto.class);
    }

    public EmployeeDto findById(long id) {
        return employeeRepository.findDtoById(id, EmployeeDto.class)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id " + id));
    }

    @Override
    public boolean hasJoined(long employeeId) {
        return employeeRepository.existsById(employeeId);
    }

    public void leave(long employeeId) {
        employeeRepository.deleteById(employeeId);
        employeeEventPublisher.employeeHasLeft(employeeId);
    }
}
