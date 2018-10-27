package com.fitch.mb.rest.dto;

import java.util.Comparator;

/**
 * Created by hliu on 2016/7/5.
 */
public class CRCard implements ApiResponse, Comparable<CRCard>{

    private int Id;
    private String name;
    private int cost;
    private String rarity;
    private String type;

    public CRCard(){}

    public CRCard(String name, int cost, String rarity, String type){
        this.name = name;
        this.cost = cost;
        this.rarity = rarity;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "CRCard{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", rarity='" + rarity + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public int compareTo(CRCard o) {
/*        if( this.name.equalsIgnoreCase(o.getName())
                && this.cost == o.getCost()
                && this.rarity.equalsIgnoreCase(o.getRarity())
                && this.getType().equalsIgnoreCase(o.getType())
                ){
            return 0;
        }else {
            return this.Id - o.getId();
        }*/
        return this.Id - o.getId();
    }

    public static Comparator<CRCard> CardNameComparator = new Comparator<CRCard>() {
        public int compare(CRCard c1, CRCard c2) {

            //TODO: if name is null will giving exception, not able to compare

            String c1Name = c1.getName().toUpperCase();
            String c2Name = c2.getName().toUpperCase();
            //ascending order
            return c1Name.compareTo(c2Name);

            //descending order
            //return c2Name.compareTo(c1Name);
        }
    };

    public static Comparator<CRCard> CardCostComparator = new Comparator<CRCard>() {
        public int compare(CRCard c1, CRCard c2) {
            int result = c1.getCost() - c2.getCost();
            return result == 0 ? c1.getName().compareTo(c2.getName()) : result;
        }
    };

    public static Comparator<CRCard> CardRarityComparator = new Comparator<CRCard>() {
        public int compare(CRCard c1, CRCard c2) {

            //TODO: if name is null will giving exception, not able to compare

            String c1Rarity = c1.getRarity().toUpperCase();
            String c2Rarity = c2.getRarity().toUpperCase();
            int result = c1Rarity.compareTo(c2Rarity);
            return result == 0 ? (c1.getCost() - c2.getCost()) : result;
        }
    };

    public static Comparator<CRCard> FullCardComparator = new Comparator<CRCard>() {
        public int compare(CRCard c1, CRCard c2) {

            //TODO: if name is null will giving exception, not able to compare

            if( c1.getName().equalsIgnoreCase(c2.getName())
                    && c1.getCost() == c2.getCost()
                    && c1.getRarity().equalsIgnoreCase(c2.getRarity())
                    && c1.getType().equalsIgnoreCase(c2.getType())
                    ){
                return 0;
            }else {
                return c1.getId() - c2.getId();
            }
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CRCard crCard = (CRCard) o;

        if (cost != crCard.cost) return false;
        if (name != null ? !name.equals(crCard.name) : crCard.name != null) return false;
        if (rarity != null ? !rarity.equals(crCard.rarity) : crCard.rarity != null) return false;
        return type != null ? type.equals(crCard.type) : crCard.type == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + cost;
        result = 31 * result + (rarity != null ? rarity.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
