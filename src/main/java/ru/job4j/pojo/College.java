package ru.job4j.pojo;

public class College {
    public static void main(String[] args) {
        Student student1 = new Student();
        student1.setFullName("Nikiforov Archibald");
        student1.setGroup("KPD-9-23");
        student1.setDateOfEntrance("06.07.2023");

        System.out.println(student1.getFullName());
        System.out.println(student1.getGroup());
        System.out.println(student1.getDateOfEntrance());
    }
}
