package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список пользователей, которые прошли проверку.
     */
    public ArrayList<Person> find(String key) {
        Predicate<Person> nameContains = person -> key.equals(person.getName());
        Predicate<Person> surnameContains = person -> key.equals(person.getSurname());
        Predicate<Person> phoneContains = person -> key.equals(person.getPhone());
        Predicate<Person> addressContains = person -> key.equals(person.getAddress());
        Predicate<Person> combine = nameContains.or(surnameContains).or(phoneContains).or(addressContains);
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}
