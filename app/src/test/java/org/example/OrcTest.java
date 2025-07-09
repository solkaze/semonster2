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
}
