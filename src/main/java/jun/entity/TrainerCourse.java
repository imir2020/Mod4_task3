package jun.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"course","trainer"})
@Builder
@Entity
@Table(name = "trainers_courses")//,schema = "public"
public class TrainerCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @Column(name = "created_at")
    private Instant instant;

    public void setTrainer(Trainer trainer){
        this.trainer = trainer;
        trainer.getTrainerCourses().add(this);
    }

    public void setCourse(Course course){
        this.course = course;
        course.getTrainerCourses().add(this);
    }




}
