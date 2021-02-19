package com.project2;

import javafx.animation.FadeTransition;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import java.util.ArrayList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.util.Duration;

public class GameDisplay {
    private Stage window;
    private Scene menuView;
    private Scene simView;
    private Scene rulesView;

    public GameDisplay(Stage window){
        this.window = window;
    }

    public Pane returnGameLayout(){
        Pane gameDisplay = new Pane();
        gameDisplay.setPrefSize(1050, 700);


////        Creating the head text, selector texts, and buttons
        Text headerText = getMainText();
        Text selectMode = getSelectorText("Select Game Mode", 50, 175, 101, 194, 40);
        Text selectLives = getSelectorText("Select Starting Lives", 50, 350, 40, 174, 194);
        Text selectRounds = getSelectorText("Select Amount of Rounds", 50, 540, 76, 40, 194);
        Button start = getButton("Start", 700, 200);
        Button rules = getButton("Rules", 700, 300);
        Button simulator = getButton("Simulator", 700, 400);
        Button menu = getButton("Menu", 700, 500);
        setBBackground(start, rules, simulator, menu);

////        Creating the check boxes
        CheckBox war = getCBoxes("War", 50, 185);

        CheckBox lives250 = getCBoxes("250 Lives", 50, 360);
        CheckBox lives500 = getCBoxes("500 Lives", 50, 410);
        CheckBox lives1000 = getCBoxes("1000 Lives", 50, 460);

        CheckBox rounds10 = getCBoxes("10 Rounds", 50, 550);
        CheckBox rounds25 = getCBoxes("25 Rounds", 50, 600);
        CheckBox rounds50 = getCBoxes("50 Rounds", 50, 650);


////         Creating the check box event listeners
        ArrayList<CheckBox> gameList = new ArrayList<>();
        gameList.add(war);
        addEventListenerGames(gameList);

        ArrayList <CheckBox> livesList = new ArrayList<>();
        livesList.add(lives250);
        livesList.add(lives500);
        livesList.add(lives1000);
        addEventListenerLives(livesList);

        ArrayList <CheckBox> roundsList = new ArrayList<>();
        roundsList.add(rounds10);
        roundsList.add(rounds25);
        roundsList.add(rounds50);
        addEventListenerRounds(roundsList);


////        Creating the event handlers for the buttons
        start.setOnMouseClicked((event) -> {
            int lives = 0;
            int rounds = 0;
            String game = " ";

            for(CheckBox i: livesList){
                boolean isSelected = i.isSelected();
                if (isSelected == true){
                    String livesText = i.getText();
                    String[] tokens = livesText.split(" ");
                    lives = Integer.valueOf(tokens[0]);
                }
            }

            for(CheckBox i: roundsList){
                boolean isSelected = i.isSelected();
                if (isSelected == true){
                    String roundsText = i.getText();
                    String[] tokens = roundsText.split(" ");
                    rounds = Integer.valueOf(tokens[0]);
                }
            }

            for(CheckBox i: gameList){
                boolean isSelected = i.isSelected();
                if (isSelected == true){
                    game = i.getText();
                }
            }

            boolean gameSelected = war.isSelected();
            if (lives != 0 && rounds != 0 && !game.equals(" ")){
                if (game.equals("War")) {
                    WarGame newWarGame = new WarGame(window, menuView, lives, rounds);
                    Pane warGamePane = newWarGame.getWarPane();
                    Scene warView = new Scene(warGamePane);
                    window.setScene(warView);
                }
            } else {
                Text error = errorText();
                gameDisplay.getChildren().add(error);
                FadeTransition errorFade = getFade();
                errorFade.setNode(error);
                errorFade.play();
            }
        });


        rules.setOnMouseClicked((event) -> {
            window.setScene(rulesView);
        });

        simulator.setOnMouseClicked((event) -> {
            window.setScene(simView);
        });

        menu.setOnMouseClicked((event) -> {
            window.setScene(menuView);
        });


        gameDisplay.getChildren().addAll(headerText, selectMode, selectLives, selectRounds, start, rules, simulator, menu, war
                , lives250, lives500, lives1000, rounds10, rounds25, rounds50);
        gameDisplay.setStyle("-fx-background-color: #F7FF09");

        return gameDisplay;
    }

    public FadeTransition getFade(){
        FadeTransition returnFade = new FadeTransition();
        returnFade.setDuration(Duration.millis(8000));
        returnFade.setFromValue(1.0);
        returnFade.setToValue(0.0);
        returnFade.setCycleCount(1);

        return returnFade;
    }

    public Text errorText(){
        Text errorText = new Text("Please Select All CheckBoxes");
        errorText.setFont(Font.font("Verdana", 50));
        errorText.setLayoutX(200);
        errorText.setLayoutY(300);

        return errorText;
    }

    public void addEventListenerGames(ArrayList<CheckBox> options){
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

    public void addEventListenerLives(ArrayList<CheckBox> options){
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

    public void addEventListenerRounds(ArrayList<CheckBox> options){
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

    public CheckBox getCBoxes(String text, int xLocation, int yLocation){
        CheckBox option = new CheckBox(text);
        option.setLayoutX(xLocation);
        option.setLayoutY(yLocation);
        option.setStyle("-fx-font-size: 18");

        return option;
    }

    public void addScenes(Scene menuView, Scene simView, Scene rulesView){
        this.menuView = menuView;
        this.simView = simView;
        this.rulesView = rulesView;
    }

    public Text getMainText(){
        Text returnText = new Text("Game Selector");
        returnText.setFont(Font.font("Verdana", 100));
        returnText.setLayoutX(180);
        returnText.setLayoutY(105);
        returnText.setFill(Color.rgb(194, 121, 40));

        return returnText;
    }

    public Button getButton(String text, int xLocation, int yLocation){
        Button returnButton = new Button(text);
        returnButton.setLayoutX(xLocation);
        returnButton.setLayoutY(yLocation);
        returnButton.setPrefSize(275, 75);
        returnButton.setFont(Font.font("Verdana", 40));

        return returnButton;
    }

    public void setBBackground(Button start, Button rules, Button sim, Button menu){
        start.setStyle("-fx-background-color: #28C251; -fx-border-color: #000000; -fx-border-width: 3px; -fx-text-fill: #000000;");
        rules.setStyle("-fx-background-color: #BC2323; -fx-border-color: #000000; -fx-border-width: 3px; -fx-text-fill: #000000;");
        sim.setStyle("-fx-background-color: #EBE713; -fx-border-color: #000000; -fx-border-width: 3px; -fx-text-fill: #000000;");
        menu.setStyle("-fx-background-color: #B972C5; -fx-border-color: #000000; -fx-border-width: 3px; -fx-text-fill: #000000;");

    }

    public Text getSelectorText(String text, int xLocation, int yLocation, int rgb1, int rgb2, int rgb3){
        Text returnText = new Text(text);
        returnText.setLayoutX(xLocation);
        returnText.setLayoutY(yLocation);
        returnText.setFont(Font.font("Verdana", 40));
        returnText.setFill(Color.rgb(rgb1, rgb2, rgb3));

        return returnText;
    }
}

