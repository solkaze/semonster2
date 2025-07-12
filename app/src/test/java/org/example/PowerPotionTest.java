package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class PowerPotionTest {

  @Test
  public void testPowerPotionConstructorAndGetters() {
    PowerPotion potion = new PowerPotion("パワーポーション", 100, "攻撃力アップ", 20);
    assertEquals("パワーポーション", potion.getName());
    assertEquals(100, potion.getValue());
    assertEquals("攻撃力アップ", potion.getDescription());
    assertEquals(20, potion.getPowerBoost());
  }

  @Test
  public void testSetPowerBoost() {
    PowerPotion potion = new PowerPotion("パワーポーション", 100, "攻撃力アップ", 20);
    potion.setPowerBoost(30);
    assertEquals(30, potion.getPowerBoost());
  }
}
