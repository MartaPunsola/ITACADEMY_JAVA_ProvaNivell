package model;

import enums.Level;

import java.util.ArrayList;
import java.util.Optional;

public class Room {

    private int roomId;
    private static int idIncrement = 1;
    private String name;
    private Optional<Level> level;
    private final double PRICE;
    private double totalPrice;
    private ArrayList<Clue> clues;
    private ArrayList<DecorationItem> decorationItems;

    public Room(String name, Optional<Level> level) {
        this.roomId = Room.idIncrement;
        Room.idIncrement++;
        this.name = name;
        this.level = level;
        this.PRICE = 100;
        this.totalPrice = this.PRICE;
        this.clues = new ArrayList<Clue>();
        this.decorationItems = new ArrayList<DecorationItem>();
    }

    public int getRoomId() {
        return this.roomId;
    }

    public String getName() {
        return this.name;
    }

    public Optional<Level> getLevel() {
        return this.level;
    }

    public double getPRICE() {
        return this.PRICE;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public ArrayList<Clue> getClues() {
        return this.clues;
    }

    public ArrayList<DecorationItem> getDecorationItems() {
        return this.decorationItems;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", totalPrice=" + totalPrice +
                '}';
    }

}
