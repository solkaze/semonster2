package org.example;

import org.example.Player;

public class PowerPotion extends Item {
  private int powerBoost;

  public PowerPotion(String name, int value, String description, int powerBoost) {
    super(name, value, description);
    this.powerBoost = powerBoost;
  }

  public void setPowerBoost(int powerBoost) {
    this.powerBoost = powerBoost;
  }

  public int getPowerBoost() {
    return powerBoost;
  }

  @Override
  public String toString() {
    return "PowerPotion{" +
        "name='" + getName() + '\'' +
        ", value=" + getValue() +
        ", description='" + getDescription() + '\'' +
        ", powerBoost=" + powerBoost +
        '}';
  }

  public void use(Player player) {
    // プレイヤーの攻撃力を増加させるロジックをここに実装
    player.increaseAttackPower(powerBoost);
  }

}
