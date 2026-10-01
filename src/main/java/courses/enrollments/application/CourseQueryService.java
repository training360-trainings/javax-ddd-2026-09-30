package courses.enrollments.application;

import courses.enrollments.adapter.repository.CourseRepository;
import courses.enrollments.application.inboundport.CourseDto;
import courses.enrollments.application.inboundport.CourseQueryServicePort;
import courses.enrollments.application.outboundport.CourseRepositoryPort;
import courses.enrollments.domain.enrollments.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseQueryService implements CourseQueryServicePort {

    private final CourseRepositoryPort courseRepository;

    @Override
    public List<CourseDto> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public CourseDto findById(long id) {
        return courseRepository.findDtoById(id)
                .orElseThrow(() -> new IllegalArgumentException("No course found with id " + id));
    }
}
