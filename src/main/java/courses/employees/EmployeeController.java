package courses.employees;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public EmployeeDto join(@RequestBody EmployeeDto employee) {
        return employeeService.join(employee);
    }

    @GetMapping
    public List<EmployeeDto> findAllBy() {
        return employeeService.findAllBy();
    }

    @GetMapping("/{id}")
    public EmployeeDto findById(@PathVariable long id) {
        return employeeService.findById(id);
    }
}
