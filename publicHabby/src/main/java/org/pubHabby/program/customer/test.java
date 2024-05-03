package org.pubHabby.program.customer;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class test {
        @GetMapping("/api/hello")
        public String test() {

            return "Hello, world!";
        }
    }

