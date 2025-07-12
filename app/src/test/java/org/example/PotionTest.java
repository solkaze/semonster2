package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class PotionTest {
    @Test
    public void testPotionConstructorAndGetters() {
        Potion potion = new Potion("potion", 10, "desc", 20);
        assertEquals("potion", potion.getName());
        assertEquals(10, potion.getValue());
        assertEquals("desc", potion.getDescription());
        assertEquals(20, potion.getHealAmount());
    }

    @Test
    public void testSetHealAmount() {
        Potion potion = new Potion("potion", 10, "desc", 20);
        potion.setHealAmount(30);
        assertEquals(30, potion.getHealAmount());
    }
}
