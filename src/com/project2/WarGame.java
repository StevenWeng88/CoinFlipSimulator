package com.project2;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.CheckBox;
import javafx.geometry.Insets;
import javafx.util.Duration;
import java.util.ArrayList;

import java.util.Random;

public class WarGame {
    private Stage window;
    private Scene menuView;
    private int lives;
    private int rounds;

    public WarGame(Stage window, Scene menuView, int lives, int rounds){
        this.window = window;
        this.menuView = menuView;
        this.lives = lives;
        this.rounds = rounds;
    }

    public Pane getWarPane(){
        Pane warGame = new Pane();
        warGame.setPrefSize(1050, 700);

////      Creating all the Text and Buttons
        Text gameText = getTopText("Game: War", 50, 50, 101, 194, 40);
        Text livesText = getTopText("Starting lives: " + String.valueOf(lives), 375, 50, 40, 174, 194);
        Text roundsText = getTopText("Total rounds: " + String.valueOf(rounds), 750, 50, 76, 40, 194);
        Line topLine = getTopLine(0, 90, 1050, 90);
        Line midLine = getTopLine(525, 90, 525, 640);
        Line botLine = getTopLine(0, 640, 1050, 640);
        Button resetButton = getTopButton("Reset", 50, 652);
        Button nextRoundButton = getTopButton("Start", 485, 575);
        Button menuButton = getTopButton("Menu", 920, 652);
        Button roundNumButton = getTopButton("Current round: 0" + "/" + this.rounds, 240, 652);
        Button currentTurnButton = getTopButton("Current turn: N/A", 570, 652);
        Text leftPlayer = getPlayerName("Player 1", 325, 150, 128, 0 ,0);
        Text rightPlayer = getPlayerName("Player 2", 585, 150, 218, 165, 32);
        Text leftTailMeter = getTailMeter(30, 610);
        Text rightTailMeter = getTailMeter(815, 610);
        Text leftYouGot = youGotText(320, 265, 128, 0 ,0);
        Text rightYouGot = youGotText(580, 265, 218, 165, 32);
        Text youWIN = youWIN();

////         Creating the coins with labels on them through StackPane
        Circle leftCoin = getCoin(128,0, 0, 0);
        Text leftCoinText = getCoinText("Heads", 0, 0, 0);
        StackPane leftCoinS = new StackPane();
        leftCoinS.getChildren().addAll(leftCoin, leftCoinText);
        leftCoinS.setLayoutX(290);
        leftCoinS.setLayoutY(360);

        Circle rightCoin = getCoin(218, 165, 32, 1);
        Text rightCoinText = getCoinText("Heads", 225, 225, 225);
        StackPane rightCoinS = new StackPane();
        rightCoinS.getChildren().addAll(rightCoin, rightCoinText);
        rightCoinS.setLayoutX(555);
        rightCoinS.setLayoutY(360);


////        Creating the side displays for both sides
//          Creating the display for the left side
        Rectangle leftSideDisplay = makeSideDisplay(-90, 215, 450, 250);
        Text leftSideLives = getStatsText("Lives: " + this.lives +"/" + this.lives, 20, 140, 40, 174, 194);
        Text leftSideCash = getStatsText("Money: 0 Cents", 20, 165, 0, 128, 0);
        Text leftSideUpgrades = getStatsText("Cash Upgrades", 20, 200, 0, 0, 139);
        Text leftSideAbilities = getStatsText("Tail Meter Abilities", 20, 330, 225, 225, 102);
        Text leftSideDPR = getStatsText("Dmg per Round: +0 dmg", 20, 465, 128, 0, 0);
        Text leftSideINC = getStatsText("Coins per Round: +0 coins", 20, 495, 0, 128, 0);

//          Creating the checkboxes for the cash upgrades left side
        VBox allLeftUpgrades = new VBox();
        CheckBox leftUpgradeMOAT = getUpgrade("More attack (+3 dmg per Round) [75c]");
        CheckBox leftUpgradePASI = getUpgrade("Add income (+12 cents per Round) [100c]");
        CheckBox leftUpgradeINHP = getUpgrade("Instant replenish (+30 hp) [125c]");
        CheckBox leftUpgradeMOMH = getUpgrade("Eve (+15 dpr, +35 hp, +2tm) [300c]");
        CheckBox leftUpgradeGHPR = getUpgrade("War (+25 dpr, +50 cpr, +50hp) [500c]");

        allLeftUpgrades.getChildren().addAll(leftUpgradeMOAT, leftUpgradePASI, leftUpgradeINHP, leftUpgradeMOMH, leftUpgradeGHPR);
        allLeftUpgrades.setLayoutX(20);
        allLeftUpgrades.setLayoutY(205);


//          Creating the checkboxes for the tail meter abilities left side
        VBox allLeftAbilities = new VBox();
        CheckBox leftAbilityRAGN = getUpgrade("Rain (All +400 coins, you +50 hp) [4T]");
        CheckBox leftAbilityITAX = getUpgrade("Ax (All = 0 coins, you deal 30 dmg) [5T]");
        CheckBox leftAbilityINST = getUpgrade("Crush (All -50% hp, you +200 coins) [6T]");
        CheckBox leftAbilityLAST = getUpgrade("??? (-50 hp, -200 coins, or 750 coins) [7T]");
        CheckBox leftAbilityROST = getUpgrade("War (+25 dpr, +50 cpr, +50hp) [10T]");

        allLeftAbilities.getChildren().addAll(leftAbilityRAGN, leftAbilityITAX, leftAbilityINST, leftAbilityLAST, leftAbilityROST);
        allLeftAbilities.setLayoutX(20);
        allLeftAbilities.setLayoutY(335);

        ArrayList <CheckBox> leftUpgradesList = new ArrayList<>();
        leftUpgradesList.add(leftUpgradeINHP);
        leftUpgradesList.add(leftUpgradeMOAT);
        leftUpgradesList.add(leftUpgradeMOMH);
        leftUpgradesList.add(leftUpgradeGHPR);
        leftUpgradesList.add(leftUpgradePASI);
        leftUpgradesList.add(leftAbilityROST);
        leftUpgradesList.add(leftAbilityRAGN);
        leftUpgradesList.add(leftAbilityINST);
        leftUpgradesList.add(leftAbilityITAX);
        leftUpgradesList.add(leftAbilityLAST);

        addEventListenerOptions(leftUpgradesList);

        for (CheckBox i: leftUpgradesList){
            i.setDisable(true);
        }

//        Creating the display for the right side
        Rectangle rightSideDisplay = makeSideDisplay(690, 215, 450, 250);
        Text rightSideLives = getStatsText("Lives: " + this.lives +"/" + this.lives, 800, 140, 40, 174, 194);
        Text rightSideCash = getStatsText("Money: 0 Cents", 800, 165, 0, 128, 0);
        Text rightSideUpgrades = getStatsText("Cash Upgrades", 800, 200, 0, 0, 139);
        Text rightSideAbilities = getStatsText("Tail Meter Abilities", 800, 330, 225, 225, 102);
        Text rightSideDPR = getStatsText("Dmg per Round: +0 dmg", 800, 465, 128, 0, 0);
        Text rightSideINC = getStatsText("Coins per Round: +0 coins", 800, 495, 0, 128, 0);

//          Creating the checkboxes for the cash upgrades right side
        VBox allRightUpgrades = new VBox();
        CheckBox rightUpgradeMOAT = getUpgrade("More attack (+3 dmg per Round) [75c]");
        CheckBox rightUpgradePASI = getUpgrade("Add income (+12 cents per Round) [100c]");
        CheckBox rightUpgradeINHP = getUpgrade("Instant replenish (+30 hp) [125c]");
        CheckBox rightUpgradeMOMH = getUpgrade("Eve (+15 dpr, +35 hp, +2tm) [300c]");
        CheckBox rightUpgradeGHPR = getUpgrade("War (+25 dpr, +50 cpr, +50hp) [500c]");

        allRightUpgrades.getChildren().addAll(rightUpgradeMOAT, rightUpgradePASI, rightUpgradeINHP, rightUpgradeMOMH, rightUpgradeGHPR);
        allRightUpgrades.setLayoutX(800);
        allRightUpgrades.setLayoutY(205);

//          Creating the checkboxes for the tail meter abilities right side
        VBox allRightAbilities = new VBox();
        CheckBox rightAbilityRAGN = getUpgrade("Rain (All +400 coins, you +50 hp) [4T]");
        CheckBox rightAbilityITAX = getUpgrade("Ax (All = 0 coins, you deal 30 dmg) [5T]");
        CheckBox rightAbilityINST = getUpgrade("Crush (All -50% hp, you +200 coins) [6T]");
        CheckBox rightAbilityLAST = getUpgrade("??? (-50 hp, -200 coins, or 750 coins) [7T]");
        CheckBox rightAbilityROST = getUpgrade("War (+25 dpr, +50 cpr, +50hp) [10T]");

        allRightAbilities.getChildren().addAll(rightAbilityRAGN, rightAbilityITAX, rightAbilityINST, rightAbilityLAST, rightAbilityROST);
        allRightAbilities.setLayoutX(800);
        allRightAbilities.setLayoutY(335);

        ArrayList <CheckBox> rightUpgradesList = new ArrayList<>();
        rightUpgradesList.add(rightUpgradeINHP);
        rightUpgradesList.add(rightUpgradeMOAT);
        rightUpgradesList.add(rightUpgradeMOMH);
        rightUpgradesList.add(rightUpgradeGHPR);
        rightUpgradesList.add(rightUpgradePASI);
        rightUpgradesList.add(rightAbilityROST);
        rightUpgradesList.add(rightAbilityRAGN);
        rightUpgradesList.add(rightAbilityINST);
        rightUpgradesList.add(rightAbilityITAX);
        rightUpgradesList.add(rightAbilityLAST);
        addEventListenerOptions(rightUpgradesList);

        for (CheckBox i: rightUpgradesList){
            i.setDisable(true);
        }

//        Creating the buttons for ending turn and upgrading and setting styles for the lines and buttons
        Button lButtonUp = endOrUpgrade("Purchase", 20, 515);
        Button lButtonEnd = endOrUpgrade("End", 180, 515);
        Button rButtonUp = endOrUpgrade("Purchase", 800, 515);
        Button rButtonEnd = endOrUpgrade("End", 960, 515);
        endOrUpgradeStyle(lButtonUp, lButtonEnd, rButtonUp, rButtonEnd);
        setLineStyle(topLine, midLine, botLine);
        setButtonStyle(resetButton, nextRoundButton, menuButton, roundNumButton, currentTurnButton);


//        Creating a War Game Functionality class to handle the functionality of the game
        WGFunctionality function = new WGFunctionality(this.lives, this.rounds, leftSideLives, rightSideLives, leftSideCash, rightSideCash,
                        leftTailMeter, rightTailMeter, roundNumButton, nextRoundButton, youWIN, currentTurnButton, lButtonEnd, lButtonUp,
                        leftUpgradesList, rightUpgradesList, leftSideDPR, leftSideINC, rightSideDPR, rightSideINC);


////        Creating the event listeners for the buttons
        resetButton.setOnMouseClicked((event) -> {
            function.reset();
        });
        menuButton.setOnMouseClicked((event) -> {
            this.window.setScene(menuView);
        });

//        Creating the main event listener, the start button
        FadeTransition fadeOutL = getCoinFade("Out");
        FadeTransition fadeInL = getCoinFade("In");
        FadeTransition fadeOutR = getCoinFade("Out");
        FadeTransition fadeInR = getCoinFade("In");
        Random random = new Random();
        nextRoundButton.setOnMouseClicked((event) -> {
            resetButton.setDisable(true);
            nextRoundButton.setDisable(true);
            changeYouGot(leftYouGot);
            changeYouGot(rightYouGot);
            fadeOutL.setNode(leftCoinS);
            fadeOutR.setNode(rightCoinS);
            String headsOrTailsL = getHeadsOrTails(random.nextInt(10));
            String headsOrTailsR = getHeadsOrTails(random.nextInt(10));

            fadeOutL.play();
            fadeOutR.play();
            fadeOutL.setOnFinished((event1) -> {
                leftCoinText.setText(headsOrTailsL);
                fadeInL.setNode(leftCoinS);
                fadeInL.play();
            });
            fadeOutR.setOnFinished((event2) -> {
                rightCoinText.setText(headsOrTailsR);
                fadeInR.setNode(rightCoinS);
                fadeInR.play();
            });
            fadeInL.setOnFinished((event3) -> {
                leftYouGot.setText(headsOrTailsL);
                resetButton.setDisable(false);
            });
            fadeInR.setOnFinished((event4) -> {
                rightYouGot.setText(headsOrTailsR);
                nextRoundButton.setDisable(false);
                function.update(leftYouGot, rightYouGot);
                nextRoundButton.setDisable(true);
            });
        });

        lButtonEnd.setDisable(true);
        lButtonUp.setDisable(true);
        rButtonEnd.setDisable(true);
        rButtonUp.setDisable(true);
        lButtonEnd.setOnMouseClicked((event) -> {
            rButtonEnd.setDisable(false);
            rButtonUp.setDisable(false);
            lButtonEnd.setDisable(true);
            lButtonUp.setDisable(true);
            for (CheckBox i: leftUpgradesList){
                i.setDisable(true);
                i.setSelected(false);
            }
            function.rightPlayersTurn();
        });
        lButtonUp.setOnMouseClicked((event) -> {
            String selectedUpgrade = "";
            for (CheckBox i: leftUpgradesList){
                boolean selected = i.isSelected();
                if (selected == true){
                    selectedUpgrade = i.getText();
                }
            }

            function.upgradeLeftColumn(selectedUpgrade);
        });

        rButtonEnd.setOnMouseClicked((event) -> {
            rButtonEnd.setDisable(true);
            rButtonUp.setDisable(true);
            nextRoundButton.setDisable(false);
            for (CheckBox i: rightUpgradesList){
                i.setDisable(true);
                i.setSelected(false);
            }
            function.checkEnd(1);
            currentTurnButton.setText("Current turn: N/A");
        });
        rButtonUp.setOnMouseClicked((event) -> {
            String selectedUpgrade = "";
            for (CheckBox i: rightUpgradesList){
                boolean selected = i.isSelected();
                if (selected == true){
                    selectedUpgrade = i.getText();
                }
            }

            function.upgradeRightColumn(selectedUpgrade);
        });


        warGame.getChildren().addAll(livesText, roundsText, gameText, midLine, topLine, botLine, resetButton, nextRoundButton, menuButton,
                roundNumButton, currentTurnButton, leftSideDisplay, rightSideDisplay, leftPlayer, rightPlayer, leftCoinS, rightCoinS,
                leftTailMeter, rightTailMeter, leftYouGot, rightYouGot, leftSideLives, leftSideCash, leftSideUpgrades, leftSideAbilities, allLeftUpgrades,
                allLeftAbilities, leftSideDPR, leftSideINC, rightSideLives, rightSideCash, rightSideUpgrades, rightSideAbilities, allRightUpgrades,
                allRightAbilities, rightSideDPR, rightSideINC, lButtonUp, lButtonEnd, rButtonUp, rButtonEnd, youWIN);
        warGame.setStyle("-fx-background-color: #b7e2e3");

        return warGame;
    }

    public void addEventListenerOptions(ArrayList<CheckBox> options){
        for (CheckBox i: options){
            i.setOnMouseClicked((event) -> {
                boolean isSelected = i.isSelected();
                if (isSelected == true){
                    String sameOption = i.getText();
                    for (CheckBox j: options){
                        if (!j.getText().equals(sameOption)){
                            j.setDisable(true);
                        }
                    }
                } else if (isSelected == false){
                    String sameOption2 = i.getText();
                    for (CheckBox k: options){
                        k.setDisable(false);
                    }
                }
            });
        }
    }

    public Text youWIN(){
        Text returnText = new Text("Player1 WINS!");
        returnText.setFont(Font.font("Verdana", 70));
        returnText.setFill(Color.BLACK);
        returnText.setLayoutX(265);
        returnText.setLayoutY(350);
        returnText.setOpacity(0.0);

        return returnText;
    }

    public String getHeadsOrTails(int number){
        if (number % 2 == 0){
            return "Heads";
        }
        return "Tails";
    }

    public void changeYouGot(Text youGotText){
        youGotText.setText("?????");
    }

    public FadeTransition getCoinFade(String text){
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(3000));

        if (text.equals("Out")){
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
        } else if (text.equals("In")){
            fade.setFromValue(0.0);
            fade.setToValue(1.0);
        }

        fade.setCycleCount(1);
        return fade;
    }

    public Button endOrUpgrade(String text, int locationX, int locationY){
        Button returnButton = new Button(text);
        returnButton.setFont(Font.font("Verdana", 15));
        returnButton.setLayoutX(locationX);
        returnButton.setLayoutY(locationY);

        return returnButton;
    }

    public void endOrUpgradeStyle(Button upgradeLeft, Button endLeft, Button upgradeRight, Button endRight){
        upgradeLeft.setStyle("-fx-background-color: #B19CD9; -fx-border-color: #000000; -fx-border-width: 1px; -fx-text-fill: #000000;");
        endLeft.setStyle("-fx-background-color: #654321; -fx-border-color: #000000; -fx-border-width: 1px; -fx-text-fill: #000000;");
        upgradeRight.setStyle("-fx-background-color: #B19CD9; -fx-border-color: #000000; -fx-border-width: 1px; -fx-text-fill: #000000;");
        endRight.setStyle("-fx-background-color: #654321; -fx-border-color: #000000; -fx-border-width: 1px; -fx-text-fill: #000000;");
    }

    public CheckBox getUpgrade(String text){
        CheckBox returnUpgrade = new CheckBox(text);
        returnUpgrade.setFont(Font.font("Verdana", 10));
        returnUpgrade.setPadding(new Insets(0, 0, 5, 0));

        return returnUpgrade;
    }

    public Text getStatsText(String text, int locationX, int locationY, int rgb1, int rgb2, int rgb3){
        Text returnText = new Text(text);
        returnText.setFont(Font.font("Verdana", 15));
        returnText.setLayoutX(locationX);
        returnText.setLayoutY(locationY);
        returnText.setFill(Color.rgb(rgb1, rgb2, rgb3));

        return returnText;
    }

    public Text getTopText(String text, int locationX, int locationY, int rgb1, int rgb2, int rgb3){
        Text returnText = new Text(text);
        returnText.setFont(Font.font("Verdana", 30));
        returnText.setLayoutX(locationX);
        returnText.setLayoutY(locationY);
        returnText.setFill(Color.rgb(rgb1, rgb2, rgb3));

        return returnText;
    }

    public Line getTopLine(int startingX, int startingY, int endingX, int endingY){
        Line returnLine = new Line(startingX, startingY, endingX, endingY);

        return returnLine;
    }

    public void setLineStyle(Line topLine, Line midLine, Line botLine){
        topLine.setStyle("-fx-stroke: red");
        topLine.setStrokeWidth(7);
        midLine.setStyle("-fx-stroke: black");
        midLine.setStrokeWidth(5);
        botLine.setStyle("-fx-stroke: grey");
        botLine.setStrokeWidth(7);

    }

    public Button getTopButton(String text, int locationX, int locationY){
        Button returnButton = new Button(text);

        returnButton.setLayoutX(locationX);
        returnButton.setLayoutY(locationY);
        returnButton.setFont(Font.font("Verdana", 20));

        return returnButton;
    }

    public void setButtonStyle(Button reset, Button start, Button menu, Button rounds, Button warNum){
        reset.setStyle("-fx-background-color: #BC2323; -fx-border-color: #000000; -fx-border-width: 1px; -fx-text-fill: #000000;");
        start.setStyle("-fx-background-color: #28C251; -fx-border-color: #000000; -fx-border-width: 1px; -fx-text-fill: #000000;");
        menu.setStyle("-fx-background-color: #B972C5; -fx-border-color: #000000; -fx-border-width: 1px; -fx-text-fill: #000000;");
        rounds.setStyle("-fx-background-color: #EBE713; -fx-border-color: #000000; -fx-border-width: 1px; -fx-text-fill: #000000;");
        warNum.setStyle("-fx-background-color: #000080; -fx-border-color: #000000; -fx-border-width: 1px; -fx-text-fill: #ffffff;");
    }

    public Rectangle makeSideDisplay(int startX, int startY, int width, int height){
        Rectangle returnRect = new Rectangle(startX, startY, width, height);
        returnRect.setRotate(90);
        returnRect.setFill(Color.rgb(240, 240, 240));
        returnRect.setStroke(Color.BLACK);
        returnRect.setStrokeWidth(7);

        return returnRect;
    }

    public Text getPlayerName(String text, int locationX, int locationY, int rgb1, int rgb2, int rgb3){
        Text returnPName = new Text(text);
        returnPName.setFont(Font.font("Verdana", 35));
        returnPName.setFill(Color.rgb(rgb1, rgb2, rgb3));
        returnPName.setLayoutX(locationX);
        returnPName.setLayoutY(locationY);

        return returnPName;
    }

    public Circle getCoin(int rgb1, int rgb2, int rgb3, int borderColor){
        Circle coin = new Circle( 100);
        if (borderColor == 0){
            coin.setStroke(Color.BLACK);
        } else {
            coin.setStroke(Color.WHITE);
        }
        coin.setStrokeWidth(5);
        coin.setFill(Color.rgb(rgb1, rgb2, rgb3));

        return coin;
    }

    public Text getCoinText(String text, int rgb1, int rgb2, int rgb3){
        Text sideText = new Text(text);
        sideText.setFill(Color.rgb(rgb1, rgb2, rgb3));
        sideText.setFont(Font.font("Aria", FontWeight.BOLD, 30));
        sideText.setBoundsType(TextBoundsType.VISUAL);

        return sideText;
    }

    public Text youGotText(int locationX, int locationY, int rgb1, int rgb2, int rgb3){
        Text youGotText = new Text("Heads");
        youGotText.setFont(Font.font("Verdana", 50));
        youGotText.setFill(Color.rgb(rgb1, rgb2, rgb3));
        youGotText.setLayoutX(locationX);
        youGotText.setLayoutY(locationY);

        return youGotText;
    }

    public Text getTailMeter(int locationX, int locationY){
        Text returnTM = new Text("Tail Meter: 0/10");
        returnTM.setLayoutX(locationX);
        returnTM.setLayoutY(locationY);
        returnTM.setFont(Font.font("Verdana", 25));
        returnTM.setFill(Color.DARKCYAN);

        return returnTM;
    }
}
