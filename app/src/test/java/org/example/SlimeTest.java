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

    @Test
    public void testPerformSpecialAction_NormalAttack() {
        Slime s = new Slime();
        Player p = new Player();
        CommandBattle cb = new CommandBattle();
        int before = p.getHp();
        s.performSpecialAction(p, cb, 7);
        assertEquals(before - 7, p.getHp()); // 通常攻撃
    }
}
