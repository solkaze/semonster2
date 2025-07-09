package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class SkeletonTest {
    @Test
    public void testSkeletonConstructorAndReward() {
        Skeleton s = new Skeleton();
        assertEquals(30, s.getHp());
        assertEquals("スケルトン", s.getName());
        assertEquals(13, s.getExpReward());
        assertEquals(22, s.getGoldReward());
    }

    @Test
    public void testPerformSpecialAction_PhysicalResist() {
        Skeleton s = new Skeleton();
        Player p = new Player();
        CommandBattle cb = new CommandBattle();
        int before = p.getHp();
        s.performSpecialAction(p, cb, 10);
        assertEquals(before - 5, p.getHp()); // 物理耐性で半減
    }
}
