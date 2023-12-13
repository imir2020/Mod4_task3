package jun;

import jakarta.persistence.Column;
import jun.entity.*;
import jun.util.HibernateUtil;
import lombok.Cleanup;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Подумать над тем, какие тесты на самом деле нужны
 * в плане их функционала. Пока здесь только образцы.
 */
public class HibernateRunnerTest {
    @Test
    public void createCourse() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Course course = Course.builder()
                .name("Java Course")
                .build();
        session.save(course);
        session.getTransaction().commit();
    }

    @Test
    public void addStudentOnCourse() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Course course = session.get(Course.class, 12L);
        int i = 7;
        Student student = Student.builder()
                .lastName("Lastname" + i)
                .name("Name" + i)
                .build();

        course.addStudent(student);
        session.save(student);
        session.getTransaction().commit();
    }

    @Test
    public void findAllStudentsOnCourse() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Course course = session.get(Course.class, 12L);
        course.getStudents().forEach(student -> System.out.println(student.getName()));
        session.getTransaction().commit();
    }

    @Test
    public void createProfile() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Profile profile = Profile.builder()
                .evaluation(12L)
                .build();
        Student student = session.get(Student.class, 35L);
        profile.setStudent(student);
        session.save(profile);
        session.getTransaction().commit();
    }

    @Test
    public void deleteStudentsFromCourse() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Course course = session.get(Course.class, 12L);
        course.getStudents()
                .removeIf(student -> student.getProfile().getEvaluation() < 6);
        session.getTransaction().commit();
    }

    /**
     * Создание объекта Trainer напрямую.
     */
    @Test
    public void createTrainer() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        int i = 4;
        Course course = session.get(Course.class, 18L);
        System.out.println(course + " klych");
        Trainer trainer = Trainer.builder()
                .name("Name" + i)
                .build();
        trainer.setCourse(course);
        session.save(trainer);

        session.getTransaction().commit();
    }

    @Test
    public void addTrainerAndHisCourses() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Course course = session.get(Course.class, 18L);
        Course courseNext = session.get(Course.class, 12L);
        Trainer trainer = session.get(Trainer.class, 8L);
        TrainerCourse trainerCourse = TrainerCourse.builder()
                .trainer(trainer)
                .course(course)
                .instant(Instant.now())
                .build();
        trainerCourse.setCourse(course);
        trainerCourse.setCourse(courseNext);
        trainerCourse.setTrainer(trainer);
        session.save(trainerCourse);
        session.getTransaction().commit();
    }

    @Test
    public void updateCourse() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Course course = session.get(Course.class, 17L);
        TrainerCourse trainerCourse = session.get(TrainerCourse.class, 1L);
        trainerCourse.setCourse(course);
        trainerCourse.setCourse(course);
        session.update(trainerCourse);

        session.getTransaction().commit();
    }

    @Test
    public void deleteCourse() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        TrainerCourse trainerCourse = session.get(TrainerCourse.class, 4L);
        session.delete(trainerCourse);
        session.getTransaction().commit();
    }

    /**
     * Здесь удаляется курс "Java EnterPrice", без всякой привязки к другим таблицам
     */

    @Test
    public void deleteCourseLessTrainer() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Course course = session.get(Course.class, 12L);
        session.delete(course);
        session.getTransaction().commit();
    }
}
