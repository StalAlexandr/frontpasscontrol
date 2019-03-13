package ru.maximumdance.passcontrol;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticController {

    @org.springframework.stereotype.Controller
    public class Rester {


        @RequestMapping(value = "/")
        public String somePg() {
            System.out.println("hi");
            return "index.html";
        }
    }
}
