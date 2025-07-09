package org.example;

import java.util.List;
import java.util.Random;

public class Goblin extends Character {
    public Goblin() {
        super(20, "ゴブリン", 5);
    }

    @Override
    public void specialAction(Player player, List<Item> inventory) {
        Random rand = new Random();
        if (rand.nextInt(5) == 0 && !inventory.isEmpty()) {
            Item stolen = inventory.remove(rand.nextInt(inventory.size()));
            System.out.println("ゴブリンがあなたの" + stolen.getName() + "を盗んだ！");
        }
    }

    // たまにアイテムを盗むなどの特殊行動は後で追加可能
    public int getExpReward() {
        return 8;
    }

    public int getGoldReward() {
        return 15;
    }
}
