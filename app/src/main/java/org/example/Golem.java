package org.example;

import java.util.Random;


public class Golem extends Character {

    public Golem() {
        super(80, 80, "ゴーレム", 10);
    }

    public int getExpReward() {
        return 30;
    }

    public int getGoldReward() {
        return 40;
    }

    @Override
    public void performSpecialAction(Player player, CommandBattle battle, int baseDamage) {
        // 乱数で0か1を生成し、行動を分岐させる
        if (rand.nextInt(2) == 0) {
            // 攻撃が成功した場合
            int powerfulBlow = baseDamage * 3; // 通常の3倍の威力のダメージ
            System.out.println("ゴーレムの痛恨の一撃！ あなたに" + powerfulBlow + "ダメージ！");
            
            // プレイヤーにダメージを与え、もし倒れたらゲームオーバー処理を行う
            if (player.damage(powerfulBlow)) {
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
        } else {
            // 攻撃が失敗した場合
            System.out.println("ゴーレムの攻撃は外れた！");
        }
    }
}