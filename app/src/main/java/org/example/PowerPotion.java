package org.example;

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

}
