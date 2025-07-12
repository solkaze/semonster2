package org.example;

public class Shield extends Item {
    private int defense;

    public Shield(String name, int value, String description, int defense) {
        super(name, value, description);
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "Shield{" +
                "name='" + getName() + '\'' +
                ", value=" + getValue() +
                ", description='" + getDescription() + '\'' +
                ", defense=" + defense +
                '}';
    }
}
