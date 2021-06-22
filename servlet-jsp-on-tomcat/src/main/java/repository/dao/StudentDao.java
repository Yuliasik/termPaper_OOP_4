package repository.dao;

import repository.model.Student;

import java.util.List;

public interface StudentDao {
    List<Student> getAll();
    List<Student> getById(Long id);
}
