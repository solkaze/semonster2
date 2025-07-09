package org.example;

public class Slime extends Character {
    public Slime() {
        super(10, "Slime", 1);
    }

    public int getExpReward() {
        return 4;
    }

    public int getGoldReward() {
        return 8;
    }
}
