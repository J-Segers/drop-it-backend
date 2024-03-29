package com.dropit.backend_drop_it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication
public class BackendDropItApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendDropItApplication.class, args);
    }

}
