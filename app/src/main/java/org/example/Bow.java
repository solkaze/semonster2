package org.example;

public class Bow extends Item {
    private int accuracy;

    public Bow(String name, int value, String description, int accuracy) {
        super(name, value, description);
        this.accuracy = accuracy;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    @Override
    public String toString() {
        return "Bow{" +
                "name='" + getName() + '\'' +
                ", value=" + getValue() +
                ", description='" + getDescription() + '\'' +
                ", accuracy=" + accuracy +
                '}';
    }
}
