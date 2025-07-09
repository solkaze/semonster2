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
}
