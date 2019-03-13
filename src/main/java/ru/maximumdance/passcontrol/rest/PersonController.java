package ru.maximumdance.passcontrol.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.maximumdance.passcontrol.model.Person;
import ru.maximumdance.passcontrol.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/")
    public List<Person> root() {
        return personService.getAll();
    }

    @PostMapping("/")
    public void insert(Person person){
         personService.insert(person);
    }


    @GetMapping("/{id}")
    public  Person findById(@PathVariable Integer id){
        System.out.println("id " + id);
        return personService.findById(id);
    }

}
