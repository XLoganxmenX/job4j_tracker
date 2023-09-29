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
        Predicate<Person> nameContains = person -> person.getName().equals(key);
        Predicate<Person> surnameContains = person -> person.getSurname().equals(key);
        Predicate<Person> phoneContains = person -> person.getPhone().equals(key);
        Predicate<Person> addressContains = person -> person.getAddress().equals(key);
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
