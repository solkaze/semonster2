package org.example;

public class RecoveryPotion extends Potion {
  private int recoveryAmount;

  public RecoveryPotion(String name, int value, String description, int recoveryAmount) {
    super(name, value, description, recoveryAmount);
    this.recoveryAmount = recoveryAmount;
  }

  public int getRecoveryAmount() {
    return recoveryAmount;
  }

  public void setRecoveryAmount(int recoveryAmount) {
    this.recoveryAmount = recoveryAmount;
  }

  public void use(Player player) {
    int newHp = player.getHp() + recoveryAmount;
    player.setHp(newHp);
    System.out.println(player.getName() + "のHPが" + recoveryAmount + "回復しました。現在のHP: " + newHp);
  }

  @Override
  public String toString() {
    return "RecoveryPotion{" +
        "name='" + getName() + '\'' +
        ", value=" + getValue() +
        ", description='" + getDescription() + '\'' +
        ", recoveryAmount=" + recoveryAmount +
        '}';
  }

}
