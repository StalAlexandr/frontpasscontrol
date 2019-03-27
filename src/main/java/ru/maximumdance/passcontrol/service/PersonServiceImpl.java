package ru.maximumdance.passcontrol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maximumdance.passcontrol.dao.PersonDAOImpl;
import ru.maximumdance.passcontrol.model.Course;
import ru.maximumdance.passcontrol.model.Lesson;
import ru.maximumdance.passcontrol.model.Pass;
import ru.maximumdance.passcontrol.model.Person;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public void update(Person person) {
        personDAO.update(person);
    }

    @Override
    public void delete(Person person){
        personDAO.delete(person);
    }

    @Override
    public List<Person> getAll() {
        return personDAO.getAll();
    }

    @Override
    public Person find(Map<String, String> params) {
        return personDAO.find(params);
    }

    @Override
    public Person findById(Integer id) {
        return personDAO.findById(id);
    }

    @Override
    public List<Person>findByNameLike(String firstName) {
        return personDAO.findByNameLike(firstName);
    }

    @Override
    public Pass findPassById(Integer id){
        return personDAO.findPassById(id);
    }

    @Override
    public Set<Pass> findPersonPasses(Integer id){
        return personDAO.findPersonPasses(id);
    }

    @Override
    public Integer addPass(Integer id, Pass pass) {
        return personDAO.addPass(id,pass);
    }

    @Override
    public void update(Pass pass) {
         personDAO.update(pass);
    }


    @Override
    public Lesson findLessonById(Integer id){
        return personDAO.findLessonById(id);
    }

    @Override
    public Set<Lesson> findPassLessons(Integer id){
        return personDAO.findPassLessons(id);
    }

    @Override
    public Long  addLesson(Integer id, Lesson lesson) {
        return personDAO.addLesson(id,lesson);
    }

    @Override
    public Long insertCourse(Course course) {
        return personDAO.insertCourse(course);
    }



}
