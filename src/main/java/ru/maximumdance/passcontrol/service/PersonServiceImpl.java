package ru.maximumdance.passcontrol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maximumdance.passcontrol.dao.PersonDAOImpl;
import ru.maximumdance.passcontrol.model.Pass;
import ru.maximumdance.passcontrol.model.Person;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDAOImpl personDAO;


    @Override
    public void insert(Person person) {
         personDAO.insert(person);
    }

    @Override
    public List<Person> getAll() {
        return personDAO.getAll();
    }


    @Override
    public Person findById(Integer id) {
        return personDAO.findById(id);
    }

    @Override
    public void addPass(Integer id, Pass pass) {
        personDAO.addPass(id,pass);
    }

    @Override
    public Person find(Map<String, String> params) {
        return personDAO.find(params);
    }

    @Override
    public List<Person>findByNameLike(String firstName) {
        return personDAO.findByNameLike(firstName);
    }


}
