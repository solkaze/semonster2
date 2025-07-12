package org.example;

import java.util.List;
import java.util.Random;

public class Wizard extends Character {
    public Wizard() {
        super(25, "ウィザード", 12);
    }

    @Override
    public void specialAction(Player player, List<Item> inventory) {
        Random rand = new Random();
        if (rand.nextInt(4) == 0) {
            int magic = 10;
            System.out.println("ウィザードの魔法攻撃！ あなたに" + magic + "ダメージ！");
            player.damage(magic);
        }
    }
    // 魔法攻撃や状態異常付与は後で追加可能

    @Override
    public void performSpecialAction(Player player, CommandBattle battle, int baseDamage) {
        Random rand = new Random();
        // 1/4の確率で魔法攻撃＋混乱付与
        if (rand.nextInt(4) == 0) {
            int magic = 10;
            System.out.println("ウィザードの魔法攻撃！ あなたに" + magic + "ダメージ！");
            player.damage(magic);
            // 50%で混乱
            if (rand.nextInt(2) == 0) {
                System.out.println("混乱状態になった！（2ターン）");
                battle.setConfuseTurns(2);
            }
        } else {
            super.performSpecialAction(player, battle, baseDamage);
        }
    }

    public int getExpReward() {
        return 25;
    }

    public int getGoldReward() {
        return 50;
    }
}
