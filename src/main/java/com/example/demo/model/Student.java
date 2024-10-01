package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "Student")
@Data
public class Student {
        @Id
        private String id;
        private String name;
        private List<String> subjects;
        private String attendance;
        private String city;

}
