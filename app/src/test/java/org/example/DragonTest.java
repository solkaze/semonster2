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

    @Test
    public void testPerformSpecialAction_FireOrBurnOrNormal() {
        Dragon d = new Dragon();
        Player p = new Player();
        CommandBattle cb = new CommandBattle();
        int before = p.getHp();
        boolean fireHit = false;
        boolean burned = false;
        boolean normal = false;
        for (int i = 0; i < 30; i++) {
            p.setHp(before);
            cb.setBurned(false);
            d.performSpecialAction(p, cb, 5);
            if (p.getHp() < before)
                fireHit = true;
            if (cb.isBurned())
                burned = true;
            if (p.getHp() == before && !cb.isBurned())
                normal = true;
        }
        assertTrue(fireHit);
        assertTrue(burned);
        assertTrue(normal);
    }
}
