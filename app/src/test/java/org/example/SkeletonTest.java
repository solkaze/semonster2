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
}
