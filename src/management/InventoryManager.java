package management;

import classes.*;
import enums.Material;
import enums.Theme;
import exceptions.*;

import java.util.ArrayList;

public class InventoryManager {

    private ArrayList<Room> inventoryRooms;
    private ArrayList<Clue> inventoryClues;
    private ArrayList<DecorationItem> inventoryDecoItems;

    public InventoryManager() {
        this.inventoryRooms = new ArrayList<Room>();
        this.inventoryClues = new ArrayList<Clue>();
        this.createClues();
        this.inventoryDecoItems = new ArrayList<DecorationItem>();
        this.createDecorationItems();
    }

    public ArrayList<Room> getInventoryRooms() {
        return this.inventoryRooms;
    }

    public ArrayList<Clue> getInventoryClues() {
        return this.inventoryClues;
    }

    public ArrayList<DecorationItem> getInventoryDecoItems() {
        return this.inventoryDecoItems;
    }

    public void addRoom(Room r) throws DuplicatedRoomException {
        String name = r.getName();
        Room roomFound = this.findRoom(name);

        if(roomFound != null) {
            throw new DuplicatedRoomException("This room already exists.");
        }
        this.inventoryRooms.add(r);
    }

    private void createClues() {
        this.inventoryClues.add(new Clue(25.10, "code","15 min", Theme.NUMBER));
        this.inventoryClues.add(new Clue(30, "palindrome","20 min", Theme.LETTER));
        this.inventoryClues.add(new Clue(52.50, "photo","5 min", Theme.IMAGE));
    }

    private void createDecorationItems() {
        this.inventoryDecoItems.add(new DecorationItem(32,"key", Material.METAL));
        this.inventoryDecoItems.add(new DecorationItem(15.60, "box", Material.WOOD));
        this.inventoryDecoItems.add(new DecorationItem(20.3, "straw", Material.PLASTIC));
        this.inventoryDecoItems.add(new DecorationItem(32, "wineglass", Material.GLASS));
    }

    public void showRooms() throws NoRoomsException {
        if(this.inventoryRooms.isEmpty()) {
            throw new NoRoomsException("There are no rooms at the moment.");
        }
        System.out.println("Registered rooms:");
        this.inventoryRooms.stream().map(r -> "ID: " + r.getRoomId() + ", Name: " + r.getName() + ", Price: " + r.getTotalPrice()).forEach(System.out::println);
        System.out.println("Total number of rooms: " + this.inventoryRooms.size());
        System.out.println("Total price: " + this.inventoryRooms.stream().mapToDouble(Room::getTotalPrice).sum());
    }

    public void showClues() throws NoCluesException {
        if(this.inventoryClues.isEmpty()) {
            throw new NoCluesException("There are no clues at the moment.");
        }
        System.out.println("Registered clues:");
        this.inventoryClues.stream().map(c -> "ID: " + c.getId() + ", Name: " + c.getName() + ", Price: " + c.getPrice()).forEach(System.out::println);
        System.out.println("Total number of clues: " + this.inventoryClues.size());
        System.out.println("Total price: " + this.inventoryClues.stream().mapToDouble(Clue::getPrice).sum());
    }

    public void showDecoItems() throws NoDecoItemsException {
        if(this.inventoryDecoItems.isEmpty()) {
            throw new NoDecoItemsException("There are no decoration items at the moment.");
        }
        System.out.println("Registered decoration items:");
        this.inventoryDecoItems.stream().map(d -> "ID: " + d.getId() + ", Name: " + d.getName() + ", Price: " + d.getPrice()).forEach(System.out::println);
        System.out.println("Total number of decoration items: " + this.inventoryDecoItems.size());
        System.out.println("Total price: " + this.inventoryDecoItems.stream().mapToDouble(DecorationItem::getPrice).sum());
    }

    public void removeRoom() throws NoRoomsException {
        if(this.inventoryRooms.isEmpty()) {
            throw new NoRoomsException("There are no rooms at the moment.");
        }
        this.inventoryRooms.remove(0);
    }

    public void removeClue() throws NoCluesException {
        if(this.inventoryClues.isEmpty()) {
            throw new NoCluesException("There are no clues at the moment.");
        }
        this.inventoryClues.remove(0);
    }

    public void removeDecoItem() throws NoDecoItemsException {
        if(this.inventoryDecoItems.isEmpty()) {
            throw new NoDecoItemsException("There are no decoration items at the moment.");
        }
        this.inventoryDecoItems.remove(0);
    }

    public Room findRoom(String name) {
        Room room = this.inventoryRooms.stream().filter(r -> r.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        return room;
    }

}
