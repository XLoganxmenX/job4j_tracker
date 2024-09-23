package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import java.util.List;


import static org.assertj.core.api.Assertions.*;

class HbmTrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            item.setCreated(LocalDateTime.now());
            tracker.add(item);

            Item result = tracker.findById(item.getId());
            assertThat(result.getName()).isEqualTo(item.getName());
        }
    }

    @Test
    public void whenFindByIdNotExistThenGetNull() {
        try (var tracker = new HbmTracker()) {
            Item result = tracker.findById(0);
            assertThat(result).isNull();
        }
    }

    @Test
    public void whenReplaceItemThenTrackerUpdateItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            item.setCreated(LocalDateTime.now());
            tracker.add(item);

            Item newItem = new Item();
            newItem.setName("test2");
            newItem.setCreated(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

            var replaceResult = tracker.replace(item.getId(), newItem);
            Item actualItem = tracker.findById(item.getId());
            assertThat(replaceResult).isTrue();
            assertThat(actualItem.getName()).isEqualTo(newItem.getName());
            assertThat(actualItem.getCreated()).isEqualTo(newItem.getCreated());
        }
    }

    @Test
    public void whenReplaceNotExistThenGetFalse() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item newItem = new Item();
            newItem.setName("test2");
            newItem.setCreated(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

            var replaceResult = tracker.replace(0, newItem);
            assertThat(replaceResult).isFalse();
        }
    }

    @Test
    public void whenDeleteItemAndFindThenNotFound() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            item.setCreated(LocalDateTime.now());
            tracker.add(item);

            tracker.delete(item.getId());
            Item actualItem = tracker.findById(item.getId());

            assertThat(actualItem).isNull();
        }
    }

    @Test
    public void whenFindAll() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            item.setCreated(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            tracker.add(item);

            Item item2 = new Item();
            item2.setName("test2");
            item2.setCreated(LocalDateTime.now().plusHours(1).truncatedTo(ChronoUnit.SECONDS));
            tracker.add(item2);

            var expectedItems = List.of(item, item2);
            var actualItems = tracker.findAll();

            assertThat(actualItems).usingRecursiveComparison()
                    .ignoringFields("participates")
                    .isEqualTo(expectedItems);
        }
    }

    @Test
    public void whenFindByName() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            item.setCreated(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            tracker.add(item);

            Item item2 = new Item();
            item2.setName("test2");
            item2.setCreated(LocalDateTime.now().plusHours(1).truncatedTo(ChronoUnit.SECONDS));
            tracker.add(item2);

            var expectedItems = List.of(item);
            var actualItems = tracker.findByName("test1");

            assertThat(actualItems).usingRecursiveComparison()
                    .ignoringFields("participates")
                    .isEqualTo(expectedItems);
        }
    }
}