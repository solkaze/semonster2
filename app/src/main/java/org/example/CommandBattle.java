package org.example;

import java.util.*;

public class CommandBattle {
    private Player player;
    private Scanner scanner;
    private List<ItemStack> inventory;
    private Item equippedWeapon;
    private Item equippedArmor;

    // 状態異常・バフ管理
    private boolean isDefending = false;
    private boolean isBurned = false;
    private int confuseTurns = 0;
    // 攻撃力バフ（チャージ）管理用
    private boolean charged = false;

    public CommandBattle() {
        this.player = new Player(30, 30, "勇者", 5);
        this.scanner = new Scanner(System.in);
        this.inventory = new ArrayList<>();
        this.equippedWeapon = new Sword("木の剣", 0, "普通の剣", 2);
        this.equippedArmor = new Armor("布の服", 0, "普通の服", 5, 1);
        addItemToInventory(equippedWeapon, 1);
        addItemToInventory(equippedArmor, 1);
    }

    public void mainMenu() {
        while (true) {
            System.out.println("\n=== メニュー ===");
            System.out.println("レベル: " + player.getLevel() + "  経験値: " + player.getExp() + "/" + player.getNextExp());
            System.out.println("HP: " + player.getHp() + "/" + player.getMaxHp());
            System.out.println("1. 冒険に出る");
            System.out.println("2. 装備・アイテム");
            System.out.println("3. ショップ");
            System.out.println("4. 終了");
            System.out.println("所持金: " + player.getMoney() + " G");
            System.out.print("選択: ");
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                switch (input) {
                    case "1":
                        startBattle();
                        break;
                    case "2":
                        equipmentMenu();
                        break;
                    case "3":
                        shopMenu();
                        break;
                    case "4":
                        System.out.println("ゲームを終了します。");
                        return;
                    default:
                        System.out.println("無効なコマンドです。");
                }
            } else {
                System.out.println("入力がありません。プログラムを終了します。");
                return;
            }
        }
    }

    private void startBattle() {
        Character enemy = randomEnemyScaled();
        int enemyLevel = enemy.getLevel();
        System.out.println("\n=== バトル開始！ ===");
        System.out.println("あなた: Lv." + player.getLevel() + " HP: " + player.getHp() + "/" + player.getMaxHp()
                + " EXP: " + player.getExp() + "/" + player.getNextExp());
        System.out.println(
                "敵: " + enemy.getName() + " Lv." + enemyLevel + " HP: " + enemy.getHp() + "/" + enemy.getMaxHp());
        isDefending = false;
        isBurned = false;
        confuseTurns = 0;
        charged = false;
        while (player.getHp() > 0 && enemy.getHp() > 0) {
            // 状態異常・バフ表示
            String status = "";
            if (isDefending)
                status += "[防御中] ";
            if (isBurned)
                status += "[やけど] ";
            if (confuseTurns > 0)
                status += "[混乱] ";
            if (charged)
                status += "[攻撃力UP(1回)] ";
            System.out.println(
                    "\nあなたのHP: " + player.getHp() + "  " + enemy.getName() + "のHP: " + enemy.getHp() + " " + status);
            System.out.println("コマンド: 1.攻撃 2.回復 3.スキル 4.逃げる 5.防御 6.アイテム 7.装備変更");
            System.out.println("[Lv." + player.getLevel() + "] EXP: " + player.getExp() + "/" + player.getNextExp());
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    if (confuseTurns > 0 && new Random().nextInt(2) == 0) {
                        System.out.println("混乱して自分を攻撃してしまった！");
                        player.damage(player.attack());
                        confuseTurns--;
                        break;
                    }
                    int baseAttack = player.attack() + getWeaponPower();
                    int damage = charged ? baseAttack * 2 : baseAttack;
                    if (charged) {
                        System.out.println("チャージ攻撃！攻撃力2倍！");
                        charged = false;
                    }
                    if (enemy instanceof Skeleton)
                        damage /= 2; // スケルトンは物理耐性
                    System.out.println("あなたの攻撃！ " + enemy.getName() + "に" + damage + "ダメージ！");
                    if (enemy.damage(damage)) {
                        System.out.println(enemy.getName() + "を倒した！");
                        reward(enemy);
                        return;
                    }
                    break;
                case "2":
                    useBattlePotion();
                    break;
                case "3":
                    if (confuseTurns > 0) {
                        System.out.println("混乱してスキルが使えない！（残り" + confuseTurns + "ターン）");
                        confuseTurns--;
                        break;
                    }
                    System.out.println("スキルを選択してください: 1. 必殺斬り(2倍攻撃) 2. チャージ(次ターン攻撃力UP) 0. キャンセル");
                    String skillInput = scanner.nextLine();
                    if ("2".equals(skillInput)) {
                        System.out.println("力を溜めた！次の攻撃力が2倍になる");
                        charged = true;
                        break;
                    } else if ("0".equals(skillInput)) {
                        System.out.println("スキル使用をキャンセルしました。");
                        break;
                    }
                    int skillDamage = (player.attack() + getWeaponPower()) * 2;
                    System.out.println("必殺技発動！ " + enemy.getName() + "に" + skillDamage + "ダメージ！（クールダウン1ターン）");
                    if (enemy.damage(skillDamage)) {
                        System.out.println(enemy.getName() + "を倒した！");
                        reward(enemy);
                        return;
                    }
                    System.out.println("あなたは疲れて次のターン行動できない...（スキルの反動）");
                    for (int i = 0; i < 2; i++) {
                        if (enemy.getHp() > 0) {
                            int enemyDamage = enemy.attack() - getArmorDefense();
                            if (isDefending)
                                enemyDamage /= 2;
                            if (enemyDamage < 0)
                                enemyDamage = 0;
                            System.out.println(enemy.getName() + "の攻撃！ あなたに" + enemyDamage + "ダメージ！");
                            if (player.damage(enemyDamage)) {
                                System.out.println("あなたは倒れてしまった... ゲームオーバー！");
                                System.out.println("1. タイトルに戻る  2. 終了");
                                String retryInput = scanner.nextLine();
                                if (retryInput.equals("1")) {
                                    player.setHp(player.getMaxHp());
                                    mainMenu();
                                } else {
                                    System.out.println("ゲームを終了します。");
                                    System.exit(0);
                                }
                            }
                        }
                    }
                    break;
                case "4":
                    System.out.println("逃げた！");
                    return;
                case "5":
                    isDefending = true;
                    System.out.println("防御の体勢をとった！次の敵の攻撃ダメージ半減");
                    break;
                case "6":
                    // アイテム使用（回復薬のみ実装例）
                    useBattlePotion();
                    break;
                case "7":
                    changeEquipment();
                    break;
                default:
                    System.out.println("無効なコマンドです。");
            }
            // 敵のターン
            if (enemy.getHp() > 0) {
                int enemyDamage = enemy.attack() - getArmorDefense();
                if (isDefending)
                    enemyDamage /= 2;
                // 特殊行動・特殊表示はキャラクラス側に委譲
                enemy.performSpecialAction(player, this, enemyDamage);
                if (isBurned) {
                    player.damage(2);
                    System.out.println("やけどでHPが2減った！");
                }
                isDefending = false;
            }
        }
        System.out.println("=== バトル終了 ===");
    }

    // プレイヤーレベルに応じて敵をスケーリング
    private Character randomEnemyScaled() {
        Character base = randomEnemy();
        int plv = player.getLevel();
        int elv = Math.max(1, plv + new Random().nextInt(2) - 1); // ±1レベル幅
        base.setLevel(elv);
        int hp = base.getMaxHp() + (elv - 1) * 5;
        base.setHp(hp);
        base.setMaxHp(hp);
        // 攻撃力はpowerフィールドがprivateのためattack()で近似
        return base;
    }

    private void equipmentMenu() {
        while (true) {
            System.out.println("\n=== 装備・アイテム ===");
            System.out.println("1. 装備変更");
            System.out.println("2. 所持品一覧");
            System.out.println("3. 売却");
            System.out.println("4. 戻る");
            System.out.println("所持金: " + player.getMoney() + " G");
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
                    sellItem();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("無効なコマンドです。");
            }
        }
    }

    private void changeEquipment() {
        // 武器リスト
        List<ItemStack> weapons = new ArrayList<>();
        for (ItemStack stack : inventory) {
            if (stack.item instanceof Sword || stack.item instanceof MagicWand || stack.item instanceof Bow) {
                weapons.add(stack);
            }
        }
        System.out.println("\n-- 武器 --");
        for (int i = 0; i < weapons.size(); i++) {
            System.out.println((i + 1) + ". " + weapons.get(i).item.getName() + " ×" + weapons.get(i).count);
        }
        System.out.print("装備したい武器の番号(0でスキップ): ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice > 0 && choice <= weapons.size()) {
                equippedWeapon = weapons.get(choice - 1).item;
                System.out.println(equippedWeapon.getName() + "を装備した！");
            }
        } catch (Exception e) {
            System.out.println("入力エラー");
        }
        // 防具リスト
        List<ItemStack> armors = new ArrayList<>();
        for (ItemStack stack : inventory) {
            if (stack.item instanceof Armor || stack.item instanceof Shield) {
                armors.add(stack);
            }
        }
        System.out.println("-- 防具 --");
        for (int i = 0; i < armors.size(); i++) {
            System.out.println((i + 1) + ". " + armors.get(i).item.getName() + " ×" + armors.get(i).count);
        }
        System.out.print("装備したい防具の番号(0でスキップ): ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice > 0 && choice <= armors.size()) {
                equippedArmor = armors.get(choice - 1).item;
                System.out.println(equippedArmor.getName() + "を装備した！");
            }
        } catch (Exception e) {
            System.out.println("入力エラー");
        }
    }

    private void showInventory() {
        System.out.println("\n-- 所持品一覧 --");
        for (ItemStack stack : inventory) {
            System.out.println(stack.item.getName() + ": " + stack.item.getDescription() + " ×" + stack.count);
        }
        System.out.println("現在の武器: " + (equippedWeapon != null ? equippedWeapon.getName() : "なし"));
        System.out.println("現在の防具: " + (equippedArmor != null ? equippedArmor.getName() : "なし"));
    }

    private void sellItem() {
        System.out.println("\n-- 売却可能なアイテム一覧 --");
        int idx = 1;
        for (ItemStack stack : inventory) {
            System.out.println(idx + ". " + stack.item.getName() + " ×" + stack.count + " (売却価格: "
                    + stack.item.getSellValue() + " G)");
            idx++;
        }
        System.out.print("売却したいアイテムの番号(0でキャンセル): ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice > 0 && choice <= inventory.size()) {
                ItemStack stack = inventory.get(choice - 1);
                player.addMoney(stack.item.getSellValue());
                stack.count--;
                System.out.println(stack.item.getName() + "を売却し" + stack.item.getSellValue() + "Gを得た！");
                if (stack.count == 0)
                    inventory.remove(stack);
            }
        } catch (Exception e) {
            System.out.println("入力エラー");
        }
    }

    private void addItemToInventory(Item item, int count) {
        for (ItemStack stack : inventory) {
            if (stack.item.getClass().equals(item.getClass()) && stack.item.getName().equals(item.getName())) {
                stack.count += count;
                return;
            }
        }
        inventory.add(new ItemStack(item, count));
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
        int n;
        if (player.getLevel() <= 2) {
            n = rand.nextInt(2); // 0:Slime, 1:Goblin
        } else if (player.getLevel() <= 4) {
            n = rand.nextInt(4); // 0:Slime, 1:Goblin, 2:Skeleton, 3:Orc
        } else {
            n = rand.nextInt(6); // 全部
        }
        switch (n) {
            case 0:
                return new Slime();
            case 1:
                return new Goblin();
            case 2:
                return new Skeleton();
            case 3:
                return new Orc();
            case 4:
                return new Dragon();
            case 5:
                return new Wizard();
            default:
                return new Slime();
        }
    }

    private void reward(Character enemy) {
        // 敵ごとの経験値・ゴールド報酬
        int expGain = 0;
        int goldGain = 0;
        if (enemy instanceof Slime) {
            expGain = ((Slime) enemy).getExpReward();
            goldGain = ((Slime) enemy).getGoldReward();
        } else if (enemy instanceof Goblin) {
            expGain = ((Goblin) enemy).getExpReward();
            goldGain = ((Goblin) enemy).getGoldReward();
        } else if (enemy instanceof Skeleton) {
            expGain = ((Skeleton) enemy).getExpReward();
            goldGain = ((Skeleton) enemy).getGoldReward();
        } else if (enemy instanceof Orc) {
            expGain = ((Orc) enemy).getExpReward();
            goldGain = ((Orc) enemy).getGoldReward();
        } else if (enemy instanceof Dragon) {
            expGain = ((Dragon) enemy).getExpReward();
            goldGain = ((Dragon) enemy).getGoldReward();
        } else if (enemy instanceof Wizard) {
            expGain = ((Wizard) enemy).getExpReward();
            goldGain = ((Wizard) enemy).getGoldReward();
        } else {
            expGain = 5 + new java.util.Random().nextInt(6) + player.getLevel();
            goldGain = 10 + new java.util.Random().nextInt(10);
        }
        player.addMoney(goldGain);
        System.out.println("ゴールドを" + goldGain + "G獲得！");
        // ランダムで装備やアイテムを入手
        java.util.Random rand = new java.util.Random();
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
            addItemToInventory(item, 1);
            System.out.println("報酬: " + item.getName() + " を手に入れた！");
        }
        // バトル勝利時にHP最大値の20%自動回復
        int heal = Math.max(1, player.getMaxHp() / 5);
        player.setHp(player.getHp() + heal);
        System.out.println("バトル勝利！HPが" + heal + "回復した（最大値の20%）");
        // 経験値報酬
        System.out.println("経験値を" + expGain + "獲得！");
        int beforeLevel = player.getLevel();
        player.gainExp(expGain);
        if (player.getLevel() > beforeLevel) {
            // レベルアップ演出はCharacter.levelUp()で表示
        } else {
            int toNext = player.getNextExp() - player.getExp();
            System.out.println("次のレベルまであと" + toNext + "経験値");
        }
    }

    private void shopMenu() {
        while (true) {
            System.out.println("\n=== ショップ ===");
            System.out.println("1. 回復薬を買う (50G)");
            System.out.println("2. 戻る");
            System.out.println("所持金: " + player.getMoney() + " G");
            System.out.print("選択: ");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    if (player.getMoney() >= 50) {
                        addItemToInventory(new Potion("回復薬", 0, "HPを20回復する薬", 20), 1);
                        player.spendMoney(50);
                        System.out.println("回復薬を1個購入した！");
                    } else {
                        System.out.println("お金が足りません。");
                    }
                    break;
                case "2":
                    return;
                default:
                    System.out.println("無効なコマンドです。");
            }
        }
    }

    // バトル中の回復薬使用処理
    private void useBattlePotion() {
        // 回復薬を所持しているか確認
        for (ItemStack stack : inventory) {
            if (stack.item instanceof Potion && stack.count > 0) {
                Potion potion = (Potion) stack.item;
                int heal = potion.getHealAmount();
                int before = player.getHp();
                player.setHp(Math.min(player.getHp() + heal, player.getMaxHp()));
                stack.count--;
                System.out.println("回復薬を使った！HPが" + (player.getHp() - before) + "回復（" + player.getHp() + "/"
                        + player.getMaxHp() + ")");
                if (stack.count == 0)
                    inventory.remove(stack);
                return;
            }
        }
        System.out.println("回復薬を持っていません。");
    }

    // Goblinの特殊行動からアクセスできるようにpublicにする
    public List<Item> getInventoryItems() {
        List<Item> items = new ArrayList<>();
        for (ItemStack stack : inventory) {
            items.add(stack.item);
        }
        return items;
    }

    public Player getPlayer() {
        return player;
    }

    // Scanner取得用
    public Scanner getScanner() {
        return scanner;
    }

    // やけど状態の制御用
    public void setBurned(boolean burned) {
        this.isBurned = burned;
    }

    // 混乱状態の制御用
    public void setConfuseTurns(int turns) {
        this.confuseTurns = turns;
    }

    // 防御状態の制御用
    public boolean isDefending() {
        return isDefending;
    }

    // 混乱状態の取得
    public int getConfuseTurns() {
        return confuseTurns;
    }

    // やけど状態の取得
    public boolean isBurned() {
        return isBurned;
    }
}
