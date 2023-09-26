package ru.job4j.collection;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.assertj.core.api.Assertions.assertThat;

public class JobTest {
    @Test
    public void whenComparatorJobAscByName() {
        Comparator<Job> cmpJobAscByName = new JobAscByName();
        int rsl = cmpJobAscByName.compare(
                new Job("Abobus", 0),
                new Job("Zoolander", 1)
        );
        assertThat(rsl).isLessThan(0);
    }

    @Test
    public void whenComparatorJobDescByName() {
        Comparator<Job> cmpJobDescByName = new JobDescByName();
        int rsl = cmpJobDescByName.compare(
                new Job("Abobus", 0),
                new Job("Zoolander", 1)
        );
        assertThat(rsl).isGreaterThan(0);
    }

    @Test
    public void whenComparatorJobAscByPriority() {
        Comparator<Job> cmpJobAscByPriority = new JobAscByPriority();
        int rsl = cmpJobAscByPriority.compare(
                new Job("Abobus", 5),
                new Job("Zoolander", 1)
        );
        assertThat(rsl).isGreaterThan(0);
    }

    @Test
    public void whenComparatorJobDescByPriority() {
        Comparator<Job> cmpJobDescByPriority = new JobDescByPriority();
        int rsl = cmpJobDescByPriority.compare(
                new Job("Abobus", 5),
                new Job("Zoolander", 1)
        );
        assertThat(rsl).isLessThan(0);
    }

    @Test
    public void whenComparatorByNameAndPriority() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl).isLessThan(0);
    }

    @Test
    public void whenComparatorByNameAscAndPriorityAsc() {
        Comparator<Job> cmpNamePriority = new JobAscByName().thenComparing(new JobAscByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Fix bug", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl).isLessThan(0);
    }
}