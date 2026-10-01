package courses.enrollments.adapter.controller;

import courses.enrollments.application.inboundport.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseApplicationServicePort service;

    private final CourseQueryServicePort queryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDto announce(@RequestBody AnnounceCommand announceCommand) {
        return service.announce(announceCommand);
    }

    @PutMapping("/{courseId}/enrollments")
    public EnrollmentDto enroll(@PathVariable long courseId, @RequestBody EnrollCommand enrollCommand) {
        if (courseId != enrollCommand.courseId()) {
            throw new IllegalArgumentException("Course id mismatch: %d != %d".
                    formatted(courseId, enrollCommand.courseId()));
        }
        return service.enroll(enrollCommand);
    }

    @GetMapping
    public List<CourseDto> findAll() {
        return queryService.findAll();
    }

    @GetMapping("/{id}")
    public CourseDto findById(@PathVariable long id) {
        return queryService.findById(id);
    }
}
