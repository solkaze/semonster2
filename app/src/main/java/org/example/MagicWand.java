package org.example;

public class MagicWand extends Item {
    private int magicPower;

    public MagicWand(String name, int value, String description, int magicPower) {
        super(name, value, description);
        this.magicPower = magicPower;
    }

    public int getMagicPower() {
        return magicPower;
    }

    public void setMagicPower(int magicPower) {
        this.magicPower = magicPower;
    }

    @Override
    public String toString() {
        return "MagicWand{" +
                "name='" + getName() + '\'' +
                ", value=" + getValue() +
                ", description='" + getDescription() + '\'' +
                ", magicPower=" + magicPower +
                '}';
    }
}
