package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class RecoveryPotionTest {
  @Test
  public void testRecoveryPotionConstructorAndGetters() {
    RecoveryPotion potion = new RecoveryPotion("recovery potion", 20, "desc", 10);
    assertEquals("recovery potion", potion.getName());
    assertEquals(20, potion.getValue());
    assertEquals("desc", potion.getDescription());
    assertEquals(10, potion.getRecoveryAmount());
  }

  @Test
  public void testSetHealthRecovery() {
    RecoveryPotion potion = new RecoveryPotion("recovery potion", 20, "desc", 10);
    potion.setRecoveryAmount(15);
    assertEquals(15, potion.getRecoveryAmount());
  }
}
