package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class CharacterTest {
    @Test
    public void testDefaultConstructor() {
        Character c = new Character();
        assertEquals(100, c.getHp());
        assertEquals("Monster", c.getName());
    }

    @Test
    public void testParameterizedConstructor() {
        Character c = new Character(50, "Test", 20);
        assertEquals(50, c.getHp());
        assertEquals("Test", c.getName());
    }

    @Test
    public void testAttackRange() {
        Character c = new Character(100, "A", 10);
        int atk = c.attack();
        assertTrue(atk >= 10 && atk <= 11);
    }

    @Test
    public void testDamage() {
        Character c = new Character(10, "A", 1);
        boolean defeated = c.damage(5);
        assertFalse(defeated);
        assertEquals(5, c.getHp());
        defeated = c.damage(5);
        assertTrue(defeated);
        assertEquals(0, c.getHp());
    }
}
