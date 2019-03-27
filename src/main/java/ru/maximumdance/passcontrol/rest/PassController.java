package ru.maximumdance.passcontrol.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.maximumdance.passcontrol.model.Lesson;
import ru.maximumdance.passcontrol.model.Pass;
import ru.maximumdance.passcontrol.service.PersonService;

import java.util.Set;

@RestController
@RequestMapping("/passes")
public class PassController {

    @Autowired
    PersonService personService;

    @PutMapping("/")
    public void update(@RequestBody Pass pass){
        personService.update(pass);
    }


    @GetMapping("/{id}/lessons")
    public Set<Lesson> findPassLessons(@PathVariable Integer id){
        return personService.findPassLessons(id);
    }


}
