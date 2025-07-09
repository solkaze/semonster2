package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Scanner;

public class SkeletonTest {
    @Test
    public void testSkeletonConstructorAndReward() {
        Skeleton s = new Skeleton();
        assertEquals(30, s.getHp());
        assertEquals("スケルトン", s.getName());
        assertEquals(13, s.getExpReward());
        assertEquals(22, s.getGoldReward());
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
    public void testPerformSpecialAction_PhysicalResist() {
        Skeleton s = new Skeleton();
        Player p = new Player();
        TestBattle cb = new TestBattle();
        int before = p.getHp();
        try {
            s.performSpecialAction(p, cb, 10);
        } catch (Exception e) {
            /* 入力例外は無視 */ }
        assertEquals(before - 5, p.getHp()); // 物理耐性で半減
    }
}
