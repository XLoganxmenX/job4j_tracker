package ru.job4j.map;

import java.util.Map;
import java.util.Set;

public class College {

    private final Map<Student, Set<Subject>> students;

    public College(Map<Student, Set<Subject>> students) {
        this.students = students;
    }

    public Student findByAccount(String account) {
        return students.keySet()
                .stream()
                .filter(s -> s.account().equals(account))
                .findFirst()
                .orElse(null);
    }

    public Subject findBySubjectName(String accountNumber, String name) {
        var account = findByAccount(accountNumber);
        if (account == null) {
            return null;
        }
        return students.get(account)
                .stream()
                .filter(s -> s.name().equals(name))
                .findFirst()
                .orElse(null);
    }

    public static void main(String[] args) {
        Map<Student, Set<Subject>> students = Map.of(new Student("Student", "000001", "201-18-15"),
                Set.of(
                        new Subject("Math", 70),
                        new Subject("English", 85)
                )
        );
        College college = new College(students);
        Student student = college.findByAccount("000001");
        System.out.println("Найденный студент: " + student);
        Subject english = college.findBySubjectName("000001", "English");
        System.out.println("Оценка по найденному предмету: " + english.score());
    }

}
