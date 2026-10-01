package courses.enrollments.application.usecase;

import courses.enrollments.application.CancelAllDomainService;
import courses.enrollments.application.outboundport.CourseRepositoryPort;
import courses.enrollments.domain.enrollments.EmployeeId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CancelAllForEmployeeUseCase {

    private final CourseRepositoryPort courseRepository;

    @Transactional
    public void cancelAllForEmployee(long employeeId) {
        var courses = courseRepository.findAllWithEnrolledEmployee(employeeId);
        var cancelAll = new CancelAllDomainService(courses);
        cancelAll.cancelAll(new EmployeeId(employeeId));
        for (var course : courses) {
            courseRepository.save(course);
        }
    }
}
