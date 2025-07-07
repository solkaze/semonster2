import java.util.Random;
import java.util.ArrayList;

public class Character {
  private int hp;
  private String name;
  private int power;
  private Random rand;

  Character() {
    this.hp = 100;
    this.name = "Monster";
    this.power = 20;
    rand = new Random();
  }

  Character(int hp, String name, int power) {
    this.hp = hp;
    this.name = name;
    this.power = power;
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

  public int attack() {
    int tmp = power + rand.nextInt(-2, 2);
    if (tmp < 0) {
      return 0;
    } else {
      return tmp;
    }
  }

}
