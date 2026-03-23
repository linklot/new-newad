package au.com.newad.web.controller;

import java.util.List;

import au.com.newad.web.model.Person;
import au.com.newad.web.utils.JsonDataReader;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class GreetingController {

    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);

    private final JsonDataReader jsonDataReader;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name = "name", required = false, defaultValue = "Test") final String name,
            final Model model) {
        try {
            List<Person> people = jsonDataReader.readJsonList("db/people.json", Person.class);
            Person person = people.getFirst();

            model.addAttribute("name", person.name());

            return "greeting";
        } catch (Exception e) {
            log.error("Error reading JSON data", e);
            model.addAttribute("name", name);
            return "greeting";
        }
    }

}
