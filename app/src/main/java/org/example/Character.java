package org.example;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Character {
    private int hp;
    private String name;
    private int power;
    protected Random rand;
    // 今後の拡張用。現状はuseItemでのみ利用
    protected ArrayList<Item> items;
    private static final int MAX_HP = 1000;
    private static final int MAX_POWER = 500;
    private static final int MAX_ITEM_COUNT = 10;

    Character() {
        this(100, "Monster", 20);
    }

    Character(int hp, String name, int power) {
        this.hp = hp;
        this.name = name;
        this.power = power;
        this.items = new ArrayList<>();
        this.rand = new Random();
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // アイテムの追加に成功したらtrueを返す
    public boolean addItem(Item item) {
        if (items.size() < MAX_ITEM_COUNT) {
            items.add(item);
            return true;
        } else {
            return false; // アイテムの数が上限に達している
        }
    }

    private boolean removeItem(Item item) {
        if (items.contains(item)) {
            items.remove(item);
            return true; // アイテムの削除に成功
        } else {
            return false; // アイテムが見つからない
        }
    }

    public boolean useItem(Item item) {
        if (items.contains(item)) {
            if (item instanceof Potion) {
                Potion potion = (Potion) item;
                int healAmount = potion.getHealAmount();
                hp += healAmount;
                if (hp > MAX_HP)
                    hp = MAX_HP;
                removeItem(item);
                return true;
            } else if (item instanceof Sword) {
                Sword sword = (Sword) item;
                power += sword.getAttackPower();
                if (power > MAX_POWER)
                    power = MAX_POWER;
                removeItem(item);
                return true;
            } else if (item instanceof Shield) {
                // シールドの効果例: HP小回復
                hp += 5;
                if (hp > MAX_HP)
                    hp = MAX_HP;
                removeItem(item);
                return true;
            } else if (item instanceof MagicWand) {
                MagicWand wand = (MagicWand) item;
                power += wand.getMagicPower();
                if (power > MAX_POWER)
                    power = MAX_POWER;
                removeItem(item);
                return true;
            } else if (item instanceof Bow) {
                Bow bow = (Bow) item;
                power += bow.getAccuracy();
                if (power > MAX_POWER)
                    power = MAX_POWER;
                removeItem(item);
                return true;
            } else if (item instanceof Armor) {
                Armor armor = (Armor) item;
                hp += armor.getHpBoost();
                if (hp > MAX_HP)
                    hp = MAX_HP;
                removeItem(item);
                return true;
            } else if (item instanceof Ring) {
                // リングの効果例: HP自動回復
                hp += 10;
                if (hp > MAX_HP)
                    hp = MAX_HP;
                removeItem(item);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public int attack() {
        int tmp = power + rand.nextInt(2);
        if (tmp < 0) {
            return 0;
        } else {
            return tmp;
        }
    }

    public boolean damage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            hp = 0;
            return true;
        } else {
            return false;
        }
    }

    // プレイヤーやアイテムリストを受け取って特殊行動を行う（デフォルトは何もしない）
    public void specialAction(Player player, List<Item> inventory) {
        // 何もしない
    }

}
