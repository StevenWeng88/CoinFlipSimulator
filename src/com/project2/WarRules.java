package com.project2;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WarRules {

    private Stage window;
    private Scene gameView;

    public WarRules(Stage window, Scene gameView){
        this.window = window;
        this.gameView = gameView;
    }

    public Pane getRulesPane(){
        Pane daRules = new Pane();
        daRules.setPrefSize(1050, 700);

        Button goBackBut = getReturnButton();
        Text topText = getTopText();
        Text subHead1 = getSubHeading("Starting the round", 50, 125, 16, 63, 105);
        Text subBody1 = getTextBody1();
        Text subHead2 = getSubHeading("Turns, upgrades, and abilities", 50, 230, 70, 208, 48);
        Text subBody2 = getTextBody2();
        Text subHead3 = getSubHeading("Winning the game", 50, 375, 165, 36, 31);
        Text subBody3 = getTextBody3();
        Text subHead4 = getSubHeading("Anticipated F.A.Qs", 50, 500, 255, 128, 0);
        Text subBody4 = getTextBody4();


        goBackBut.setOnMouseClicked((event) -> {
            this.window.setScene(gameView);
        });

        daRules.getChildren().addAll(goBackBut, topText, subHead1, subBody1, subHead2, subBody2, subHead3, subBody3, subHead4, subBody4);
        daRules.setStyle("-fx-background-color: #F7FF09");

        return daRules;
    }

    public Text getSubHeading(String text, int locationX, int locationY, int rgb1, int rgb2, int rgb3){
        Text returnText = new Text(text);
        returnText.setFont(Font.font("Verdana", 30));
        returnText.setFill(Color.rgb(rgb1, rgb2, rgb3));
        returnText.setLayoutX(locationX);
        returnText.setLayoutY(locationY);

        return returnText;
    }

    public Text getTextBody1(){
        Text returnText = new Text("The game begins when the start button is pressed. After the button is pressed, both of the players will flip a coin. \n" +
                "If a player flips heads, they will deal 15 damage to the enemy and get 50 coins. If a player flips tails, they will deal \n" +
                "5 damage to the enemy, get 10 coins, and get a tail added to their tail meter.");
        returnText.setFill(Color.rgb(0, 0, 0));
        returnText.setFont(Font.font("Verdana", 15));
        returnText.setLayoutX(50);
        returnText.setLayoutY(150);

        return returnText;
    }

    public Text getTextBody2(){
        Text returnText = new Text("After the coins have been flipped, the damage has been dealt, and the cents have been distributed. Each player\n" +
                    "will have a turn to buy upgrades and abilities. Player1 (Left player) will always go first. During a turn, you can buy \n" +
                    "upgrades and abilities while your opponent cannot. When you are finished buying upgrades and abilities, simply press end \n" +
                    "and it will be the next players' turn. After the second players' turn has ended, there is nothing left to be done except to\n" +
                    "press the start button to repeat the rounds until someone wins.");
        returnText.setFill(Color.rgb(0, 0, 0));
        returnText.setFont(Font.font("Verdana", 15));
        returnText.setLayoutX(50);
        returnText.setLayoutY(255);

        return returnText;
    }

    public Text getTextBody3(){
        Text returnText = new Text("There are multiple ways to win the game. The first way is to simply get your opponents life lower than or equal\n" +
                    "to 0. This can either be done through a coin flip or buying upgrades and abilities that do damage to your opponent's lives.\n" +
                    "If however, neither you or your opponents' lives reach 0 by the end of the set amount of rounds, whoever has the most amount\n" +
                    "of lives at the end of the final round will be declared the winner of the game.");
        returnText.setFill(Color.rgb(0, 0, 0));
        returnText.setFont(Font.font("Verdana", 15));
        returnText.setLayoutX(50);
        returnText.setLayoutY(400);

        return returnText;
    }

    public Text getTextBody4(){
        Text returnText = new Text("Q: Can I buy multiple of the same upgrades and abilities? A: Yes, there is not a limit to the amount of times you\n" +
                    "can buy something within a single round or throughout the game. If you have the cash, you can buy it.\n" +
                    "Q: Is there a turn timer, where my turn will end automatically? A: No, there is not a turn timer and it is up to the player\n" +
                    "to not abuse that part of the game, there might be a turn timer in the future.");
        returnText.setFill(Color.rgb(0, 0, 0));
        returnText.setFont(Font.font("Verdana", 15));
        returnText.setLayoutX(50);
        returnText.setLayoutY(525);

        return returnText;
    }

    public Text getTopText(){
        Text returnText = new Text("War Rules");
        returnText.setFont(Font.font("Verdana", 80));
        returnText.setFill(Color.MAROON);
        returnText.setLayoutX(350);
        returnText.setLayoutY(75);

        return returnText;
    }

    public Button getReturnButton(){
        Button goBackBut = new Button("Go back");
        goBackBut.setFont(Font.font("Verdana", 30));
        goBackBut.setStyle("-fx-background-color: #BC2323; -fx-border-color: #000000; -fx-border-width: 3px; -fx-text-fill: #000000;");
        goBackBut.setLayoutX(25);
        goBackBut.setLayoutY(625);
        return goBackBut;
    }

}
