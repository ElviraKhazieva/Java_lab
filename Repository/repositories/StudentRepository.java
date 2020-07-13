package repositories;

import models.Student;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student> {
    List<Student> findAllByAge(int age);
}
