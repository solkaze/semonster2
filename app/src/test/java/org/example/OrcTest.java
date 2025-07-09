package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class OrcTest {
    @Test
    public void testOrcConstructorAndReward() {
        Orc o = new Orc();
        assertEquals(40, o.getHp());
        assertEquals("オーク", o.getName());
        assertEquals(18, o.getExpReward());
        assertEquals(30, o.getGoldReward());
    }

    @Test
    public void testPerformSpecialAction_TwoAttackOrNormal() {
        Orc o = new Orc();
        Player p = new Player();
        CommandBattle cb = new CommandBattle();
        int before = p.getHp();
        boolean twoAttack = false;
        boolean normal = false;
        for (int i = 0; i < 30; i++) {
            p.setHp(before);
            o.performSpecialAction(p, cb, 5);
            if (p.getHp() < before - 5)
                twoAttack = true; // 2回攻撃
            if (p.getHp() == before - 5)
                normal = true; // 通常攻撃
        }
        assertTrue(twoAttack);
        assertTrue(normal);
    }
}
