package com.project2;

import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import java.util.ArrayList;
import java.util.Random;

public class WGFunctionality {
    private String leftCoin;
    private String rightCoin;
    private Random rand;
    private int totalLives;
    private int maxRounds;
    private int currentRound;
    private boolean gameOver;
    private Text leftSideLivesT;
    private Text rightSideLivesT;
    private Text leftSideCashT;
    private Text rightSideCashT;
    private Text leftSideTM;
    private Text rightSideTM;
    private Text theWinnerIs;
    private Text leftSideDPR;
    private Text leftSideINC;
    private Text rightSideDPR;
    private Text rightSideINC;
    private Button roundNumButton;
    private Button startRound;
    private Button currentTurn;
    private Button leftEnd;
    private Button leftUpgrade;
    private ArrayList<CheckBox> leftUpgradesList;
    private ArrayList<CheckBox> rightUpgradesList;
    private PlayersStats leftPlayer;
    private PlayersStats rightPlayer;
    private String leftOrRightW;



    public WGFunctionality(int lives, int rounds, Text leftSideLives, Text rightSideLives, Text leftSideCash, Text rightSideCash, Text leftTailMeter,
                           Text rightTailMeter, Button roundNum, Button startRound, Text theWinnerIS, Button currentTurn, Button leftEnd,
                           Button leftUpgrade, ArrayList<CheckBox> leftUpgradeList, ArrayList<CheckBox> rightUpgradeList, Text leftSideDPR,
                           Text leftSideINC, Text rightSideDPR, Text rightSideINC){

        this.totalLives = lives;
        this.rand = new Random();
        this.leftPlayer = new PlayersStats(lives);
        this.rightPlayer = new PlayersStats(lives);
        this.leftSideLivesT = leftSideLives;
        this.rightSideLivesT = rightSideLives;
        this.leftSideCashT = leftSideCash;
        this.rightSideCashT = rightSideCash;
        this.leftSideTM = leftTailMeter;
        this.rightSideTM = rightTailMeter;
        this.roundNumButton = roundNum;
        this.startRound = startRound;
        this.currentTurn = currentTurn;
        this.leftEnd = leftEnd;
        this.leftUpgrade = leftUpgrade;
        this.leftUpgradesList = leftUpgradeList;
        this.rightUpgradesList = rightUpgradeList;
        this.leftSideDPR = leftSideDPR;
        this.leftSideINC = leftSideINC;
        this.rightSideDPR = rightSideDPR;
        this.rightSideINC = rightSideINC;
        this.theWinnerIs = theWinnerIS;
        this.maxRounds = rounds;
        this.currentRound = 0;
        this.gameOver = false;
    }

    public void reset(){
        leftPlayer.resetStats(this.totalLives);
        rightPlayer.resetStats(this.totalLives);
        currentRound = 0;
        this.gameOver = false;
        theWinnerIs.setOpacity(0.0);
        leftSideLivesT.setText("Lives: " + leftPlayer.getLives() +"/" + totalLives);
        leftSideCashT.setText("Money: " + leftPlayer.getCoins() + " Cents");
        leftSideDPR.setText("Dmg per Round: +" + leftPlayer.getDmgPerRound() + " dmg");
        leftSideINC.setText("Coins per Round: +" + leftPlayer.getPassiveIncome() + " coins");
        leftSideTM.setText("Tail Meter: " + leftPlayer.getTailMeter() + "/10");
        rightSideLivesT.setText("Lives: " + rightPlayer.getLives() +  "/" + this.totalLives);
        rightSideCashT.setText("Money: " + rightPlayer.getCoins() + " Cents");
        rightSideDPR.setText("Dmg per Round: +" + rightPlayer.getDmgPerRound() + " dmg");
        rightSideINC.setText("Coins per Round: +" + rightPlayer.getPassiveIncome() + " coins");
        rightSideTM.setText("Tail Meter: " + rightPlayer.getTailMeter() + "/10");
        roundNumButton.setText("Current round: " + this.currentRound + "/" + maxRounds);
        currentTurn.setText("Current turn: N/A");
        startRound.setDisable(false);
        for (CheckBox i: leftUpgradesList){
            i.setDisable(true);
            i.setSelected(false);
        }

        for (CheckBox i: rightUpgradesList){
            i.setSelected(false);
        }
        leftEnd.setDisable(true);
        leftUpgrade.setDisable(true);
    }

    public void update(Text leftText, Text rightText){
        currentRound += 1;
        this.leftCoin = leftText.getText();
        this.rightCoin = rightText.getText();
        addStats();
    }

    public void addStats(){
        int leftPlayerDeals = 0;
        int rightPlayerDeals = 0;

        if (this.leftCoin.equals("Heads")){
            leftPlayer.addCoins(50 + leftPlayer.getPassiveIncome());
            leftPlayerDeals = 15;
        } else {
            leftPlayer.addCoins(10 + leftPlayer.getPassiveIncome());
            leftPlayer.addTailMeter(1);
            leftPlayerDeals = 5;
        }

        if (this.rightCoin.equals("Heads")){
            rightPlayer.addCoins(50 + rightPlayer.getPassiveIncome());
            rightPlayerDeals = 15;
        } else {
            rightPlayer.addCoins(10 + rightPlayer.getPassiveIncome());
            rightPlayer.addTailMeter(1);
            rightPlayerDeals = 5;
        }

        rightPlayer.subtractLives(leftPlayerDeals + leftPlayer.getDmgPerRound());
        leftPlayer.subtractLives(rightPlayerDeals + rightPlayer.getDmgPerRound());

        leftSideLivesT.setText("Lives: " + leftPlayer.getLives() +"/" + this.totalLives);
        leftSideCashT.setText("Money: " + leftPlayer.getCoins() + " Cents");

        rightSideLivesT.setText("Lives: " + rightPlayer.getLives() +  "/" + this.totalLives);
        rightSideCashT.setText("Money: " + rightPlayer.getCoins() + " Cents");

        leftSideTM.setText("Tail Meter: " + leftPlayer.getTailMeter() + "/10");
        rightSideTM.setText("Tail Meter: " + rightPlayer.getTailMeter() + "/10");

        roundNumButton.setText("Current round: " + this.currentRound + "/" + maxRounds);

        leftPlayersTurn();

    }

    public void leftPlayersTurn(){
        leftEnd.setDisable(false);
        leftUpgrade.setDisable(false);
        for (CheckBox i: leftUpgradesList){
            i.setDisable(false);
        }
        currentTurn.setText("Current turn: Player1");
        checkEnd(1);
    }

    public void rightPlayersTurn(){
        for (CheckBox i: rightUpgradesList){
            i.setDisable(false);
        }
        currentTurn.setText("Current turn: Player2");
    }

    public void upgradeLeftColumn(String upgradeText){
        if (upgradeText.equals("Instant replenish (+30 hp) [125c]")){
            if (leftPlayer.getCoins() >= 125) {
                leftPlayer.subtractCoins(125);
                leftPlayer.addLives(30);
                leftSideLivesT.setText("Lives: " + leftPlayer.getLives() +"/" + this.totalLives);
                leftSideCashT.setText("Money: " + leftPlayer.getCoins() + " Cents");
            }
        } else if (upgradeText.equals("More attack (+3 dmg per Round) [75c]")){
            if (leftPlayer.getCoins() >= 75){
                leftPlayer.subtractCoins(75);
                leftPlayer.addDmgPerRound(3);
                leftSideCashT.setText("Money: " + leftPlayer.getCoins() + " Cents");
                leftSideDPR.setText("Dmg per Round: +" + leftPlayer.getDmgPerRound() + " dmg");
            }
        } else if (upgradeText.equals("Eve (+15 dpr, +35 hp, +2tm) [300c]")){
            if (leftPlayer.getCoins() >= 300){
                leftPlayer.subtractCoins(300);
                leftPlayer.addDmgPerRound(15);
                leftPlayer.addLives(35);
                leftPlayer.addTailMeter(2);
                leftSideLivesT.setText("Lives: " + leftPlayer.getLives() +"/" + this.totalLives);
                leftSideCashT.setText("Money: " + leftPlayer.getCoins() + " Cents");
                leftSideDPR.setText("Dmg per Round: +" + leftPlayer.getDmgPerRound() + " dmg");
                leftSideTM.setText("Tail Meter: " + leftPlayer.getTailMeter() + "/10");
            }

        } else if (upgradeText.equals("War (+25 dpr, +50 cpr, +50hp) [500c]")){
            if (leftPlayer.getCoins() >= 500){
                leftPlayer.subtractCoins(500);
                leftPlayer.addDmgPerRound(25);
                leftPlayer.addIncPerRound(50);
                leftPlayer.addLives(50);
                leftSideCashT.setText("Money: " + leftPlayer.getCoins() + " Cents");
                leftSideLivesT.setText("Lives: " + leftPlayer.getLives() +"/" + this.totalLives);
                leftSideDPR.setText("Dmg per Round: +" + leftPlayer.getDmgPerRound() + " dmg");
                leftSideINC.setText("Coins per Round: +" + leftPlayer.getPassiveIncome() + " coins");
            }
        } else if (upgradeText.equals("Add income (+12 cents per Round) [100c]")){
            if (leftPlayer.getCoins() >= 100){
                leftPlayer.subtractCoins(100);
                leftPlayer.addIncPerRound(12);
                leftSideCashT.setText("Money: " + leftPlayer.getCoins() + " Cents");
                leftSideINC.setText("Coins per Round: +" + leftPlayer.getPassiveIncome() + " coins");
            }
        } else if (upgradeText.equals("War (+25 dpr, +50 cpr, +50hp) [10T]")){
            if (leftPlayer.getTailMeter() == 10) {
                leftPlayer.subtractTailMeter(10);
                leftPlayer.addDmgPerRound(25);
                leftPlayer.addIncPerRound(50);
                leftPlayer.addLives(50);
                leftSideTM.setText("Tail Meter: " + leftPlayer.getTailMeter() + "/10");
                leftSideLivesT.setText("Lives: " + leftPlayer.getLives() + "/" + this.totalLives);
                leftSideDPR.setText("Dmg per Round: +" + leftPlayer.getDmgPerRound() + " dmg");
                leftSideINC.setText("Coins per Round: +" + leftPlayer.getPassiveIncome() + " coins");
            }
        } else if (upgradeText.equals("Rain (All +400 coins, you +50 hp) [4T]")){
            if (leftPlayer.getTailMeter() >= 4){
                leftPlayer.subtractTailMeter(4);
                leftPlayer.addCoins(400);
                rightPlayer.addCoins(400);
                leftPlayer.addLives(50);
                leftSideTM.setText("Tail Meter: " + leftPlayer.getTailMeter() + "/10");
                leftSideLivesT.setText("Lives: " + leftPlayer.getLives() +"/" + this.totalLives);
                leftSideCashT.setText("Money: " + leftPlayer.getCoins() + " Cents");
                rightSideCashT.setText("Money: " + rightPlayer.getCoins() + " Cents");
            }
        } else if (upgradeText.equals("Crush (All -50% hp, you +200 coins) [6T]")){
            if (leftPlayer.getTailMeter() >= 6){
                leftPlayer.subtractTailMeter(6);
                int leftHalfHpLost = leftPlayer.getLives() / 2;
                int rightHalfHpLost = rightPlayer.getLives() / 2;
                leftPlayer.subtractLives(leftHalfHpLost);
                rightPlayer.subtractLives(rightHalfHpLost);
                leftPlayer.addCoins(200);
                leftSideTM.setText("Tail Meter: " + leftPlayer.getTailMeter() + "/10");
                leftSideCashT.setText("Money: " + leftPlayer.getCoins() + " Cents");
                leftSideLivesT.setText("Lives: " + leftPlayer.getLives() +"/" + this.totalLives);
                rightSideLivesT.setText("Lives: " + rightPlayer.getLives() +"/" + this.totalLives);
            }
        } else if (upgradeText.equals("Ax (All = 0 coins, you deal 30 dmg) [5T]")){
            if (leftPlayer.getTailMeter() >= 5){
                leftPlayer.subtractTailMeter(5);
                leftPlayer.subtractCoins(leftPlayer.getCoins());
                rightPlayer.subtractCoins(rightPlayer.getCoins());
                rightPlayer.subtractLives(30);
                leftSideTM.setText("Tail Meter: " + leftPlayer.getTailMeter() + "/10");
                leftSideCashT.setText("Money: " + leftPlayer.getCoins() + " Cents");
                rightSideCashT.setText("Money: " + rightPlayer.getCoins() + " Cents");
                rightSideLivesT.setText("Lives: " + rightPlayer.getLives() +"/" + this.totalLives);
            }
        } else if (upgradeText.equals("??? (-50 hp, -200 coins, or 750 coins) [7T]")){
            if (leftPlayer.getTailMeter() >= 7){
                leftPlayer.subtractTailMeter(7);
                int choice = rand.nextInt(3);
                if (choice == 0){
                    leftPlayer.subtractLives(50);
                    leftSideLivesT.setText("Lives: " + leftPlayer.getLives() +"/" + this.totalLives);
                } else if (choice == 1){
                    leftPlayer.subtractCoins(200);
                    leftSideCashT.setText("Money: " + leftPlayer.getCoins() + " Cents");
                } else if (choice == 2){
                    leftPlayer.addCoins(500);
                    leftSideCashT.setText("Money: " + leftPlayer.getCoins() + " Cents");
                }
                leftSideTM.setText("Tail Meter: " + leftPlayer.getTailMeter() + "/10");
            }
        }

        checkEnd(2);
    }

    public void upgradeRightColumn(String upgradeText){
        if (upgradeText.equals("Instant replenish (+30 hp) [125c]")){
            if (rightPlayer.getCoins() >= 125) {
                rightPlayer.subtractCoins(125);
                rightPlayer.addLives(30);
                rightSideLivesT.setText("Lives: " + rightPlayer.getLives() +"/" + this.totalLives);
                rightSideCashT.setText("Money: " + rightPlayer.getCoins() + " Cents");
            }
        } else if (upgradeText.equals("More attack (+3 dmg per Round) [75c]")){
            if (rightPlayer.getCoins() >= 75){
                rightPlayer.subtractCoins(75);
                rightPlayer.addDmgPerRound(3);
                rightSideCashT.setText("Money: " + rightPlayer.getCoins() + " Cents");
                rightSideDPR.setText("Dmg per Round: +" + rightPlayer.getDmgPerRound() + " dmg");
            }
        } else if (upgradeText.equals("Eve (+15 dpr, +35 hp, +2tm) [300c]")){
            if (rightPlayer.getCoins() >= 300){
                rightPlayer.subtractCoins(250);
                rightPlayer.addDmgPerRound(15);
                rightPlayer.addLives(35);
                rightPlayer.addTailMeter(2);
                rightSideLivesT.setText("Lives: " + rightPlayer.getLives() +"/" + this.totalLives);
                rightSideCashT.setText("Money: " + rightPlayer.getCoins() + " Cents");
                rightSideDPR.setText("Dmg per Round: +" + rightPlayer.getDmgPerRound() + " dmg");
                rightSideTM.setText("Tail Meter: " + rightPlayer.getTailMeter() + "/10");
            }

        } else if (upgradeText.equals("War (+25 dpr, +50 cpr, +50hp) [500c]")){
            if (rightPlayer.getCoins() >= 500){
                rightPlayer.subtractCoins(500);
                rightPlayer.addDmgPerRound(25);
                rightPlayer.addIncPerRound(50);
                rightPlayer.addLives(50);
                rightSideCashT.setText("Money: " + rightPlayer.getCoins() + " Cents");
                rightSideDPR.setText("Dmg per Round: +" + rightPlayer.getDmgPerRound() + " dmg");
                rightSideINC.setText("Coins per Round: +" + rightPlayer.getPassiveIncome() + " coins");
                rightSideLivesT.setText("Lives: " + rightPlayer.getLives() +"/" + this.totalLives);
            }
        } else if (upgradeText.equals("Add income (+12 cents per Round) [100c]")){
            if (rightPlayer.getCoins() >= 100){
                rightPlayer.subtractCoins(100);
                rightPlayer.addIncPerRound(12);
                rightSideCashT.setText("Money: " + rightPlayer.getCoins() + " Cents");
                rightSideINC.setText("Coins per Round: +" + rightPlayer.getPassiveIncome() + " coins");
            }
        } else if (upgradeText.equals("War (+25 dpr, +50 cpr, +50hp) [10T]")){
            if (rightPlayer.getTailMeter() == 10){
                rightPlayer.subtractTailMeter(10);
                rightPlayer.addDmgPerRound(25);
                rightPlayer.addIncPerRound(50);
                rightPlayer.addLives(50);
                rightSideTM.setText("Tail Meter: " + rightPlayer.getTailMeter() + "/10");
                rightSideDPR.setText("Dmg per Round: +" + rightPlayer.getDmgPerRound() + " dmg");
                rightSideINC.setText("Coins per Round: +" + rightPlayer.getPassiveIncome() + " coins");
                rightSideLivesT.setText("Lives: " + rightPlayer.getLives() +"/" + this.totalLives);
            }
        } else if (upgradeText.equals("Rain (All +400 coins, you +50 hp) [4T]")){
            if (rightPlayer.getTailMeter() >= 4){
                rightPlayer.subtractTailMeter(4);
                rightPlayer.addCoins(400);
                leftPlayer.addCoins(400);
                rightPlayer.addLives(50);
                rightSideTM.setText("Tail Meter: " + rightPlayer.getTailMeter() + "/10");
                rightSideLivesT.setText("Lives: " + rightPlayer.getLives() +"/" + this.totalLives);
                rightSideCashT.setText("Money: " + rightPlayer.getCoins() + " Cents");
                leftSideCashT.setText("Money: " + leftPlayer.getCoins() + " Cents");
            }
        } else if (upgradeText.equals("Crush (All -50% hp, you +200 coins) [6T]")){
            if (rightPlayer.getTailMeter() >= 6){
                rightPlayer.subtractTailMeter(6);
                int leftHalfHpLost = leftPlayer.getLives() / 2;
                int rightHalfHpLost = rightPlayer.getLives() / 2;
                leftPlayer.subtractLives(leftHalfHpLost);
                rightPlayer.subtractLives(rightHalfHpLost);
                rightPlayer.addCoins(200);
                rightSideTM.setText("Tail Meter: " + rightPlayer.getTailMeter() + "/10");
                rightSideCashT.setText("Money: " + rightPlayer.getCoins() + " Cents");
                leftSideLivesT.setText("Lives: " + leftPlayer.getLives() +"/" + this.totalLives);
                rightSideLivesT.setText("Lives: " + rightPlayer.getLives() +"/" + this.totalLives);
            }
        } else if (upgradeText.equals("Ax (All = 0 coins, you deal 30 dmg) [5T]")){
            if (rightPlayer.getTailMeter() >= 5){
                rightPlayer.subtractTailMeter(5);
                rightPlayer.subtractCoins(rightPlayer.getCoins());
                leftPlayer.subtractCoins(leftPlayer.getCoins());
                leftPlayer.subtractLives(30);
                rightSideTM.setText("Tail Meter: " + rightPlayer.getTailMeter() + "/10");
                rightSideCashT.setText("Money: " + rightPlayer.getCoins() + " Cents");
                leftSideCashT.setText("Money: " + leftPlayer.getCoins() + " Cents");
                leftSideLivesT.setText("Lives: " + leftPlayer.getLives() +"/" + this.totalLives);
            }
        } else if (upgradeText.equals("??? (-50 hp, -200 coins, or 750 coins) [7T]")){
            if (rightPlayer.getTailMeter() >= 7){
                rightPlayer.subtractTailMeter(7);
                int choice = rand.nextInt(3);
                if (choice == 0){
                    rightPlayer.subtractLives(50);
                    rightSideLivesT.setText("Lives: " + rightPlayer.getLives() +"/" + this.totalLives);
                } else if (choice == 1){
                    rightPlayer.subtractCoins(200);
                    rightSideCashT.setText("Money: " + rightPlayer.getCoins() + " Cents");
                } else if (choice == 2){
                    rightPlayer.addCoins(500);
                    rightSideCashT.setText("Money: " + rightPlayer.getCoins() + " Cents");
                }
                rightSideTM.setText("Tail Meter: " + rightPlayer.getTailMeter() + "/10");
            }
        }

        checkEnd(2);
    }

    public void checkEnd(int num){
        if (currentRound == maxRounds && num != 2){
            if (leftPlayer.getLives() > rightPlayer.getLives()){
                leftOrRightW = "Left";
            } else if (leftPlayer.getLives() < rightPlayer.getLives()){
                leftOrRightW = "Right";
            } else {
                leftOrRightW = "Tie";
            }
            gameOver = true;
        } else if(leftPlayer.getLives() == 0 && rightPlayer.getLives() == 0){
            leftOrRightW = "Tie";
            gameOver = true;
        } else if (leftPlayer.getLives() <= 0){
            leftOrRightW = "Right";
            gameOver = true;
        } else if (rightPlayer.getLives() <= 0){
            leftOrRightW = "Left";
            gameOver = true;
        }

        if (gameOver == true){
            startRound.setDisable(true);
            if (leftOrRightW.equals("Left")){
                theWinnerIs.setText("Player1 WINS!");
                theWinnerIs.setOpacity(1.0);
            } else if (leftOrRightW.equals("Right")) {
                theWinnerIs.setText("Player2 WINS!");
                theWinnerIs.setOpacity(1.0);
            } else {
                theWinnerIs.setText("Nobody WINS!");
                theWinnerIs.setOpacity(1.0);
            }

            leftEnd.setDisable(true);
            leftUpgrade.setDisable(true);
            for (CheckBox i: leftUpgradesList){
                i.setDisable(true);
            }
        }
    }
}
