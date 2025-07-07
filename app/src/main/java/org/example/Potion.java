package app.src.main.java.org.example;
public class Potion extends Item {
    private int healAmount;

    public Potion(String name, int value, String description, int healAmount) {
        super(name, value, description);
        this.healAmount = healAmount;
    }

    public int getHealAmount() {
        return healAmount;
    }

    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }

    @Override
    public String toString() {
        return "Potion{" +
                "name='" + getName() + '\'' +
                ", value=" + getValue() +
                ", description='" + getDescription() + '\'' +
                ", healAmount=" + healAmount +
                '}';
    }
}
