package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Scanner;

public class SlimeTest {
    @Test
    public void testSlimeConstructor() {
        Slime s = new Slime();
        assertEquals(10, s.getHp());
        assertEquals("Slime", s.getName());
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
    public void testPerformSpecialAction_NormalAttack() {
        Slime s = new Slime();
        Player p = new Player();
        TestBattle cb = new TestBattle();
        int before = p.getHp();
        try {
            s.performSpecialAction(p, cb, 7);
        } catch (Exception e) {
            /* 入力例外は無視 */ }
        assertEquals(before - 7, p.getHp()); // 通常攻撃
    }
}
