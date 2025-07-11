package org.example;

public class Armor extends Item {
  private int hpBoost;
  private int defense;

  public Armor(String name, int value, String description, int hpBoost, int defense) {
    super(name, value, description);
    this.hpBoost = hpBoost;
    this.defense = defense;
  }

  public int getHpBoost() {
    return hpBoost;
  }

  public void setHpBoost(int hpBoost) {
    this.hpBoost = hpBoost;
  }

  public int getDefense() {
    return defense;
  }

  public void setDefense(int defense) {
    this.defense = defense;
  }

  @Override
  public String toString() {
    return "Armor{" +
        "name='" + getName() + '\'' +
        ", value=" + getValue() +
        ", description='" + getDescription() + '\'' +
        ", hpBoost=" + hpBoost +
        ", defense=" + defense +
        '}';
  }
}
