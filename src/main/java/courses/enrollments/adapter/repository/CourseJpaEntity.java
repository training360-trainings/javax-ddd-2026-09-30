package courses.enrollments.adapter.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courses")
public class CourseJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    @Column(nullable = false)
    private String title;

    @Column(name = "enrollment_limit", nullable = false)
    private int limit;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,  orphanRemoval = true,
        mappedBy = "course")
    private List<EnrollmentJpaEntity> enrollments;

}
