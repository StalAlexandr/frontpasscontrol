package ru.maximumdance.passcontrol.dao;

import org.springframework.stereotype.Repository;
import ru.maximumdance.passcontrol.model.Course;
import ru.maximumdance.passcontrol.model.Lesson;
import ru.maximumdance.passcontrol.model.Pass;
import ru.maximumdance.passcontrol.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

@Repository
public class PersonDAOImpl {

    protected EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void insert(Person person){
        entityManager.persist(person);
    }

    public void update(Person person){
        entityManager.merge(person);
    }

    public void delete(Person person){
        entityManager.remove(person);
    }


    public List<Person> getAll(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> cq = cb.createQuery(Person.class);
        Root<Person> rootEntry = cq.from(Person.class);
        CriteriaQuery<Person> all = cq.select(rootEntry);
        TypedQuery<Person> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    public Person findById(Integer id){
        return entityManager.find(Person.class, id);
    };

    public Pass findPassById(Integer id){
        return entityManager.find(Pass.class, id);
    };

    public Course findCourceById(Integer id){
        return entityManager.find(Course.class, id);
    };

    public Long insertCource(Course course){
        entityManager.persist(course);
        entityManager.flush();
        return course.getId();
    };



    public Integer addPass(Integer id, Pass pass){
        Person person = findById(id);
        person.addPass(pass);
        entityManager.persist(pass);
        entityManager.flush();
        return pass.getId();
    }

    public Person find(Map<String,String> params) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
        Root<Person> from = criteria.from(Person.class);
        criteria.select(from);
        params.forEach((key, value) -> criteria.where(builder.equal(from.get(key), value)));

        TypedQuery<Person> typed = entityManager.createQuery(criteria);
        try {
            return typed.getSingleResult();
        } catch (final NoResultException nre) {
            return null;
        }
    }

    public List<Person> findByNameLike(String name) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
        Root<Person> from = criteria.from(Person.class);
        criteria.select(from);

        criteria.where(builder.like(from.get("lastName"), builder.parameter(String.class, "likeCondition")));

        TypedQuery<Person> typed = entityManager.createQuery(criteria);
        typed.setParameter("likeCondition", name+"%");
        return typed.getResultList();
    }

    public Long addLesson(Integer id, Lesson lesson) {

        Pass pass = findPassById(id);
        pass.addLesson(lesson);
        entityManager.persist(pass);
        entityManager.flush();
        return lesson.getId();
    }
}
