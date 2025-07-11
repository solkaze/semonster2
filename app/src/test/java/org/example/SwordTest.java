package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class SwordTest {
    @Test
    public void testSwordConstructorAndGetters() {
        Sword sword = new Sword("sword", 10, "desc", 20);
        assertEquals("sword", sword.getName());
        assertEquals(10, sword.getValue());
        assertEquals("desc", sword.getDescription());
        assertEquals(20, sword.getAttackPower());
    }

    @Test
    public void testSetAttackPower() {
        Sword sword = new Sword("sword", 10, "desc", 20);
        sword.setAttackPower(30);
        assertEquals(30, sword.getAttackPower());
    }
}
