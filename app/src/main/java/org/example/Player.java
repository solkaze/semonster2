
package org.example;

public class Player extends Character {
  private int money = 0;

  public Player() {
    super(30, 30, "勇者", 5);
  }

  public Player(int hp, int maxHp, String name, int power) {
    super(hp, maxHp, name, power);
  }

  

  public int getMoney() {
    return money;
  }

  public void addMoney(int amount) {
    money += amount;
  }

  public boolean spendMoney(int amount) {
    if (money >= amount) {
      money -= amount;
      return true;
    }
    return false;
  }
}
