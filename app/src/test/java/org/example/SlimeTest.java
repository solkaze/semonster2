package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class SlimeTest {
    @Test
    public void testSlimeConstructor() {
        Slime s = new Slime();
        assertEquals(10, s.getHp());
        assertEquals("Slime", s.getName());
    }
}
