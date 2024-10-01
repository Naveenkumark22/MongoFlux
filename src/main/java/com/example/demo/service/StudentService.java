package com.example.demo.service;


import com.example.demo.model.Student;
import com.example.demo.repo.StudentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class StudentService {


    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepo repo;

    public Flux<Student> getallstudents() {
       return  repo.findAll();

    }

    public Mono<String> saveStudent(Student studentobj) {
      return   repo.save(studentobj)
                .then(Mono.just("saved successfully   id: " +studentobj.getId()))
              .onErrorReturn("error in saving");


    }

    public Mono<String> bulkinsert(List<Student> requestmap) {

        return Flux.fromIterable(requestmap)  // convert list into flux
                .flatMap(this::saveStudent)    //call the savestudent method and save one by one
                // log the each student
                .doOnNext(student ->{            //student contains whaterver returned from above saveStudent method
                    logger.info(" saved" + student);
                })
                //if error occurs in saving particular student it will be captured
                .doOnError(student->{
                    logger.error("save error" + student.getMessage());
                })
                .collectList()  //  collect all the saved as list whatever returining in the saveStudent method
                .doOnNext(collectlist->logger.info("collectled list" +String.valueOf(collectlist)))  //adding logg and print in console
                .then(Mono.just("all records saved successfully"))
                .onErrorReturn("error in saving ");

    }

    public Mono<Student> getbyid(String id) {
        return  repo.findById(id);
    }

    public Flux<Student> findbynames(String name) {
       return   repo.findByName(name).
                 filter(student ->student.getCity().equalsIgnoreCase("New York"));

    }

//    public Mono<Student> saveStudent(Student student) {
//        return repo.save(student);
//    }
}
