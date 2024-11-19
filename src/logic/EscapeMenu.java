package logic;

import enums.EscapeItemType;
import enums.*;
import exception.*;
import management.InventoryManager;
import model.*;
import utils.Input;
import java.util.List;
import java.util.Optional;


public class EscapeMenu {

    private InventoryManager inventory;

    public EscapeMenu() {
        this.inventory = new InventoryManager();
    }

    public void start() {
        int option;

        do {
            option = menu();
            switch (option) {
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
        } while (option != 0);

    }

    private int menu() {
        int option;
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
        String name;
        Optional<Level> chosenLevel = Optional.empty();

        name = Input.readString("Introduce the room's name:");
        do {
            try {
                chosenLevel = Level.findByValue(Input.readInt("Choose the difficulty: 1, 2 or 3"));
            } catch (ValueNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } while (chosenLevel.isEmpty());

        Room room = new Room(name, chosenLevel);

        try {
            this.inventory.addRoom(room);
            System.out.println("Room successfully created.");
        } catch (DuplicatedRoomException e) {
            System.err.println(e.getMessage());
        }

    }

    private void addClues() {
        String roomName, clueName;
        Room roomFound;
        Clue chosenClue;

        try {
            this.inventory.showRooms();
            roomName = Input.readString("Please enter the name of your room:");
            roomFound = this.inventory.findRoom(roomName);
            this.inventory.showClues();
            clueName = Input.readString("Please enter the name of your clue:");
            chosenClue = this.inventory.findClue(clueName);
            this.inventory.addClue(roomFound, chosenClue);
            System.out.println("A new clue has been added to your room.");
        } catch (NoRoomsException | RoomNotFoundException | NoCluesException | ClueNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private void addDecoItems() {
        String roomName, decoItemName;
        Room roomFound;
        DecorationItem chosenDecoItem;

        try {
            this.inventory.showRooms();
            roomName = Input.readString("Please enter the name of your room:");
            roomFound = this.inventory.findRoom(roomName);
            this.inventory.showDecoItems();
            decoItemName = Input.readString("Please enter the name of your decoration item:");
            chosenDecoItem = this.inventory.findDecoItem(decoItemName);
            this.inventory.addDecoItem(roomFound, chosenDecoItem);
            System.out.println("A new decoration item has been added to your room.");
        } catch (NoRoomsException | RoomNotFoundException | NoDecoItemsException | DecoItemNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private void showInventory() {
        List<Room> rooms;
        List<Clue> clues;
        List<DecorationItem> decoItems;

        try {
            System.out.println("Registered rooms:");
            rooms = this.inventory.showRooms();
            System.out.println("Total number of rooms: " + rooms.size());
            System.out.println("Total price: " + rooms.stream().mapToDouble(Room::getTotalPrice).sum());
        } catch (NoRoomsException e) {
            System.err.println(e.getMessage());
        }

        try {
            System.out.println("\nRegistered clues:");
            clues = this.inventory.showClues();
            System.out.println("Total number of clues: " + clues.size());
            System.out.println("Total price: " + clues.stream().mapToDouble(Clue::getPrice).sum());
        } catch (NoCluesException e) {
            System.err.println(e.getMessage());
        }

        try {
            System.out.println("\nRegistered decoration items:");
            decoItems = this.inventory.showDecoItems();
            System.out.println("Total number of decoration items: " + decoItems.size());
            System.out.println("Total price: " + decoItems.stream().mapToDouble(DecorationItem::getPrice).sum());

        } catch (NoDecoItemsException e) {
            System.err.println(e.getMessage());
        }
    }

    private void removeFromInventory() {
        EscapeItemType chosenItem = null;
        String roomName, clueName, decoName;
        Room roomFound;
        Clue clueFound;
        DecorationItem decoFound;

        do {
            try {
                chosenItem = EscapeItemType.findByValue(Input.readInt("What do you want to remove from the inventory? 1. Room, 2. Clue, 3. Decoration item"));

            } catch (ValueNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } while (chosenItem == null);

        switch (chosenItem) {
            case ROOM:
                try {
                    this.inventory.showRooms();
                    roomName = Input.readString("Please enter the name of the room you wish to remove:");
                    roomFound = this.inventory.findRoom(roomName);
                    this.inventory.removeRoom(roomFound);
                    System.out.println("The room with the name " + roomName + " has been removed from the inventory.");
                } catch (NoRoomsException | RoomNotFoundException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case CLUE:
                try {
                    this.inventory.showClues();
                    clueName = Input.readString("Please enter the name of the clue you wish to remove:");
                    clueFound = this.inventory.findClue(clueName);
                    this.inventory.removeClue(clueFound);
                    System.out.println("The clue with the name " + clueName + " has been removed from the inventory.");
                } catch (NoCluesException | ClueNotFoundException e) {
                    System.err.println(e.getMessage());
                }
                break;

            case DECORATION:
                try {
                    this.inventory.showDecoItems();
                    decoName = Input.readString("Please enter the name of the decoration item you wish to remove:");
                    decoFound = this.inventory.findDecoItem(decoName);
                    this.inventory.removeDecoItem(decoFound);
                    System.out.println("The decoration item with the name " + decoName + " has been removed from the inventory.");
                } catch (NoDecoItemsException | DecoItemNotFoundException e) {
                    System.err.println(e.getMessage());
                }
                break;
                }
        }


}
