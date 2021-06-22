package repository.mapper;

import repository.model.Student;
import repository.model.Subject;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

@Component
public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt("student_id"));
        student.setFirstName(resultSet.getString("student_first_name"));
        student.setLastName(resultSet.getString("student_last_name"));
        student.getSubjects()
                .add(new Subject(resultSet.getString("subject_name"),
                        resultSet.getInt("mark")));

        return student;

    }

}
