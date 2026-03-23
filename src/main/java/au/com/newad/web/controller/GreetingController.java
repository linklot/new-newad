package au.com.newad.web.controller;

import au.com.newad.web.model.Person;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tools.jackson.databind.json.JsonMapper;

@RequiredArgsConstructor
@Controller
public class GreetingController {

    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);

    private final JsonMapper jsonMapper;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name = "name", required = false, defaultValue = "Test") final String name,
            final Model model) {
        String json = """
                {
                    "name": "John Doe",
                    "age": 30
                }
                """;
        Person person = jsonMapper.readValue(json, Person.class);

        model.addAttribute("name", person.name());

        log.info(jsonMapper.writeValueAsString(person));
        return "greeting";
    }

}
