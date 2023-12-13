package jun.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"student"})
@Builder
@Entity
@Table(name = "student_profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne()
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "evaluations")
    private Long evaluation;

    public void setStudent(Student student){
       student.setProfile(this);
        this.student = student;

    }
}
