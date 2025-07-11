package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class RecoveryPotion {
  @Test
  public void testRecoveryPotionConstructorAndGetters() {
    Potion potion = new Potion("recovery potion", 20, "desc", 30);
    assertEquals("recovery potion", potion.getName());
    assertEquals(20, potion.getValue());
    assertEquals("desc", potion.getDescription());
    assertEquals(30, potion.getHealAmount());
  }

  @Test
  public void testSetHealAmount() {
    Potion potion = new Potion("recovery potion", 20, "desc", 30);
    potion.setHealAmount(50);
    assertEquals(50, potion.getHealAmount());
  }
}
