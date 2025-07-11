package org.example;

public class PowerPotion extends Potion {
  private int powerBoost;

  public PowerPotion(String name, int value, String description, int powerBoost) {
    super(name, value, description, 0); // healAmount is not used in PowerPotion
    this.powerBoost = powerBoost;
  }

  public int getPowerBoost() {
    return powerBoost;
  }

  public void setPowerBoost(int powerBoost) {
    this.powerBoost = powerBoost;
  }

  public void use(Player player) {
    int newAttackPower = player.getPower() + powerBoost;
    player.setPower(newAttackPower);
    System.out.println(player.getName() + "の攻撃力が" + powerBoost + "上昇しました。現在の攻撃力: " + newAttackPower);
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
}
