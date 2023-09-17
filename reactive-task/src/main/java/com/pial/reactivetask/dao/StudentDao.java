package com.pial.reactivetask.dao;


import com.pial.reactivetask.model.Student;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Repository
public class StudentDao {

    private Flux<Student> studentFlux = Flux.empty();

    public Flux<Student> getStudentFlux() {
        return studentFlux;
    }

    public Mono<Student> addStudentMono(String name, String email) {
        UUID id = UUID.randomUUID();
        Student student = new Student(id, name, email);
        studentFlux = studentFlux.concatWithValues(student);
        return Mono.just(student);
    }

    public Mono<Student> updateStudentMono(UUID id, String name, String email) {
        return studentFlux
                .filter(student -> student.getId().equals(id))
                .next()
                .doOnNext(student -> {
                    student.setName(name);
                    student.setEmail(email);
                });
    }

    public Mono<Void> deleteStudent(UUID id) {
        studentFlux = studentFlux.filter(student -> !student.getId().equals(id));
        return Mono.empty();
    }




}
