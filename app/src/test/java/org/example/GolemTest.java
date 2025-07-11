package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Scanner;

public class GolemTest {

    static class TestBattle extends CommandBattle {
        @Override
        public void mainMenu() { /* テスト中はメニューに戻らないので何もしない */ }

        @Override
        public Scanner getScanner() {
            // System.inの代わりに空の文字列を返すScannerを生成する
            return new Scanner("");
        }
    }

    /**
     * Golemのコンストラクタが正しく初期値を設定するか、
     * また、報酬（経験値・ゴールド）が正しく返されるかをテストする。
     */
    @Test
    public void testGolemConstructorAndReward() {

        Golem golem = new Golem();

        assertEquals("HPの初期値が正しくありません", 80, golem.getHp());
        assertEquals("名前が正しくありません", "ゴーレム", golem.getName());
        assertEquals("経験値報酬が正しくありません", 30, golem.getExpReward());
        assertEquals("ゴールド報酬が正しくありません", 40, golem.getGoldReward());
    }

    /**
     * Golemの特殊行動(performSpecialAction)が、
     * 「攻撃成功」と「攻撃失敗」の両方のパターンを確率通りに実行するかテストする。
     */
    @Test
    public void testPerformSpecialAction() {

        Golem golem = new Golem();
        Player player = new Player(200, 200, "テスト勇者", 10); // HPが十分あるプレイヤー
        TestBattle battle = new TestBattle();

        // 各パターンの発生を記録するためのフラグ
        boolean attackHit = false;
        boolean attackMiss = false;

        // 確率的な事象を検証するため、十分な回数ループを実行する
        for (int i = 0; i < 100; i++) {
            player.setHp(200);
            int initialHp = player.getHp();
            
            golem.performSpecialAction(player, battle, 5);

            if (player.getHp() < initialHp) {
                attackHit = true;
            } else {

                attackMiss = true;
            }
        }

        // 100回の試行の中で、両方のパターンが少なくとも1回は発生したことを検証
        assertTrue("100回の試行で攻撃が一度も成功しませんでした", attackHit);
        assertTrue("100回の試行で攻撃が一度も外れませんでした", attackMiss);
    }
}