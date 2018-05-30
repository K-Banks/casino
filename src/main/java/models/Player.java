package models;

public class Player {
    private int money = 100;
    private String name;

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void changeMoney(int bet) {
        money = money + bet;
    }
}
