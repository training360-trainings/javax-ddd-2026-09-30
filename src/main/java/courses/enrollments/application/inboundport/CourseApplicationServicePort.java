package courses.enrollments.application.inboundport;

public interface CourseApplicationServicePort {

    CourseDto announce(AnnounceCommand announceCommand);

    EnrollmentDto enroll(EnrollCommand enrollCommand);
}
