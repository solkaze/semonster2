package org.example;

import java.util.*;

public class CommandBattle {
    private Player player;
    private Scanner scanner;
    private List<Item> inventory;
    private Item equippedWeapon;
    private Item equippedArmor;

    public CommandBattle() {
        this.player = new Player(100, "勇者", 10);
        this.scanner = new Scanner(System.in);
        this.inventory = new ArrayList<>();
        // 初期装備
        this.equippedWeapon = new Sword("木の剣", 0, "普通の剣", 2);
        this.equippedArmor = new Armor("布の服", 0, "普通の服", 5, 1);
        inventory.add(equippedWeapon);
        inventory.add(equippedArmor);
    }

    public void mainMenu() {
        while (true) {
            System.out.println("\n=== メニュー ===");
            System.out.println("1. 冒険に出る");
            System.out.println("2. 装備・アイテム");
            System.out.println("3. 終了");
            System.out.print("選択: ");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    startBattle();
                    break;
                case "2":
                    equipmentMenu();
                    break;
                case "3":
                    System.out.println("ゲームを終了します。");
                    return;
                default:
                    System.out.println("無効なコマンドです。");
            }
        }
    }

    private void startBattle() {
        Character enemy = randomEnemy();
        System.out.println("\n=== バトル開始！ ===");
        System.out.println("敵: " + enemy.getName() + " (HP: " + enemy.getHp() + ")");
        while (player.getHp() > 0 && enemy.getHp() > 0) {
            System.out.println("\nあなたのHP: " + player.getHp() + "  " + enemy.getName() + "のHP: " + enemy.getHp());
            System.out.println("コマンド: 1.攻撃 2.回復 3.スキル 4.逃げる");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    int damage = player.attack() + getWeaponPower();
                    System.out.println("あなたの攻撃！ " + enemy.getName() + "に" + damage + "ダメージ！");
                    if (enemy.damage(damage)) {
                        System.out.println(enemy.getName() + "を倒した！");
                        reward();
                        return;
                    }
                    break;
                case "2":
                    Potion potion = new Potion("回復薬", 0, "HPを20回復する薬", 20);
                    inventory.add(potion);
                    if (player.useItem(potion)) {
                        System.out.println("回復薬を使った！ HPが20回復した。");
                        inventory.remove(potion);
                    } else {
                        System.out.println("回復薬が使えませんでした。");
                    }
                    break;
                case "3":
                    int skillDamage = (player.attack() + getWeaponPower()) * 2;
                    System.out.println("必殺技発動！ " + enemy.getName() + "に" + skillDamage + "ダメージ！（クールダウン1ターン）");
                    if (enemy.damage(skillDamage)) {
                        System.out.println(enemy.getName() + "を倒した！");
                        reward();
                        return;
                    }
                    System.out.println("あなたは疲れて次のターン行動できない...");
                    for (int i = 0; i < 2; i++) {
                        if (enemy.getHp() > 0) {
                            int enemyDamage = enemy.attack() - getArmorDefense();
                            if (enemyDamage < 0)
                                enemyDamage = 0;
                            System.out.println(enemy.getName() + "の攻撃！ あなたに" + enemyDamage + "ダメージ！");
                            if (player.damage(enemyDamage)) {
                                System.out.println("あなたは倒れてしまった...");
                                return;
                            }
                        }
                    }
                    continue;
                case "4":
                    System.out.println("逃げた！");
                    return;
                default:
                    System.out.println("無効なコマンドです。");
            }
            // 敵のターン
            if (enemy.getHp() > 0) {
                int enemyDamage = enemy.attack() - getArmorDefense();
                if (enemyDamage < 0)
                    enemyDamage = 0;
                System.out.println(enemy.getName() + "の攻撃！ あなたに" + enemyDamage + "ダメージ！");
                if (player.damage(enemyDamage)) {
                    System.out.println("あなたは倒れてしまった...");
                    return;
                }
            }
        }
        System.out.println("=== バトル終了 ===");
    }

    private void equipmentMenu() {
        while (true) {
            System.out.println("\n=== 装備・アイテム ===");
            System.out.println("1. 装備変更");
            System.out.println("2. 所持品一覧");
            System.out.println("3. 戻る");
            System.out.print("選択: ");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    changeEquipment();
                    break;
                case "2":
                    showInventory();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("無効なコマンドです。");
            }
        }
    }

    private void changeEquipment() {
        System.out.println("\n-- 武器 --");
        int idx = 1;
        for (Item item : inventory) {
            if (item instanceof Sword || item instanceof MagicWand || item instanceof Bow) {
                System.out.println(idx + ". " + item.getName());
            }
            idx++;
        }
        System.out.print("装備したい武器の番号(0でスキップ): ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice > 0 && choice <= inventory.size()) {
                Item item = inventory.get(choice - 1);
                if (item instanceof Sword || item instanceof MagicWand || item instanceof Bow) {
                    equippedWeapon = item;
                    System.out.println(item.getName() + "を装備した！");
                } else {
                    System.out.println("武器ではありません。");
                }
            }
        } catch (Exception e) {
            System.out.println("入力エラー");
        }
        System.out.println("-- 防具 --");
        idx = 1;
        for (Item item : inventory) {
            if (item instanceof Armor || item instanceof Shield) {
                System.out.println(idx + ". " + item.getName());
            }
            idx++;
        }
        System.out.print("装備したい防具の番号(0でスキップ): ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice > 0 && choice <= inventory.size()) {
                Item item = inventory.get(choice - 1);
                if (item instanceof Armor || item instanceof Shield) {
                    equippedArmor = item;
                    System.out.println(item.getName() + "を装備した！");
                } else {
                    System.out.println("防具ではありません。");
                }
            }
        } catch (Exception e) {
            System.out.println("入力エラー");
        }
    }

    private void showInventory() {
        System.out.println("\n-- 所持品一覧 --");
        for (Item item : inventory) {
            System.out.println(item.getName() + ": " + item.getDescription());
        }
        System.out.println("現在の武器: " + (equippedWeapon != null ? equippedWeapon.getName() : "なし"));
        System.out.println("現在の防具: " + (equippedArmor != null ? equippedArmor.getName() : "なし"));
    }

    private int getWeaponPower() {
        if (equippedWeapon instanceof Sword) {
            return ((Sword) equippedWeapon).getAttackPower();
        } else if (equippedWeapon instanceof MagicWand) {
            return ((MagicWand) equippedWeapon).getMagicPower();
        } else if (equippedWeapon instanceof Bow) {
            return ((Bow) equippedWeapon).getAccuracy();
        }
        return 0;
    }

    private int getArmorDefense() {
        if (equippedArmor instanceof Armor) {
            return ((Armor) equippedArmor).getDefense();
        } else if (equippedArmor instanceof Shield) {
            return ((Shield) equippedArmor).getDefense();
        }
        return 0;
    }

    private Character randomEnemy() {
        Random rand = new Random();
        int n = rand.nextInt(6);
        switch (n) {
            case 0:
                return new Slime();
            case 1:
                return new Goblin();
            case 2:
                return new Dragon();
            case 3:
                return new Skeleton();
            case 4:
                return new Wizard();
            case 5:
                return new Orc();
            default:
                return new Slime();
        }
    }

    private void reward() {
        // ランダムで装備やアイテムを入手
        Random rand = new Random();
        int n = rand.nextInt(5);
        Item item = null;
        switch (n) {
            case 0:
                item = new Sword("鉄の剣", 100, "攻撃力+5", 5);
                break;
            case 1:
                item = new Shield("木の盾", 80, "防御力+3", 3);
                break;
            case 2:
                item = new MagicWand("魔法の杖", 120, "魔力+7", 7);
                break;
            case 3:
                item = new Armor("革の鎧", 90, "HP+10, 防御力+2", 10, 2);
                break;
            case 4:
                item = new Potion("回復薬", 0, "HPを20回復する薬", 20);
                break;
        }
        if (item != null) {
            inventory.add(item);
            System.out.println("報酬: " + item.getName() + " を手に入れた！");
        }
    }
}
