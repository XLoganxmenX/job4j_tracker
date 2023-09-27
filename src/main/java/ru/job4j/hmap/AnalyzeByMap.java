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
        Map<String, Integer> subjectMap = new LinkedHashMap<>();
        int pupilCount = 0;
        List<Label> listOfLabels = new ArrayList<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                // subjectMap.put(subject.name(), subjectMap.getOrDefault(subject.name(), 0) + subject.score());
                subjectMap.merge(subject.name(), subject.score(), Integer::sum);
            }
            pupilCount++;
        }
        for (String subject : subjectMap.keySet()) {
            Label label = new Label(subject, (double) subjectMap.get(subject) / pupilCount);
            listOfLabels.add(label);
        }
        return List.copyOf(listOfLabels);
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> allLabel = new ArrayList<>();
        for (Pupil pupil : pupils) {
            int scoreSum = 0;
            for (Subject subject : pupil.subjects()) {
                scoreSum += subject.score();
            }
            Label pupilLabel = new Label(pupil.name(), scoreSum);
            allLabel.add(pupilLabel);
        }
        allLabel.sort(Comparator.naturalOrder());
        return allLabel.get(allLabel.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        Map<String, Integer> subjectMap = new LinkedHashMap<>();
        List<Label> listOfLabels = new ArrayList<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                subjectMap.merge(subject.name(), subject.score(), Integer::sum);
            }
        }
        for (String subject : subjectMap.keySet()) {
            Label label = new Label(subject, (double) subjectMap.get(subject));
            listOfLabels.add(label);
        }
        listOfLabels.sort(Comparator.naturalOrder());
        return listOfLabels.get(listOfLabels.size() - 1);
    }
}
