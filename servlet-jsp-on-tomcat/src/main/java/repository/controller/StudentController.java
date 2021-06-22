package repository.controller;

import org.springframework.stereotype.Controller;
import repository.dao.StudentDao;
import repository.model.Student;
import repository.model.Subject;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@Controller
@RequestMapping("/students_final_marks")
public class StudentController {

    private StudentDao studentDao;

    @Autowired
    public StudentController(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @GetMapping
    public List<Student> getAll() {
        return groupStudentWithSubject(studentDao.getAll());
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        try {
            return groupStudentWithSubject(studentDao.getById(id)).get(0);
        }catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    private List<Student> groupStudentWithSubject(List<Student> list) {
        List<Student> newList = new ArrayList<>();
        list.forEach(student -> {
            addByOption(newList, student);
        });
        return newList;
    }

    private void addByOption(List<Student> list, Student student) {
        int index;
        if ((index = list.indexOf(student)) != -1)
            list.get(index).addNewSubject(student.getSubjects());
        else
            list.add(student);
    }

    // Метод, якийсь сортує весь список студентів у алфавітному порядку
    // Повертає список об'єктів класу Student
    public List<Student> toSortListAllStudent() {
        List<Student> students = getAll();
        students.sort(Comparator.comparing(Student::getLastName).thenComparing(Student::getFirstName));
        return students;
    }

    // Метод, який знаходить середнє арифметичне всіх оцінок студента
    // Повертає в HashMap 1 пару елеметнів, ключем, якого є прізвище й ім'я студента, а значенням - середнє арифметичне
    public HashMap<String, Double> findAverageOneStudent(@NonNull Student student) {
        HashMap<String, Double> hashMap = new HashMap<>();
        double averageMark = student.getSubjects().stream().collect(Collectors.averagingDouble(Subject::getMark));
        hashMap.put(student.getLastName() + " " + student.getFirstName(), averageMark);
        return hashMap;
    }

    // Метод, який знаходить середнє арифметичне всіх оцінок всіх студентів
    // Повертає HashMap ключем, якого є прізвище й ім'я студента, а значенням - середнє арифметичне
    private HashMap<String, Double> findAverageAllStudent() {
        List<Student> students = getAll();
        HashMap<String, Double> hashMap = new HashMap<>();
        for (Student student : students) {
            hashMap.putAll(findAverageOneStudent(student));
        }
        return hashMap;
    }

    // Метод, який сортує всіх студентів, у порядку спадання середньої арифметичної оцінки
    // Повертає HashMap ключем, якого є прізвище й ім'я студента, а значенням - середнє арифметичне
    public HashMap<String, Double> findAverageAndSortAllStudent() {
        return findAverageAllStudent().entrySet()
                .stream()
                .sorted(Map.Entry
                        .comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e2, LinkedHashMap::new));
    }

    // Метод, який знаходить середнє арифметичне оцінок всіх студентів з одного заданого користувачем предмету
    // Повертає середнє арифметичне типу double
    public Double findAverageBySubject(String subjectName) {
        List<Student> students = getAll();
        double average = 0.0;
        int count = 0, index;
        Subject searched = new Subject(subjectName, 0);
        for (Student student : students) {
            if ((index = student.getSubjects().indexOf(searched)) != -1) {
                average += student.getSubjects().get(index).getMark();
                count++;
            }
        }
        return average / count;
    }

    // Метод, який знаходить всіх предмети, які не були здані студентами (оцінка < 50)
    // Повертає HashMap ключем, якого є прізвище й ім'я студента, а значенням - перелік не зданих ним предметів
    public HashMap<String, String> failedSubject() {
        List<Student> students = getAll();
        HashMap<String, String> hashMap = new HashMap<>();
        String key;

        for (Student student : students) {
            List<Subject> subjects = student.getSubjects();
            key = student.getLastName() + " " + student.getFirstName();
            for (Subject subject : subjects) {
                if (subject.getMark() < 50) {
                    if (hashMap.containsKey(key))
                        hashMap.put(key, hashMap.get(key) + ", " + subject.getSubjectName());
                    else
                        hashMap.put(key, subject.getSubjectName());
                }
            }
        }
        return hashMap;
    }

    // Метод, який впорядковує та групує предмети обраного студента, за кількістю балів, де:
    // >= 90 - 5 (відмінно)
    // 70-89 - 4 (добре)
    // 50-69 - 3 (задовільно)
    //  < 50 - 2 (незадовільно)
    // Повертає HashMap ключем, якого є діапазон оцінки студента, а значенням - перелік предметів, що входять в цей діапазон
    public HashMap<String, String> graduationOfStudentMarks(@NonNull Student student) {
        HashMap<String, String> hashMap = new HashMap<>();
        String key = " < 50";
        hashMap.put(">= 90", "");
        hashMap.put("70-89", "");
        hashMap.put("50-69", "");
        hashMap.put(" < 50", "");
        for (Subject subject : student.getSubjects()) {
            int mark = subject.getMark();
            if (mark > 89)
                key = ">= 90";
            else if (mark > 69)
                key = "70-89";
            else if (mark > 49)
                key = "50-69";

            addByHashMap(hashMap, key, subject);
        }
        return hashMap;
    }

    private void addByHashMap(HashMap<String, String> hashMap, String key, Subject subject) {
        hashMap.put(key, hashMap.get(key) + " " + subject.getSubjectName() + ",");
    }
}
