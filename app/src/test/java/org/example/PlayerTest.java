package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void testDefaultPlayer() {
        Player p = new Player();
        assertEquals(300, p.getHp());
        assertEquals("勇者", p.getName());
    }

    @Test
    public void testParameterizedPlayer() {
        Player p = new Player(50, "テスト", 20);
        assertEquals(50, p.getHp());
        assertEquals("テスト", p.getName());
    }
}
