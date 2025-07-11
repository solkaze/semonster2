package org.example;

import java.util.List;
import java.util.Random;

public class Dragon extends Character {
    public Dragon() {
        super(100, "ドラゴン", 25);
    }

    @Override
    public void specialAction(Player player, List<Item> inventory) {
        Random rand = new Random();
        if (rand.nextInt(3) == 0) {
            int fire = 15;
            System.out.println("ドラゴンの火炎攻撃！ あなたに" + fire + "ダメージ！");
            player.damage(fire);
        }
    }
    // 火炎攻撃などの特殊行動は後で追加可能

    @Override
    public void performSpecialAction(Player player, CommandBattle battle, int baseDamage) {
        Random rand = new Random();
        // 1/3の確率で火炎攻撃（やけど付与も）
        if (rand.nextInt(3) == 0) {
            int fire = 15;
            System.out.println("ドラゴンの火炎攻撃！ あなたに" + fire + "ダメージ！");
            player.damage(fire);
            // 50%でやけど
            if (rand.nextInt(2) == 0) {
                System.out.println("やけど状態になった！毎ターンHP-2");
                battle.setBurned(true);
            }
        } else {
            super.performSpecialAction(player, battle, baseDamage);
        }
    }

    public int getExpReward() {
        return 40;
    }

    public int getGoldReward() {
        return 100;
    }
}
