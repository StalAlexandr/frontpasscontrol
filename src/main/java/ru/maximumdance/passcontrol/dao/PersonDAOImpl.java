package ru.maximumdance.passcontrol.dao;

import org.springframework.stereotype.Repository;
import ru.maximumdance.passcontrol.model.Pass;
import ru.maximumdance.passcontrol.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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

    public List<Person> findByFirstName(String name){
        TypedQuery<Person> q = entityManager.createQuery("SELECT person FROM Person person WHERE person.firstName  = :name ORDER BY id DESC",Person.class);
        q.setParameter("name", name);
        return q.getResultList();
    }

    public Person findByCardNumber(String cardNumber){
        TypedQuery<Person> q = entityManager.createQuery("SELECT person FROM Person person WHERE person.cardNumber  = :cardNumber ORDER BY id DESC",Person.class);
        q.setParameter("cardNumber", cardNumber);
        return q.getSingleResult();
    };

    public Person findById(Integer id){
        return entityManager.find(Person.class, id);
    };

    public void addPass(Integer id, Pass pass){
        Person person = findById(id);
        person.addPass(pass);
        entityManager.persist(pass);


    }

}
