package ru.maximumdance.passcontrol.service;

import ru.maximumdance.passcontrol.model.Course;
import ru.maximumdance.passcontrol.model.Lesson;
import ru.maximumdance.passcontrol.model.Pass;
import ru.maximumdance.passcontrol.model.Person;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PersonService {

    void insert(Person person);

    void update(Person person);

    void delete(Person person);

    List<Person> getAll();

    Person findById(Integer id);

    Pass findPassById(Integer id);

    Set<Pass> findPersonPasses(Integer id);

    Integer addPass(Integer id, Pass pass);

    Lesson findLessonById(Integer id);

    Set<Lesson> findPassLessons(Integer id);

    Long addLesson(Integer id, Lesson lesson);

    Person find(Map<String,String> params);

    List<Person>findByNameLike(String firstName);

    Long insertCourse(Course course);

    void update(Pass pass);
}
