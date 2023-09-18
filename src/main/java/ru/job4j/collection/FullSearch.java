package ru.job4j.collection;

import java.util.HashSet;
import java.util.List;

public class FullSearch {
    public HashSet<String> extractNumber(List<Task> tasks) {
        HashSet<String> rsl = new HashSet<>();
        for (Task task : tasks) {
            rsl.add(task.getNumber());
        }
        return rsl;
    }
}
