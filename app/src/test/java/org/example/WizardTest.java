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
}
