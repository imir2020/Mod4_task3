package jun.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"course","profile"})
@Builder
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "name")
    private String name;

    @Column(name = "middle_name")
    private String middleName;

    @ManyToOne()
    @JoinColumn(name = "course_name")
    private Course course;

    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    private Profile profile;

    public void setProfile(Profile profile){
        this.profile = profile;
    }
}
