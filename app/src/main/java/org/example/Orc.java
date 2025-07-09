package org.example;

public class Orc extends Character {
    public Orc() {
        super(40, "オーク", 15);
    }

    // 2回攻撃などの特殊行動は後で追加可能
    public int getExpReward() {
        return 18;
    }

    public int getGoldReward() {
        return 30;
    }

    @Override
    public void performSpecialAction(Player player, CommandBattle battle, int baseDamage) {
        // 1/3の確率で2回攻撃
        if (rand.nextInt(3) == 0) {
            System.out.println("オークの2回攻撃！");
            for (int i = 0; i < 2; i++) {
                int orcDmg = attack();
                if (battle.isDefending())
                    orcDmg /= 2;
                if (orcDmg < 0)
                    orcDmg = 0;
                System.out.println("オークの攻撃！ あなたに" + orcDmg + "ダメージ！");
                if (player.damage(orcDmg)) {
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
                    return;
                }
            }
        } else {
            super.performSpecialAction(player, battle, baseDamage);
        }
    }
}
