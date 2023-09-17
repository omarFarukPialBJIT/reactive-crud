package com.pial.reactivetask.controller;

import com.pial.reactivetask.model.Student;
import com.pial.reactivetask.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/rstudents", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Student> studentFlux(){
        return studentService.studentFlux();
    }

    @PostMapping("/rstudent")
    Mono<Student> addStudentMono(@RequestBody Student student){
        return studentService.addStudentMono(student.getName(), student.getEmail());
    }


    @PutMapping("/rstudent")
    Mono<Student> updateStudentMono(@RequestBody Student student) {
        return studentService.updateStudentMono(student.getId(), student.getName(), student.getEmail());
    }


    @DeleteMapping("/rstudent")
    Mono<Void> deleteStudent(@RequestBody Student student) {
        return studentService.deleteStudent(student.getId());
    }

}
