package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class DragonTest {
    @Test
    public void testDragonConstructorAndReward() {
        Dragon d = new Dragon();
        assertEquals(100, d.getHp());
        assertEquals("ドラゴン", d.getName());
        assertEquals(40, d.getExpReward());
        assertEquals(100, d.getGoldReward());
    }

    @Test
    public void testSpecialActionFire() {
        Dragon d = new Dragon();
        Player p = new Player();
        int before = p.getHp();
        d.specialAction(p, new ArrayList<>());
        assertTrue(p.getHp() <= before);
    }
}
