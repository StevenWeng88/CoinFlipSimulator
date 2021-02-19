package com.project2;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CoinTracker {
    private ArrayList<Coin> lastTen;
    private ArrayList<Coin> coinList;
    private int count;

    public CoinTracker(){
        this.lastTen = new ArrayList<>();
        this.coinList = new ArrayList<>();
        this.count = 0;
    }

    public void add(Coin coin){
        this.coinList.add(coin);
        updateLastTen(coin);
        count++;
    }

    public int getHeads(){
        int headCount = 0;
        for(Coin i: coinList){
            if (i.getSide().equals("Heads")){
                headCount += 1;
            }
        }
        return headCount;
    }

    public int getTails(){
        int tailCount = 0;
        for(Coin i: coinList){
            if (i.getSide().equals("Tails")){
                tailCount += 1;
            }
        }
        return tailCount;
    }

    public void updateLastTen(Coin coin){
        if (lastTen.size() == 10){
            lastTen.remove(0);
            lastTen.add(coin);
        } else {
            lastTen.add(coin);
        }
    }

    public ArrayList getLastTen(){
        return lastTen;
    }

    public int getCount(){
        return this.count;
    }

    public String getHeadsPercentage(){
        DecimalFormat df = new DecimalFormat("#.##");
        double headPercentage = 100 * (double) getHeads() / count;
        String percentage = df.format(headPercentage);
        return percentage;
    }

    public String getTailsPercentage(){
        DecimalFormat df = new DecimalFormat("#.##");
        double tailPercentage = 100 * (double) getTails() / count;
        String percentage = df.format(tailPercentage);
        return percentage;
    }

    public String lastCoin(){
        Coin coin = coinList.get(coinList.size() - 1);
        return coin.getSide();
    }

    public void clear(){
        this.count = 0;
        this.coinList.clear();
        this.lastTen.clear();
    }


}
