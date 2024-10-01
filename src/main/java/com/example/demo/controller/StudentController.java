package com.example.demo.controller;


import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@RequestMapping("api")
@RestController
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("api/getall")
    public Flux<Student> getall(){
       return service.getallstudents();
    }

    @PostMapping("/save")
    public Mono<String> savestudent(@RequestBody Student studentobj)
    {
           return service.saveStudent(studentobj);
    }


    @PostMapping("/bulkinsert")
    public Mono<String> bulkdsave(@RequestBody List<Student> requestmap){

            return service.bulkinsert(requestmap);
    }

    //get by id usiong @Requestbody
    @GetMapping("/getbyid")
    public Mono<Student> getbyid(@RequestBody Student requestmap)
    {
           return service.getbyid(requestmap.getId());
    }

    @GetMapping("/getbyid/{id}")
        public Mono<Student> getbyid1(@PathVariable String id)
        {
            return service.getbyid(id);
        }

    @GetMapping("/findbyname")
    public Flux<Student> findbyname(@RequestBody Student requestmap){

       return service.findbynames(requestmap.getName());
    }
    }



//    public Mono<ResponseEntity<Student>> saveStudent(@RequestBody Student student) {
//        return service.saveStudent(student)
//                .map(ResponseEntity::ok)
//                .defaultIfEmpty(ResponseEntity.badRequest().build());
//    }


