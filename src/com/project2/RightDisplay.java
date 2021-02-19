package com.project2;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import java.util.ArrayList;

public class RightDisplay {
    private Text text1;
    private Text text2;
    private Text text3;
    private Text text4;
    private Text text5;
    private Text text6;
    private Text text7;

    private CoinTracker tracker;
    private int flipTenTracker;


    public RightDisplay(){
        text1 = makeText("Last coin: N/A");
        text2 = makeText("Last 10 flips: N/A");
        text3 = makeText("Percentage heads: N/A");
        text4 = makeText("Percentage tails: N/A");
        text5 = makeText("Total heads: N/A");
        text6 = makeText("Total tails: N/A");
        text7 = makeText("Total flips: N/A");
        tracker = new CoinTracker();
    }

    public VBox getVBox(){

        Rectangle rect1 = makeRectangle(119, 136, 153);
        StackPane sPane1 = makePane(rect1, text1);

        Rectangle rect2 = makeRectangle(128, 0, 0);
        StackPane sPane2 = makePane(rect2, text2);

        Rectangle rect3 = makeRectangle(255, 165, 0);
        StackPane sPane3 = makePane(rect3, text3);

        Rectangle rect4 = makeRectangle(128, 0, 128);
        StackPane sPane4 = makePane(rect4, text4);

        Rectangle rect5 = makeRectangle(0, 0, 128);
        StackPane sPane5 = makePane(rect5, text5);

        Rectangle rect6 = makeRectangle(144, 238, 144);
        StackPane sPane6 = makePane(rect6, text6);

        Rectangle rect7 = makeRectangle(255, 42, 0);
        StackPane sPane7 = makePane(rect7, text7);

        VBox stats = new VBox();

        stats.getChildren().addAll(sPane1, sPane2, sPane3, sPane4, sPane5, sPane6, sPane7);
        stats.setLayoutX(680);
        stats.setLayoutY(115);
        return stats;
    }

    public void update(String headsOrTails){
        Coin coin = new Coin(headsOrTails);
        tracker.add(coin);

        text1.setText("Last coin: " + coin.getSide());
        text3.setText("Percentage heads: " + tracker.getHeadsPercentage() + "%");
        text4.setText("Percentage tails: " + tracker.getTailsPercentage() + "%");
        text5.setText("Total heads: " + tracker.getHeads());
        text6.setText("Total tails: " + tracker.getTails());
        text7.setText("Total flips: " + tracker.getCount());

        String tenCoins = "";
        ArrayList<Coin> lastTen = tracker.getLastTen();
        for(Coin i: lastTen){
            if (i.getSide().equals("Heads")){
                tenCoins += "H";
            } else {
                tenCoins += "T";
            }
        }
        text2.setText("Last 10 flips: " + tenCoins);
    }

    public void updateTen(String headsOrTails){
        flipTenTracker++;
        Coin coin = new Coin(headsOrTails);
        tracker.add(coin);

        if (flipTenTracker == 10){
            text1.setText("Last coin: " + coin.getSide());
            text3.setText("Percentage heads: " + tracker.getHeadsPercentage() + "%");
            text4.setText("Percentage tails: " + tracker.getTailsPercentage() + "%");
            text5.setText("Total heads: " + tracker.getHeads());
            text6.setText("Total tails: " + tracker.getTails());
            text7.setText("Total flips: " + tracker.getCount());

            String tenCoins = "";
            ArrayList<Coin> lastTen = tracker.getLastTen();
            for(Coin i: lastTen){
                if (i.getSide().equals("Heads")){
                    tenCoins += "H";
                } else {
                    tenCoins += "T";
                }
            }
            text2.setText("Last 10 flips: " + tenCoins);
            flipTenTracker = 0;
        }
    }

    public Rectangle makeRectangle(int value1, int value2, int value3){
        Rectangle rectangle = new Rectangle(336, 50);
        rectangle.setFill(Color.rgb(value1, value2, value3));
        rectangle.setStroke(Color.rgb(value1, value2, value3));
        rectangle.setStrokeWidth(5);
        rectangle.setOpacity(0.5);

        return rectangle;
    }

    public Text makeText(String theText){
        Text text = new Text(theText);
        text.setFont(Font.font("Verdana", 15));
        text.setBoundsType(TextBoundsType.VISUAL);
        return text;
    }

    public StackPane makePane(Rectangle rectangle, Text text){
        StackPane returnPane = new StackPane();
        returnPane.getChildren().addAll(rectangle, text);
        return returnPane;
    }

    public void clearDisplay(){
        tracker.clear();
        text1.setText("Last coin: N/A");
        text2.setText("Last 10 flips: N/A");
        text3.setText("Percentage heads: N/A");
        text4.setText("Percentage tails: N/A");
        text5.setText("Total heads: N/A");
        text6.setText("Total tails: N/A");
        text7.setText("Total flips: N/A");
    }
}
