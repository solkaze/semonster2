package org.example;

public class Ring extends Item {
    private String effect;

    public Ring(String name, int value, String description, String effect) {
        super(name, value, description);
        this.effect = effect;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    @Override
    public String toString() {
        return "Ring{" +
                "name='" + getName() + '\'' +
                ", value=" + getValue() +
                ", description='" + getDescription() + '\'' +
                ", effect='" + effect + '\'' +
                '}';
    }
}
