package ru.maximumdance.passcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.maximumdance.passcontrol.model.Course;
import ru.maximumdance.passcontrol.model.Pass;
import ru.maximumdance.passcontrol.model.Person;
import ru.maximumdance.passcontrol.service.PersonService;

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


		Person person2 = new Person();
		person2.setLastName("Сталь");
		person2.setCardNumber(321);
		person2.setFirstName("Николай");
		service.insert(person2);

/*
		Integer id = service.findByFirstName("Сталь").get(0).getId();

		System.out.println("ID " +id);
		Pass pass = new Pass();
	//	Course course = new Course();
//		course.setName("LATINA");
		pass.setCurrentItemCount(100);

		service.addPass(id, pass);
		System.out.println(service.findByFirstName("Сталь").get(0).getPasses().size());
	*/
	}

}
