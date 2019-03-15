package ru.maximumdance.passcontrol.service;

import ru.maximumdance.passcontrol.model.Pass;
import ru.maximumdance.passcontrol.model.Person;

import java.util.List;
import java.util.Map;

public interface PersonService {

    void insert(Person person);

    List<Person> getAll();

    Person findById(Integer id);

    void addPass(Integer id, Pass pass);

    Person find(Map<String,String> params);

    List<Person>findByNameLike(String firstName);
}
