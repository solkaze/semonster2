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

    public int getExpReward() {
        return 40;
    }

    public int getGoldReward() {
        return 100;
    }
}
