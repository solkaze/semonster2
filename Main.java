public class Main {
    public static void main(String[] args) {
        Slime slime1 = new Slime();

        Player yusya1 = new Player(100, "勇者", 10);

        if (slime1.damage(yusya1.attack())) {
            System.out.println("スライムを倒した！");
        } else {
            System.out.println("残りのスライムHP:" + slime1.getHp());
        }

    }
}
