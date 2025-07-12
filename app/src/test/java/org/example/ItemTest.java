package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class ItemTest {
    @Test
    public void testItemConstructorAndGetters() {
        Item item = new Item("item", 10, "desc");
        assertEquals("item", item.getName());
        assertEquals(10, item.getValue());
        assertEquals("desc", item.getDescription());
    }

    @Test
    public void testSetters() {
        Item item = new Item("a", 1, "b");
        item.setName("b");
        item.setValue(2);
        item.setDescription("c");
        assertEquals("b", item.getName());
        assertEquals(2, item.getValue());
        assertEquals("c", item.getDescription());
    }
}
