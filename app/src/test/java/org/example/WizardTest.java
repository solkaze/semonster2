package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class WizardTest {
    @Test
    public void testWizardConstructorAndReward() {
        Wizard w = new Wizard();
        assertEquals(25, w.getHp());
        assertEquals("ウィザード", w.getName());
        assertEquals(25, w.getExpReward());
        assertEquals(50, w.getGoldReward());
    }

    @Test
    public void testSpecialActionMagic() {
        Wizard w = new Wizard();
        Player p = new Player();
        int before = p.getHp();
        w.specialAction(p, new ArrayList<>());
        assertTrue(p.getHp() <= before);
    }

    @Test
    public void testPerformSpecialAction_MagicOrConfuseOrNormal() {
        Wizard w = new Wizard();
        Player p = new Player();
        CommandBattle cb = new CommandBattle();
        int before = p.getHp();
        // 何度か繰り返して魔法攻撃・混乱・通常攻撃のいずれかが発生することを確認
        boolean magicHit = false;
        boolean confused = false;
        boolean normal = false;
        for (int i = 0; i < 30; i++) {
            p.setHp(before); // HPリセット
            cb.setConfuseTurns(0);
            w.performSpecialAction(p, cb, 5);
            if (p.getHp() < before)
                magicHit = true;
            if (cb.getConfuseTurns() > 0)
                confused = true;
            if (p.getHp() == before && cb.getConfuseTurns() == 0)
                normal = true;
        }
        assertTrue(magicHit);
        assertTrue(confused);
        assertTrue(normal);
    }
}
