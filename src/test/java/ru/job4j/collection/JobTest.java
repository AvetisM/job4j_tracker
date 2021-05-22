package ru.job4j.collection;

import org.junit.Test;

import java.util.Comparator;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

public class JobTest {

    @Test
    public void whenComparatorIncByName() {

        Comparator<Job> cmpNameInc = new JobIncByName();
        int rsl = cmpNameInc.compare(new Job("Impl task", 1),
                                     new Job("Fix bug", 0)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorDescByName() {

        Comparator<Job> cmpNameDesc = new JobDescByName();
        int rsl = cmpNameDesc.compare(new Job("Impl task", 1),
                new Job("Fix bug", 0)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenComparatorIncByPriority() {

        Comparator<Job> cmpPriorityInc = new JobIncByPriority();
        int rsl = cmpPriorityInc.compare(new Job("Impl task", 1),
                new Job("Fix bug", 0)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorDescByPriority() {

        Comparator<Job> cmpPriorityDesc = new JobDescByPriority();
        int rsl = cmpPriorityDesc.compare(new Job("Impl task", 1),
                new Job("Fix bug", 0)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenComparatorDescNameAndByPriority() {

        Comparator<Job> cmpDesc = new JobDescByName().thenComparing(new JobDescByPriority());

        int rsl = cmpDesc.compare(new Job("Impl task", 2),
                new Job("Impl task", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenComparatorIncByPriorityAndName() {

        Comparator<Job> cmpInc = new JobIncByPriority().thenComparing(new JobIncByName());

        int rsl = cmpInc.compare(new Job("Fix bug", 1),
                new Job("Impl task", 1)
        );
        assertThat(rsl, lessThan(0));
    }
}