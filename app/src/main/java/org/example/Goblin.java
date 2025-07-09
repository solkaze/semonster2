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

    @Override
    public void performSpecialAction(Player player, CommandBattle battle, int baseDamage) {
        Random rand = new Random();
        // 20%の確率でアイテムを盗む
        if (!battle.getInventoryItems().isEmpty() && rand.nextInt(5) == 0) {
            List<Item> items = battle.getInventoryItems();
            Item stolen = items.remove(rand.nextInt(items.size()));
            System.out.println("ゴブリンがあなたの" + stolen.getName() + "を盗んだ！");
        } else {
            super.performSpecialAction(player, battle, baseDamage);
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
