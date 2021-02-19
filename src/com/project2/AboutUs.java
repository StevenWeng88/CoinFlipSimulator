package com.project2;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class AboutUs {
    private Stage window;
    private Scene menuView;

    public AboutUs(Stage window, Scene menuView){
        this.window = window;
        this.menuView = menuView;
    }

    public Pane aboutUsDisplay(){
        Pane aboutPane = new Pane();
        aboutPane.setPrefSize(1050, 700);

        Text headingText = returnHeading();
        Text bodyText = returnTextBody();
        Button returnBut = returnButton();

        returnBut.setOnMouseClicked((event) -> {
            window.setScene(menuView);
        });


        aboutPane.getChildren().addAll(headingText, bodyText, returnBut);

        return aboutPane;
    }

    public Button returnButton(){
        Button returnButton = new Button("Return");
        returnButton.setFont(Font.font("Verdana", 30));
        returnButton.setLayoutX(75);
        returnButton.setLayoutY(600);

        return returnButton;
    }

    public Text returnHeading(){
        Text returnText = new Text("About this Project");
        returnText.setFont(Font.font("Verdana", 50));
        returnText.setFill(Color.BLACK);
        returnText.setLayoutX(300);
        returnText.setLayoutY(75);

        return returnText;
    }

    public Text returnTextBody(){
        Text returnText = new Text(
                    "A project created by Steven Weng using JavaFx. \n" +
                    "I got the background image for the menu scene on unsplash.com from Michael Longmire. ");
        returnText.setFont(Font.font("Verdana", 15));
        returnText.setFill(Color.GRAY);
        returnText.setLayoutX(75);
        returnText.setLayoutY(125);

        return returnText;
    }

}
