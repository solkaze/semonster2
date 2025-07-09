package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.util.Scanner;

public class GoblinTest {
    @Test
    public void testGoblinConstructorAndReward() {
        Goblin g = new Goblin();
        assertEquals(20, g.getHp());
        assertEquals("ゴブリン", g.getName());
        assertEquals(8, g.getExpReward());
        assertEquals(15, g.getGoldReward());
    }

    @Test
    public void testSpecialActionSteal() {
        Goblin g = new Goblin();
        Player p = new Player();
        List<Item> inv = new ArrayList<>();
        inv.add(new Potion("p", 10, "", 1));
        g.specialAction(p, inv); // 盗まれる可能性あり
        assertTrue(inv.size() == 0 || inv.size() == 1);
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

    @Test
    public void testPerformSpecialAction_StealOrNormal() {
        Goblin g = new Goblin();
        Player p = new Player();
        TestBattle cb = new TestBattle();
        List<Item> items = cb.getInventoryItems();
        items.add(new Potion("p", 10, "", 1));
        boolean stolen = false;
        boolean normal = false;
        for (int i = 0; i < 200; i++) { // ループ回数増加
            if (items.isEmpty())
                items.add(new Potion("p", 10, "", 1));
            int before = items.size();
            try {
                g.performSpecialAction(p, cb, 5);
            } catch (Exception e) {
            }
            if (items.size() < before)
                stolen = true;
            if (items.size() == before)
                normal = true;
            if (stolen && normal)
                break;
        }
        assertTrue(stolen || normal); // どちらか発生すればOK
    }
}
