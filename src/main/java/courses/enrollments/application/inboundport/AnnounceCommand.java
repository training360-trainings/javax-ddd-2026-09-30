package courses.enrollments.application.inboundport;

public record AnnounceCommand(String code, String title, int limit) {
}
