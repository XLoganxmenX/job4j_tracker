package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("loganxmen97@gmail.com", "Avdoshin D.U.");
        map.put("balabol@gmail.com", "Aziziov Abdul");
        for (String key : map.keySet()) {
            System.out.println(key + " = " + map.get(key));
        }
    }
}
