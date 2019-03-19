package ru.maximumdance.passcontrol.service;

import ru.maximumdance.passcontrol.model.Course;
import ru.maximumdance.passcontrol.model.Lesson;
import ru.maximumdance.passcontrol.model.Pass;
import ru.maximumdance.passcontrol.model.Person;

import java.util.List;
import java.util.Map;

public interface PersonService {

    void insert(Person person);

    void update(Person person);

    List<Person> getAll();

    Person findById(Integer id);

    Integer addPass(Integer id, Pass pass);

    Long addLesson(Integer id, Lesson lesson);

    Person find(Map<String,String> params);

    List<Person>findByNameLike(String firstName);

    Long insertCource(Course course);
}
