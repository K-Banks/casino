package models;

public class Player {
    private int money = 100;
    private String name;
    private int currentBet = 0;
    private boolean hasBet = false;

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }

    public boolean isHasBet() {
        return hasBet;
    }

    public void setHasBet(boolean hasBet) {
        this.hasBet = hasBet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void changeMoney(int bet) {
        money = money + bet;
    }
}
