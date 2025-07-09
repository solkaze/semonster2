package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void testDefaultPlayer() {
        Player p = new Player();
        assertEquals(30, p.getHp());
        assertEquals(30, p.getMaxHp());
        assertEquals("勇者", p.getName());
        assertEquals(1, p.getLevel());
        assertEquals(0, p.getExp());
    }

    @Test
    public void testParameterizedPlayer() {
        Player p = new Player(20, 40, "テスト", 10);
        assertEquals(20, p.getHp());
        assertEquals(40, p.getMaxHp());
        assertEquals("テスト", p.getName());
    }

    @Test
    public void testMoney() {
        Player p = new Player();
        assertEquals(0, p.getMoney());
        p.addMoney(100);
        assertEquals(100, p.getMoney());
        assertTrue(p.spendMoney(50));
        assertEquals(50, p.getMoney());
        assertFalse(p.spendMoney(100));
        assertEquals(50, p.getMoney());
    }

    @Test
    public void testLevelUp() {
        Player p = new Player();
        int before = p.getMaxHp();
        p.gainExp(100);
        assertTrue(p.getLevel() > 1);
        assertTrue(p.getMaxHp() > before);
    }
}
