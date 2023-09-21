package ru.job4j.tracker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCollection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ItemAscByNameTest {
    @Test
    public void whenItemAscByName() {
        List<Item> items = new ArrayList<>();
        Item aladdin = new Item("Aladdin");
        Item balu = new Item("Balu");
        Item zorro = new Item("Zorro");
        items.add(zorro);
        items.add(balu);
        items.add(aladdin);
        List<Item> expect = List.of(aladdin, balu, zorro);
        items.sort(new ItemAscByName());
        assertThat(items).containsExactlyElementsOf(expect);
    }

    @Test
    public void whenItemDescByName() {
        List<Item> items = new ArrayList<>();
        Item aladdin = new Item("Aladdin");
        Item balu = new Item("Balu");
        Item zorro = new Item("Zorro");
        items.add(zorro);
        items.add(balu);
        items.add(aladdin);
        List<Item> expect = List.of(zorro, balu, aladdin);
        items.sort(new ItemDescByName());
        assertThat(items).containsExactlyElementsOf(expect);
    }
}