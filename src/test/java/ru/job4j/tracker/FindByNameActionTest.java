package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindByNameActionTest {

    @Test
    public void whenFindNotExistItem() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        FindByNameAction findByNameAction = new FindByNameAction(output);
        String itemsName = "Item";

        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(itemsName);

        findByNameAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find items by name ===" + ln
                        + "Заявки с именем: " + itemsName + " не найдены." + ln
        );
    }

    @Test
    public void whenFindWithOneItem() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        Item savedItem = new Item("Item");
        tracker.add(savedItem);
        FindByNameAction findByNameAction = new FindByNameAction(output);

        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("Item");

        findByNameAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find items by name ===" + ln
                        + savedItem + ln
        );
    }

    @Test
    public void whenFindWithManyItem() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        Item savedItem = new Item("Item");
        tracker.add(savedItem);
        tracker.add(new Item("Second Item"));
        tracker.add(new Item("Third Item"));
        FindByNameAction findByNameAction = new FindByNameAction(output);

        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("Item");

        findByNameAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find items by name ===" + ln
                        + savedItem + ln
        );
    }

    @Test
    public void whenFindWithSeveralValidItems() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        Item savedItem = new Item("Item");
        Item secondSavedItem = new Item("Item");
        List.of(savedItem, secondSavedItem).forEach(tracker::add);
        tracker.add(new Item("Second Item"));
        tracker.add(new Item("Third Item"));
        FindByNameAction findByNameAction = new FindByNameAction(output);

        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("Item");

        findByNameAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find items by name ===" + ln
                        + savedItem + ln
                        + secondSavedItem + ln
        );
    }
}