package ru.job4j.hmap;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        double rsl = 0D;
        int count = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                rsl += subject.score();
                count++;
            }
        }
        rsl /= count;
        return rsl;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> allLabel = new ArrayList<>();
        for (Pupil pupil : pupils) {
            int count = 0;
            double average = 0D;
            for (Subject subject : pupil.subjects()) {
                average += subject.score();
                count++;
            }
            Label pupilLabel = new Label(pupil.name(), average / count);
            allLabel.add(pupilLabel);

        }
        return List.copyOf(allLabel);
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        Map<String, Integer> map = new LinkedHashMap<>();
        return List.of();
    }

    public static Label bestStudent(List<Pupil> pupils) {
        return null;
    }

    public static Label bestSubject(List<Pupil> pupils) {
        return null;
    }
}
