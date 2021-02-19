package com.project2;

public class PlayersStats {
    private int lives;
    private int coins;
    private int tailMeter;
    private int passiveIncome;
    private int dmgPerRound;
    private int maxLives;

    public PlayersStats(int lives){
        this.lives = lives;
        this.maxLives = lives;
        this.coins = 0;
        this.tailMeter = 0;
        this.passiveIncome = 0;
        this.dmgPerRound = 0;
    }

    public void addCoins(int amount){
        this.coins += amount;
    }

    public void addTailMeter(int amount){
        this.tailMeter += amount;
        if (this.tailMeter == 11){
            this.tailMeter = 10;
        }
    }

    public void addLives(int amount){
        this.lives += amount;
        if (this.lives > maxLives){
            this.lives = maxLives;
        }
    }

    public void addDmgPerRound(int amount){
        this.dmgPerRound += amount;
    }

    public void addIncPerRound(int amount){
        this.passiveIncome += amount;
    }

    public void subtractLives(int amount){
        this.lives -= amount;
        if (this.lives < 0) {
            this.lives = 0;
        }
    }

    public void subtractCoins(int amount){
        this.coins -= amount;
        if (this.coins < 0) {
            this.coins = 0;
        }
    }

    public void subtractTailMeter(int amount){
        this.tailMeter -= amount;
    }

    public int getCoins(){
        return this.coins;
    }

    public int getTailMeter(){
        return this.tailMeter;
    }

    public int getDmgPerRound(){
        return this.dmgPerRound;
    }

    public int getLives(){
        return this.lives;
    }

    public int getPassiveIncome(){
        return this.passiveIncome;
    }

    public void resetStats(int maxLives){
        this.lives = maxLives;
        this.coins = 0;
        this.tailMeter = 0;
        this.dmgPerRound = 0;
        this.passiveIncome = 0;
    }
}
