package ru.maximumdance.passcontrol.service;

import ru.maximumdance.passcontrol.model.Pass;
import ru.maximumdance.passcontrol.model.Person;

import java.util.List;

public interface PersonService {

    void insert(Person person);

    List<Person> getAll();

    List<Person> findByFirstName(String firstName);

    Person findByCardNumber(String cardNumber);

    Person findById(Integer id);

    void addPass(Integer id, Pass pass);

}
