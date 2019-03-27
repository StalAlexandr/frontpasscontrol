package ru.maximumdance.passcontrol.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.maximumdance.passcontrol.model.Pass;
import ru.maximumdance.passcontrol.model.Person;
import ru.maximumdance.passcontrol.service.PersonService;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/")
    public List<Person> root() {
        return personService.getAll();
    }

    @PutMapping("/")
    public void update(@RequestBody Person person){
         personService.update(person);
    }


    @PostMapping("/")
    public void insert(@RequestBody Person person){
        personService.insert(person);
    }

    @GetMapping("/{id}")
    public  Person findById(@PathVariable Integer id){
        return personService.findById(id);
    }

    @GetMapping("/{id}/passes")
    public Set<Pass> findPersonPasses(@PathVariable Integer id){
        return personService.findPersonPasses(id);
    }

    @PutMapping("/{id}/pass")
    public void addPass(@PathVariable Integer id, @RequestBody Pass pass){
        personService.addPass(id,pass);
    }


    @GetMapping("/select")
    public  Person find(@RequestParam Map<String,String> params){

        return personService.find(params);
    }

    @GetMapping("/selectByName/{name}")
    public  List<Person> findByName(@PathVariable String name){
        return personService.findByNameLike(name);
    }

}
