package org.example.projectwebservice1;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title="Animal Protection API"))
public class ProjectWebService1Application {

    public static void main(String[] args) {
        SpringApplication.run(ProjectWebService1Application.class, args);
    }

}
