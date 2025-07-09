package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

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

    @Test
    public void testPerformSpecialAction_StealOrNormal() {
        Goblin g = new Goblin();
        Player p = new Player();
        CommandBattle cb = new CommandBattle();
        List<Item> items = cb.getInventoryItems();
        items.add(new Potion("p", 10, "", 1));
        boolean stolen = false;
        boolean normal = false;
        for (int i = 0; i < 30; i++) {
            if (items.isEmpty())
                items.add(new Potion("p", 10, "", 1));
            int before = items.size();
            g.performSpecialAction(p, cb, 5);
            if (items.size() < before)
                stolen = true;
            if (items.size() == before)
                normal = true;
        }
        assertTrue(stolen);
        assertTrue(normal);
    }
}
