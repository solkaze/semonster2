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
}
