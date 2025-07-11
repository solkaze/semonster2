
package org.example;

public class Slime extends Character {
  public Slime() {
    super(10, "Slime", 1);
  }

  public int getExpReward() {
    return 4;
  }

  public int getGoldReward() {
    return 8;
  }

  @Override
  public void performSpecialAction(Player player, CommandBattle battle, int baseDamage) {
    // スライムは特殊行動なし（デフォルトの通常攻撃）
    super.performSpecialAction(player, battle, baseDamage);
  }
}
