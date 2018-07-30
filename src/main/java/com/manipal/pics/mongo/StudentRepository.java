package com.manipal.pics.mongo;

import com.manipal.pics.mongo.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {
    Student findByName(String name);

    Optional<Student> findById(String id);


}