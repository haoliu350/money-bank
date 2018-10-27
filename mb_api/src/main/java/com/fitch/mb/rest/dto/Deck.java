package com.fitch.mb.rest.dto;

import java.util.List;

/**
 * Created by hliu on 2016/7/18.
 */
public class Deck implements ApiResponse, Comparable<Deck>{

    private int Id;
    private List<CRCard> cardlist;
    private double cost;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public List<CRCard> getCardlist() {
        return cardlist;
    }

    public void setCardlist(List<CRCard> cardlist) {
        this.cardlist = cardlist;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public int compareTo(Deck o) {
        return this.Id - o.getId();
    }
}
