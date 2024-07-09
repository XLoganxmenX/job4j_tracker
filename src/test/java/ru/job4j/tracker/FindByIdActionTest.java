package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindByIdActionTest {

    @Test
    public void whenFindById() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        Item savedItem = new Item("Item");
        tracker.add(savedItem);
        FindByIdAction findByIdAction = new FindByIdAction(output);
        int requestedId = 1;

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(requestedId);

        findByIdAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find item by id ===" + ln
                        + savedItem + ln
        );
    }

    @Test
    public void whenFindNotExistId() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Item"));
        FindByIdAction findByIdAction = new FindByIdAction(output);
        int requestedId = 0;

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(requestedId);

        findByIdAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find item by id ===" + ln
                        + "Заявка с введенным id: " + requestedId + " не найдена." + ln
        );
    }

}