package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class PowerPotionTest {
  @Test
  public void testPowerPotionConstructorAndGetters() {
    PowerPotion potion = new PowerPotion("power potion", 15, "desc", 5);
    assertEquals("power potion", potion.getName());
    assertEquals(15, potion.getValue());
    assertEquals("desc", potion.getDescription());
    assertEquals(5, potion.getPowerBoost());
  }

  @Test
  public void testSetPowerBoost() {
    PowerPotion potion = new PowerPotion("power potion", 15, "desc", 5);
    potion.setPowerBoost(10);
    assertEquals(10, potion.getPowerBoost());
  }
}
