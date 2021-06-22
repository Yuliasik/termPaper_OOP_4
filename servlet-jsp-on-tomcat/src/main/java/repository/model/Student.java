package repository.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private List<Subject> subjects = new ArrayList<>();

    public void addNewSubject(Subject subject){
        this.subjects.add(subject);
    }

    public void addNewSubject(List<Subject> subject){
        this.subjects.addAll(subject);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }
}
