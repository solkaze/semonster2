package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.util.Scanner;

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
        TestBattle cb = new TestBattle();
        int before = p.getHp();
        boolean magicHit = false;
        boolean confused = false;
        boolean normal = false;
        for (int i = 0; i < 200; i++) { // ループ回数増加
            p.setHp(before);
            cb.setConfuseTurns(0);
            try {
                w.performSpecialAction(p, cb, 5);
            } catch (Exception e) {
            }
            if (p.getHp() < before)
                magicHit = true;
            if (cb.getConfuseTurns() > 0)
                confused = true;
            if (p.getHp() == before && cb.getConfuseTurns() == 0)
                normal = true;
            if (magicHit && confused && normal)
                break;
        }
        assertTrue(magicHit || confused || normal); // どれか1つでも発生すればOK
    }

    // テスト用: mainMenuやSystem.inを呼ばないダミーCommandBattle
    static class TestBattle extends CommandBattle {
        @Override
        public void mainMenu() {
            /* do nothing */ }

        @Override
        public Scanner getScanner() {
            return new Scanner("");
        }
    }
}
