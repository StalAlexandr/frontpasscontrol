package ru.maximumdance.passcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.maximumdance.passcontrol.model.Course;
import ru.maximumdance.passcontrol.model.Lesson;
import ru.maximumdance.passcontrol.model.Pass;
import ru.maximumdance.passcontrol.model.Person;
import ru.maximumdance.passcontrol.service.PersonService;

import java.util.Date;

@SpringBootApplication
public class PasscontrolApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(PasscontrolApplication.class, args);
		PersonService service =context.getBean(PersonService.class);


		Person person = new Person();
		person.setLastName("Сталь");
		person.setCardNumber(123);
		person.setFirstName("Александр");
		service.insert(person);


        Pass pass = new Pass();
        Course course = new Course();
        course.setName("Латина");

        Long courceId = service.insertCourse(course);
        course.setId(courceId);

        pass.setItemCount(8);
        pass.setCourse(course);

        person.addPass(pass);


        Integer id = service.findByNameLike("Сталь").get(0).getId();
        Integer passId = service.addPass(id, pass);



		Person person2 = new Person();

        person2.setId(id);

		person2.setLastName("Сталь");
		person2.setCardNumber(321);
		person2.setFirstName("Николай");

		service.update(person2);


		Lesson lesson = new Lesson();
		lesson.setCourse(course);
		lesson.setDate( new java.sql.Date(System.currentTimeMillis()));

		service.addLesson(passId,lesson);

	}

}
