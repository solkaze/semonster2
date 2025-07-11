package org.example;

public class Skeleton extends Character {
  public Skeleton() {
    super(30, "スケルトン", 8);
  }

  // 剣に強いなどの特殊耐性は後で追加可能
  public int getExpReward() {
    return 13;
  }

  public int getGoldReward() {
    return 22;
  }

  @Override
  public void performSpecialAction(Player player, CommandBattle battle, int baseDamage) {
    // スケルトンは物理耐性（攻撃半減）
    int reduced = baseDamage / 2;
    System.out.println("スケルトンの攻撃！（物理耐性） あなたに" + reduced + "ダメージ！");
    if (player.damage(reduced)) {
      System.out.println("あなたは倒れてしまった... ゲームオーバー！");
      System.out.println("1. タイトルに戻る  2. 終了");
      String retryInput = battle.getScanner().nextLine();
      if (retryInput.equals("1")) {
        player.setHp(player.getMaxHp());
        battle.mainMenu();
      } else {
        System.out.println("ゲームを終了します。");
        System.exit(0);
      }
    }
  }
}
