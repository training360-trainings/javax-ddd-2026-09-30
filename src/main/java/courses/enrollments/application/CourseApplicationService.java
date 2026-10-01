package courses.enrollments.application;

import courses.enrollments.application.inboundport.*;
import courses.enrollments.application.outboundport.CourseRepositoryPort;
import courses.enrollments.application.outboundport.EmployeeGatewayPort;
import courses.enrollments.application.usecase.AnnounceUseCase;
import courses.enrollments.application.usecase.CancelAllForEmployeeUseCase;
import courses.enrollments.application.usecase.EnrollUseCase;
import courses.enrollments.domain.enrollments.Course;
import courses.enrollments.domain.enrollments.CourseCode;
import courses.enrollments.domain.enrollments.EmployeeId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseApplicationService implements CourseApplicationServicePort {

    private final AnnounceUseCase announceUseCase;

    private final EnrollUseCase enrollUseCase;

    private final CancelAllForEmployeeUseCase cancelAllForEmployeeUseCase;

    @Override
    public CourseDto announce(AnnounceCommand command) {
        return announceUseCase.announce(command);
    }

    @Override
    public EnrollmentDto enroll(EnrollCommand enrollCommand) {
        return enrollUseCase.enroll(enrollCommand);
    }

    @Override
    @Transactional
    public void cancelAllForEmployee(long employeeId) {
        cancelAllForEmployeeUseCase.cancelAllForEmployee(employeeId);
    }
}
