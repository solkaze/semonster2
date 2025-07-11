/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
    @Test
    public void testSlimeAndPlayerBattle() {
        Slime slime = new Slime();
        Player player = new Player(30, 30, "勇者", 5);
        int attack = player.attack();
        boolean defeated = slime.damage(attack);
        assertTrue(slime.getHp() <= 10);
        if (defeated) {
            assertEquals(0, slime.getHp());
        } else {
            assertTrue(slime.getHp() > 0);
        }
    }

    @Test
    public void testPlayerAddAndUsePotion() {
        Player player = new Player(10, 30, "勇者", 5);
        Potion potion = new Potion("回復薬", 100, "HP回復", 50);
        assertTrue(player.addItem(potion));
        assertTrue(player.useItem(potion));
        assertEquals(30, player.getHp()); // maxHpまでしか回復しない
    }

    @Test
    public void testPlayerAddAndUseSword() {
        Player player = new Player(30, 30, "勇者", 5);
        Sword sword = new Sword("剣", 200, "攻撃力アップ", 20);
        assertTrue(player.addItem(sword));
        assertTrue(player.useItem(sword));
        int atk = player.attack();
        assertTrue(atk >= 25 && atk <= 26);
    }

    @Test
    public void testRemoveItem() {
        Player player = new Player();
        Potion potion = new Potion("p", 10, "", 1);
        player.addItem(potion);
        assertTrue(player.useItem(potion));
        assertFalse(player.useItem(potion));
    }
}
