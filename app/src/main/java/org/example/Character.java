
package org.example;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Character {
  protected int hp;
  protected int maxHp; // 最大体力
  private String name;
  private int power;
  protected Random rand;
  // 今後の拡張用。現状はuseItemでのみ利用
  protected ArrayList<Item> items;
  protected int exp = 0; // 経験値
  protected int nextExp = 10; // 次のレベルまでの経験値
  protected int level = 1;

  Character() {
    this(20, 20, "Monster", 5);
  }

  Character(int hp, int maxHp, String name, int power) {
    this.hp = hp;
    this.maxHp = maxHp;
    this.name = name;
    this.power = power;
    this.items = new ArrayList<>();
    this.rand = new Random();
  }

  // 旧コンストラクタ互換
  Character(int hp, String name, int power) {
    this(hp, hp, name, power);
  }

  public int getHp() {
    return hp;
  }

  public void setHp(int hp) {
    this.hp = Math.min(hp, maxHp);
  }

  public int getMaxHp() {
    return maxHp;
  }

  public void setMaxHp(int maxHp) {
    this.maxHp = maxHp;
    if (hp > maxHp)
      hp = maxHp;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getExp() {
    return exp;
  }

  public int getNextExp() {
    return nextExp;
  }

  public void gainExp(int amount) {
    this.exp += amount;
    while (exp >= nextExp) {
      exp -= nextExp;
      levelUp();
    }
  }

  protected void levelUp() {
    level++;
    int hpIncrease = 5 + rand.nextInt(6); // 5~10増加
    maxHp += hpIncrease;
    hp = maxHp; // レベルアップ時は全回復
    nextExp = (int) (nextExp * 1.5 + 5); // 次の必要経験値増加
    // レベルアップ演出
    System.out.println("\n==============================");
    System.out.println("  ★★★ LEVEL UP!! ★★★");
    System.out.println("    \u2605 Lv." + level + "  最大HP+" + hpIncrease + " \u2605");
    System.out.println("==============================");
    System.out.println("新たな力がみなぎる！ HPが全回復した！");
    System.out.println();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // アイテムの追加に成功したらtrueを返す
  public boolean addItem(Item item) {
    items.add(item);
    return true;
  }

  private boolean removeItem(Item item) {
    if (items.contains(item)) {
      items.remove(item);
      return true; // アイテムの削除に成功
    } else {
      return false; // アイテムが見つからない
    }
  }

  public boolean useItem(Item item) {
    if (items.contains(item)) {
      if (item instanceof Potion) {
        Potion potion = (Potion) item;
        int healAmount = potion.getHealAmount();
        hp += healAmount;
        if (hp > maxHp)
          hp = maxHp;
        removeItem(item);
        return true;
      } else if (item instanceof Sword) {
        Sword sword = (Sword) item;
        power += sword.getAttackPower();
        removeItem(item);
        return true;
      } else if (item instanceof Shield) {
        // シールドの効果例: HP小回復
        hp += 5;
        if (hp > maxHp)
          hp = maxHp;
        removeItem(item);
        return true;
      } else if (item instanceof MagicWand) {
        MagicWand wand = (MagicWand) item;
        power += wand.getMagicPower();
        removeItem(item);
        return true;
      } else if (item instanceof Bow) {
        Bow bow = (Bow) item;
        power += bow.getAccuracy();
        removeItem(item);
        return true;
      } else if (item instanceof Armor) {
        Armor armor = (Armor) item;
        hp += armor.getHpBoost();
        if (hp > maxHp)
          hp = maxHp;
        removeItem(item);
        return true;
      } else if (item instanceof Ring) {
        // リングの効果例: HP自動回復
        hp += 10;
        if (hp > maxHp)
          hp = maxHp;
        removeItem(item);
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  public int attack() {
    int tmp = power + rand.nextInt(2);
    if (tmp < 0) {
      return 0;
    } else {
      return tmp;
    }
  }

  public boolean damage(int damage) {
    hp -= damage;
    if (hp <= 0) {
      hp = 0;
      return true;
    } else {
      return false;
    }
  }

  // プレイヤーやアイテムリストを受け取って特殊行動を行う（デフォルトは何もしない）
  public void specialAction(Player player, List<Item> inventory) {
    // 何もしない
  }

  // プレイヤーやバトル状況を受け取って特殊行動を行う（デフォルトは何もしない）
  public void performSpecialAction(Player player, CommandBattle battle, int baseDamage) {
    // デフォルトは通常攻撃のみ
    if (baseDamage < 0)
      baseDamage = 0;
    System.out.println(getName() + "の攻撃！ あなたに" + baseDamage + "ダメージ！");
    if (player.damage(baseDamage)) {
      System.out.println("あなたは倒れてしまった... ゲームオーバー！");
      System.out.println("1. タイトルに戻る  2. 終了");
      String retryInput = battle.getScanner().nextLine();
      if (retryInput.equals("1")) {
        player.setHp(player.getMaxHp());
        battle.mainMenu();
      } else {
        System.out.println("ゲームを終了します。");
        System.exit(0);
      }
    }
  }

  // Scanner取得用（特殊行動のため）
  public Scanner getScanner() {
    // CommandBattleのscannerを返すためのダミー。実際はCommandBattle側でpublic Scanner
    // scanner;にするか、getterを用意する。
    // ここではCommandBattleにpublic Scanner getScanner()を追加する前提で呼び出し。
    return null;
  }

}
