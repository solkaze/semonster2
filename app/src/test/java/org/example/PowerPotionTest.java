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

  @Test
  public void testToString() {
    PowerPotion potion = new PowerPotion("パワーポーション", 100, "攻撃力アップ", 20);
    // 各カンマの後ろにスペースを追加する
    String expected = "PowerPotion{" +
        "name='パワーポーション'" +
        ", value=100" + // カンマの後ろにスペースを追加
        ", description='攻撃力アップ'" + // カンマの後ろにスペースを追加
        ", powerBoost=20" +
        '}';
    assertEquals(expected, potion.toString());
  }

  @Test
  public void testUseItem() {
    Player player = new Player(30, 30, "テストプレイヤー", 1);
    PowerPotion potion = new PowerPotion("パワーポーション", 100, "攻撃力アップ", 20);
    potion.use(player);
    // プレイヤーの攻撃力が増加したことを確認
    assertEquals(21, player.getPower()); // プレイヤーの初期攻撃力が0で、ポーション使用後に20増加することを想定
  }
}
