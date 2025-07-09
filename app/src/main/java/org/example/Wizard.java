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
}
