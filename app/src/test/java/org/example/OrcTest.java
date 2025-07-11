package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Scanner;

public class OrcTest {
  @Test
  public void testOrcConstructorAndReward() {
    Orc o = new Orc();
    assertEquals(40, o.getHp());
    assertEquals("オーク", o.getName());
    assertEquals(18, o.getExpReward());
    assertEquals(30, o.getGoldReward());
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
  public void testPerformSpecialAction_TwoAttackOrNormal() {
    Orc o = new Orc();
    Player p = new Player();
    TestBattle cb = new TestBattle();
    int before = p.getHp();
    boolean twoAttack = false;
    boolean normal = false;
    for (int i = 0; i < 30; i++) {
      p.setHp(before);
      try {
        o.performSpecialAction(p, cb, 5);
      } catch (Exception e) {
        /* 入力例外は無視 */ }
      if (p.getHp() < before - 5)
        twoAttack = true; // 2回攻撃
      if (p.getHp() == before - 5)
        normal = true; // 通常攻撃
    }
    assertTrue(twoAttack);
    assertTrue(normal);
  }
}
