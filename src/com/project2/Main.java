package com.project2;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;



public class Main extends Application{

    @Override
    public void start(Stage window){
////        Creating the Main text and the buttons
        Text mainText = mainText();
        Button simulator = makeButton("Simulator", 400, 350);
        Button game = makeButton("Game", 400, 450);
        Button info = makeButton("About", 400, 550);


////        Creating the main pane
        Pane menuPane = new Pane();
        menuPane.setPrefSize(1050, 700);
        menuPane.getChildren().addAll(mainText, simulator, game, info);
        Scene view = new Scene(menuPane);


////        Creating the background image of the main pane
        String image = this.getClass().getResource("CoinSimMenu.jpg").toExternalForm();
        menuPane.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;" +
                "-fx-background-size: 100%;");


//        Creating instances of SimulatorDisplay and GameDisplay for so the scene can be set through different classes
        SimulatorDisplay simDisplay = new SimulatorDisplay(window);
        Pane sim = simDisplay.getSimDisplay();
        Scene simView = new Scene(sim);

        GameDisplay gameMode = new GameDisplay(window);
        Pane gameDisplay = gameMode.returnGameLayout();
        Scene gameView = new Scene(gameDisplay);

        AboutUs infoPane = new AboutUs(window, view);
        Pane aboutDisplay = infoPane.aboutUsDisplay();
        Scene aboutView = new Scene(aboutDisplay);

        WarRules daRules = new WarRules(window, gameView);
        Pane rulesPane = daRules.getRulesPane();
        Scene rulesView = new Scene(rulesPane);

        simDisplay.addScenes(view, gameView);
        gameMode.addScenes(view, simView, rulesView);


////        Creating the event listeners for all of the buttons
        simulator.setOnMouseClicked((event) -> {
            window.setScene(simView);
        });

        game.setOnMouseClicked((event) -> {
            window.setScene(gameView);
        });

        info.setOnMouseClicked((event) -> {
            window.setScene(aboutView);
        });

        window.setTitle("Coin flip simulator");
        window.setScene(view);
        window.show();

    }

    public Text mainText(){
        Text mainText = new Text("Deluxe Coin Simulator");
        mainText.setFont(Font.font("Verdana", FontWeight.BOLD, 80));
        mainText.setLayoutX(25);
        mainText.setLayoutY(130);
        mainText.setFill(Color.rgb(170, 170, 170));
        mainText.setStrokeWidth(3);
        mainText.setStroke(Color.BLACK);

        return mainText;
    }

    public Button makeButton(String text, int locationX, int locationY){
        Button returnButton = new Button(text);
        returnButton.setLayoutX(locationX);
        returnButton.setLayoutY(locationY);
        returnButton.setFont(Font.font("Verdana", 35));
        returnButton.setPrefSize(250,75);

        return returnButton;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
