package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class CharacterTest {
    @Test
    public void testDefaultConstructor() {
        Character c = new Character();
        assertEquals(20, c.getHp());
        assertEquals(20, c.getMaxHp());
        assertEquals("Monster", c.getName());
        assertEquals(1, c.getLevel());
        assertEquals(0, c.getExp());
        assertEquals(10, c.getNextExp());
    }

    @Test
    public void testParameterizedConstructor() {
        Character c = new Character(50, 60, "Test", 20);
        assertEquals(50, c.getHp());
        assertEquals(60, c.getMaxHp());
        assertEquals("Test", c.getName());
    }

    @Test
    public void testAttackRange() {
        Character c = new Character(20, 20, "A", 10);
        int atk = c.attack();
        assertTrue(atk >= 10 && atk <= 11);
    }

    @Test
    public void testDamage() {
        Character c = new Character(10, 10, "A", 1);
        boolean defeated = c.damage(5);
        assertFalse(defeated);
        assertEquals(5, c.getHp());
        defeated = c.damage(5);
        assertTrue(defeated);
        assertEquals(0, c.getHp());
    }

    @Test
    public void testLevelUpAndGrowth() {
        Character c = new Character();
        int beforeMaxHp = c.getMaxHp();
        c.gainExp(100); // 十分な経験値で複数回レベルアップ
        assertTrue(c.getLevel() > 1);
        assertTrue(c.getMaxHp() > beforeMaxHp);
        assertEquals(c.getHp(), c.getMaxHp()); // レベルアップ時は全回復
    }

    @Test
    public void testUsePotionHealsToMaxHp() {
        Character c = new Character(10, 20, "A", 1);
        Potion p = new Potion("potion", 0, "heal", 50);
        c.addItem(p);
        boolean used = c.useItem(p);
        assertTrue(used);
        assertEquals(20, c.getHp()); // maxHpまでしか回復しない
    }

    @Test
    public void testSpecialActionDefault() {
        Character c = new Character();
        Player p = new Player();
        List<Item> inv = new ArrayList<>();
        c.specialAction(p, inv); // 何も起きない
        assertTrue(true); // 例外が出なければOK
    }
}
