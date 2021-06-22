package repository.dao;

import repository.mapper.StudentRowMapper;
import repository.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoImp implements StudentDao {
    private static final String SQL_SELECT_ALL_STUDENTS =
            "SELECT * FROM students NATURAL JOIN marks NATURAL JOIN subjects";
    private static final String SQL_SELECT_STUDENT_BY_ID =
            "SELECT * FROM students NATURAL JOIN marks NATURAL JOIN subjects WHERE student_id=?";

    private JdbcTemplate jdbcTemplate;
    private StudentRowMapper studentRowMapper;

    @Autowired
    public StudentDaoImp(JdbcTemplate jdbcTemplate, StudentRowMapper studentRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.studentRowMapper = studentRowMapper;
    }

    @Override
    public List<Student> getAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_STUDENTS, studentRowMapper);
    }

    @Override
    public List<Student> getById(Long id) {
        return jdbcTemplate.query(SQL_SELECT_STUDENT_BY_ID, studentRowMapper, id);
    }
}
