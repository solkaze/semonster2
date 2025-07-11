package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class CommandBattleTest {
  @Test
  public void testInitPlayerStatus() {
    CommandBattle cb = new CommandBattle();
    Player p = cb.getPlayer();
    assertEquals(30, p.getHp());
    assertEquals("勇者", p.getName());
    assertEquals(1, p.getLevel());
  }
  // バトル進行や報酬、レベルアップ演出はモック化や標準出力キャプチャで拡張可能
}
