package org.example;

import java.util.Random;
import java.util.ArrayList;

public class Character {
    private int hp;
    private String name;
    private int power;
    protected Random rand;
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
        // アイテムが存在するか確認
        if (items.contains(item)) {
            // アイテムの種類に応じて処理を分岐
            if (item instanceof Potion) {
                Potion potion = (Potion) item;
                // ポーションの効果を適用
                int healAmount = potion.getHealAmount();
                hp += healAmount;
                if (hp > MAX_HP) {
                    hp = MAX_HP; // 最大HPを超えないようにする
                }
                removeItem(item); // アイテムを使用したので削除
                return true; // 使用成功
            } else if (item instanceof Sword) {
                Sword sword = (Sword) item;
                // 剣の効果を適用（例: 攻撃力を上げる）
                power += sword.getAttackPower();
                if (power > MAX_POWER) {
                    power = MAX_POWER; // 最大攻撃力を超えないようにする
                }
                removeItem(item); // アイテムを使用したので削除
                return true; // 使用成功
            } else {
                return false; // 未対応のアイテムタイプ
            }
        } else {
            return false; // アイテムが存在しない
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

}
