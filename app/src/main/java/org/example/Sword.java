package org.example;

public class Sword extends Item {
    private int attackPower;

    public Sword(String name, int value, String description, int attackPower) {
        super(name, value, description);
        this.attackPower = attackPower;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    @Override
    public String toString() {
        return "Sword{" +
                "name='" + getName() + '\'' +
                ", value=" + getValue() +
                ", description='" + getDescription() + '\'' +
                ", attackPower=" + attackPower +
                '}';
    }

}
