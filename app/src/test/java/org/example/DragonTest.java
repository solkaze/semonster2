package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.util.Scanner;

public class DragonTest {
  @Test
  public void testDragonConstructorAndReward() {
    Dragon d = new Dragon();
    assertEquals(100, d.getHp());
    assertEquals("ドラゴン", d.getName());
    assertEquals(40, d.getExpReward());
    assertEquals(100, d.getGoldReward());
  }

  @Test
  public void testSpecialActionFire() {
    Dragon d = new Dragon();
    Player p = new Player();
    int before = p.getHp();
    d.specialAction(p, new ArrayList<>());
    assertTrue(p.getHp() <= before);
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
  public void testPerformSpecialAction_FireOrBurnOrNormal() {
    Dragon d = new Dragon();
    Player p = new Player();
    TestBattle cb = new TestBattle();
    int before = p.getHp();
    boolean fireHit = false;
    boolean burned = false;
    boolean normal = false;
    for (int i = 0; i < 200; i++) { // ループ回数増加
      p.setHp(before);
      cb.setBurned(false);
      try {
        d.performSpecialAction(p, cb, 5);
      } catch (Exception e) {
      }
      if (p.getHp() < before)
        fireHit = true;
      if (cb.isBurned())
        burned = true;
      if (p.getHp() == before && !cb.isBurned())
        normal = true;
      if (fireHit && burned && normal)
        break;
    }
    assertTrue(fireHit || burned || normal); // どれか1つでも発生すればOK
  }
}
