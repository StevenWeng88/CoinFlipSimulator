package com.project2;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.util.Duration;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.File;
import java.util.Random;

public class SimulatorDisplay {
    private Stage window;
    private Scene menuView;
    private Scene gameView;

    public SimulatorDisplay(Stage window){
        this.window = window;
    }

    public Pane getSimDisplay(){
////        Creating a MediaPlayer that plays a sound clip
        String path = "C:/Users/steve/OneDrive/Desktop/CoinFlip.wav";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);


////        Creating the Right side display that shows statistics and other option buttons
        Rectangle rightSideRect = getRDisplay();
        RightDisplay rightDisplay = new RightDisplay();
        VBox stats = rightDisplay.getVBox();
        Button game = moreOptions("Game", 710, 525);
        Button reset = moreOptions("Reset", 710, 600);
        Button menu = moreOptions("Menu", 870, 600);
        Button flipTen = moreOptions("Flip 10", 870, 525);
        menu.setStyle("-fx-background-color: #ffa600; ");
        game.setStyle("-fx-background-color: #00ffff");
        reset.setStyle("-fx-background-color: #ff0037");
        flipTen.setStyle("-fx-background-color: d0ff00");


////        Creating texts, buttons, and the Coin.
        Text topText = getTopText();
        Text firstText = getCoinText("Heads");
        Text youGotText = youGotText();
        Text statsLabel = statsLabel();
        Button flipButton = getFlipButton();
        Circle coin = newCoin();


////        Adding the text label onto the coin and creating the button underneath
        StackPane coinWithHLabel = new StackPane();
        coinWithHLabel.getChildren().addAll(coin, firstText);

        VBox coinButton = new VBox();
        coinButton.getChildren().add(coinWithHLabel);
        coinButton.getChildren().add(flipButton);
        coinButton.setLayoutX(200);
        coinButton.setLayoutY(350);


////        Creating and initializing new objects that will be used when the button is clicked
        FadeTransition fadeOut = getCoinFade("Out");
        FadeTransition fadeIn = getCoinFade("In");
        Random random = new Random();


////        Creating the button event listener
        flipButton.setOnMouseClicked((event -> {
//            AudioClip which is simpler code but this took me so long to figure out so I should have backup code
//            sound.play();
            mediaPlayer.play();
            flipButton.setDisable(true);
            flipTen.setDisable(true);
            changeYouGot(youGotText);
            fadeOut.setNode(coinWithHLabel);
            fadeOut.play();
            String headsOrTails = getHeadsOrTails(random.nextInt(10));

            fadeOut.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    firstText.setText(headsOrTails);
                    fadeIn.setNode(coinWithHLabel);
                    fadeIn.play();
                }
            });

            fadeIn.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    youGotText.setText("You got " + headsOrTails + "!");
                    flipButton.setDisable(false);
                    mediaPlayer.stop();
                    rightDisplay.update(headsOrTails);
                    reset.setDisable(false);
                    flipTen.setDisable(false);
                }
            });
        }));


////         Creating the four options button event listener
        flipTen.setOnMouseClicked((event) -> {
            mediaPlayer.play();
            flipButton.setDisable(true);
            flipTen.setDisable(true);
            changeYouGot(youGotText);
            fadeOut.setNode(coinWithHLabel);
            fadeOut.play();

            fadeOut.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    firstText.setText("Ten");
                    fadeIn.setNode(coinWithHLabel);
                    fadeIn.play();
                }
            });

            fadeIn.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    for(int i = 0; i < 10; i++){
                        String headsOrTails = getHeadsOrTails(random.nextInt(10));
                        rightDisplay.updateTen(headsOrTails);
                    }
                    youGotText.setText("You got Ten!");
                    flipButton.setDisable(false);
                    mediaPlayer.stop();
                    reset.setDisable(false);
                    flipTen.setDisable(false);
                }
            });

        });

        reset.setOnMouseClicked((event) -> {
            rightDisplay.clearDisplay();
            reset.setDisable(true);
        });

        game.setOnMouseClicked((event) -> {
            window.setScene(gameView);
        });

        menu.setOnMouseClicked((event) -> {
            window.setScene(menuView);
        });


////        Creating the pane to be returned
        Pane firstPane = new Pane();
        firstPane.setPrefSize(1050, 700);
        firstPane.getChildren().addAll(topText, youGotText, coinButton, rightSideRect, stats, statsLabel, game, reset, menu, flipTen);
        firstPane.setStyle("-fx-background-color: #9e908d; ");

        return firstPane;
    }

    public void addScenes(Scene mainMenu, Scene gameMenu){
        this.menuView = mainMenu;
        this.gameView = gameMenu;
    }

    public void changeYouGot(Text youGotText){
        youGotText.setLayoutX(140);
        youGotText.setLayoutY(250);
        youGotText.setText("You got ?????");
    }

    public Button moreOptions(String text, int locationX, int locationY){
        Button returnButton = new Button(text);
        returnButton.setFont(Font.font("Aria", FontWeight.BOLD, 25));
        returnButton.setPrefHeight(50);
        returnButton.setPrefWidth(125);
        returnButton.setLayoutX(locationX);
        returnButton.setLayoutY(locationY);
        return returnButton;
    }

    public Circle newCoin(){
        Circle coin = new Circle( 100);
        coin.setStroke(Color.BLACK);
        coin.setStrokeWidth(10);
        coin.setFill(Color.GRAY);
        return coin;
    }

    public Text statsLabel(){
        Text statsLabel = new Text("Statistics");
        statsLabel.setFont(Font.font("Verdana", 60));
        statsLabel.setLayoutX(715);
        statsLabel.setLayoutY(86);
        statsLabel.setFill(Color.rgb(110, 57, 153));
        return statsLabel;
    }

    public Text youGotText(){
        Text youGotText = new Text("Press flip to start");
        youGotText.setFont(Font.font("Verdana", 50));
        youGotText.setFill(Color.DARKBLUE);
        youGotText.setLayoutX(110);
        youGotText.setLayoutY(250);
        return youGotText;
    }

    public Text getTopText(){
        Text text = new Text("Coin Simulator");
        text.setFill(Color.BLACK);
        text.setFont(Font.font("Verdana", 80));
        text.setLayoutX(40);
        text.setLayoutY(100);
        return text;
    }

    public Rectangle getRDisplay(){
        Rectangle rightSideRect = new Rectangle(525, 172, 650, 350);
        rightSideRect.setRotate(90);
        rightSideRect.setFill(Color.rgb(240, 240, 240));
        rightSideRect.setStroke(Color.BLACK);
        rightSideRect.setStrokeWidth(10);
        return rightSideRect;
    }

    public Text getCoinText(String text){
        Text sideText = new Text(text);
        sideText.setFill(Color.BLACK);
        sideText.setFont(Font.font("Aria", FontWeight.BOLD, 40));
        sideText.setBoundsType(TextBoundsType.VISUAL);
        return sideText;
    }

    public Button getFlipButton(){
        Button flipButton = new Button("Flip");
        flipButton.setFont(Font.font("Verdana", 30));
        flipButton.setTranslateX(55);
        flipButton.setTranslateY(40);
        flipButton.setStyle("-fx-background-color: #67696e");
        flipButton.setPrefHeight(50);
        flipButton.setPrefWidth(100);
        return flipButton;
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

    public String getHeadsOrTails(int number){
        if (number % 2 == 0){
            return "Heads";
        }
        return "Tails";
    }

}
