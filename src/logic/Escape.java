package logic;

import classes.Clue;
import classes.DecorationItem;
import classes.Room;
import enums.Level;
import exceptions.*;
import management.InventoryManager;
import utils.Input;

import java.util.ArrayList;
import java.util.Random;

public class Escape {

    private InventoryManager inventory;

    public Escape() {
        this.inventory = new InventoryManager();
    }

    public void start() {
        int option = 0;

        do {
            option = menu();
            switch(option) {
                case 0:
                    System.out.println("You are exiting the system. Goodbye!");
                    break;
                case 1:
                    newRoom();
                    break;
                case 2:
                    addClues();
                    break;
                case 3:
                    addDecoItems();
                    break;
                case 4:
                    showInventory();
                    break;
                case 5:
                    removeFromInventory();
                    break;
                default:
                    System.out.println("Only 0, 1, 2, 3, 4 and 5 are valid answers. Please, try again.");
            }
        } while(option != 0);

    }

    private int menu() {
        int option = 0;
        option = Input.readInt("Choose the action:\n"
                + "1. Create a new room."
                + "\n2. Add clues to a room."
                + "\n3. Add decoration items to a room."
                + "\n4. Show updated inventory."
                + "\n5. Remove from inventory."
                + "\n0. Exit.");
        return option;
    }

    private void newRoom() {
        String name = "";
        int level = 0;
        Level chosenLevel = null;

        name = Input.readString("Introduce the room's name:");
        level = Input.readInt("Choose the difficulty: 1, 2 or 3");
        chosenLevel = Level.findByValue(level);

        Room room = new Room(name, chosenLevel);

        try {
            this.inventory.addRoom(room);
            System.out.println("Room successfully created.");
        } catch(DuplicatedRoomException e) {
            System.err.println(e.getMessage());
        }

    }

    private void addClues() {
        String name = "";
        int clueType = 0;
        Room roomFound = null;

       name = Input.readString("Please enter the name of your room:");
       roomFound = this.inventory.findRoom(name);

        if(roomFound == null) {
            System.out.println("Sorry, this room is not in the system.");
        } else {
            clueType = Input.readInt("What kind of clue do you need: 1. Numerical, 2. Alphabetical, 3. Visual?");

            switch (clueType) {
                case 1:
                    Clue numClue = this.inventory.getInventoryClues().get(0);
                    roomFound.getClues().add(numClue);
                    roomFound.setTotalPrice(roomFound.getTotalPrice() + numClue.getPrice());
                    System.out.println("A numerical clue has been added to your room.");
                    break;
                case 2:
                    Clue alphClue = this.inventory.getInventoryClues().get(1);
                    roomFound.getClues().add(alphClue);
                    roomFound.setTotalPrice(roomFound.getTotalPrice() + alphClue.getPrice());
                    System.out.println("An alphabetical clue has been added to your room.");
                    break;
                case 3:
                    Clue visClue = this.inventory.getInventoryClues().get(2);
                    roomFound.getClues().add(visClue);
                    roomFound.setTotalPrice(roomFound.getTotalPrice() + visClue.getPrice());
                    System.out.println("A visual clue has been added to your room.");
                    break;
            }
        }

    }

    private void addDecoItems() {
        String name = "";
        Room roomFound = null;
        DecorationItem selectedItem = null;

        name = Input.readString("Please enter the name of your room:");
        roomFound = this.inventory.findRoom(name);

        if(roomFound == null) {
            System.out.println("Sorry, this room is not in the system.");
        } else {
            ArrayList<DecorationItem> availableItems = this.inventory.getInventoryDecoItems();
            Random random = new Random();
            selectedItem = availableItems.get(random.nextInt(availableItems.size()));
            roomFound.getDecorationItems().add(selectedItem);
            roomFound.setTotalPrice(roomFound.getTotalPrice() + selectedItem.getPrice());
            System.out.println("A new decoration has been added to your room.");
        }
    }

    private void showInventory() {
        try {
            this.inventory.showRooms();
        } catch (NoRoomsException e) {
            System.err.println(e.getMessage());
        }

        try {
            this.inventory.showClues();
        } catch (NoCluesException e) {
            System.err.println(e.getMessage());
        }

        try {
            this.inventory.showDecoItems();
        } catch (NoDecoItemsException e) {
            System.err.println(e.getMessage());
        }
    }

    private void removeFromInventory() {
        int removal = 0;
        removal = Input.readInt("What do you want to remove from the inventory? 1. Room, 2. Clue, 3. Decoration item");

        switch(removal) {
            case 1:
                try {
                    this.inventory.removeRoom();
                    System.out.println("The first room found in the inventory has been removed.");
                } catch (NoRoomsException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case 2:
                try {
                    this.inventory.removeClue();
                    System.out.println("The first clue found in the inventory has been removed.");
                } catch (NoCluesException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case 3:
                try {
                    this.inventory.removeDecoItem();
                    System.out.println("The first decoration item found in the inventory has been removed.");
                } catch (NoDecoItemsException e) {
                    System.err.println(e.getMessage());
                }
                break;
        }
    }

}
