package com.pial.reactivetask.service;

import com.pial.reactivetask.dao.StudentDao;
import com.pial.reactivetask.model.Student;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public Flux<Student> studentFlux (){
        return studentDao.getStudentFlux();
    }

    public Mono<Student> addStudentMono(String name, String email){
        return studentDao.addStudentMono(name, email);
    }
}
