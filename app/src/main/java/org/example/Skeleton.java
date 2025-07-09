package org.example;

public class Skeleton extends Character {
    public Skeleton() {
        super(30, "スケルトン", 8);
    }

    // 剣に強いなどの特殊耐性は後で追加可能
    public int getExpReward() {
        return 13;
    }

    public int getGoldReward() {
        return 22;
    }
}
