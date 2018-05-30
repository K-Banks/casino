package models;

public class Player {
    private int money = 100;
    private String name;
    public Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void changeMoney(int bet) {
        money = money + bet;
    }
}
