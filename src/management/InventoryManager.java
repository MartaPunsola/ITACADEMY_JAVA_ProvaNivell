package management;

import model.*;
import enums.Material;
import enums.Theme;
import exception.*;

import java.util.ArrayList;
import java.util.List;

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
        Room roomFound = null;
        try {
            roomFound = this.findRoom(name);
        } catch (RoomNotFoundException e) {
            System.out.println("The name " + r.getName() + " is available. Creating room...");
        }

        if(roomFound != null) {
            throw new DuplicatedRoomException("A room with the name " + r.getName() + " already exists.");
        }
        this.inventoryRooms.add(r);
    }

    public void addClue(Room room, Clue clue) {
        room.getClues().add(clue);
        room.setTotalPrice(room.getTotalPrice() + clue.getPrice());
    }

    public void addDecoItem(Room room, DecorationItem decoItem) {
        room.getDecorationItems().add(decoItem);
        room.setTotalPrice(room.getTotalPrice() + decoItem.getPrice());
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

    public List<Room> showRooms() throws NoRoomsException {
        List<Room> rooms = this.inventoryRooms;
        if(rooms.isEmpty()) {
            throw new NoRoomsException("There are no rooms at the moment.");
        }

        this.inventoryRooms.stream().map(r -> "ID: " + r.getRoomId() + ", Name: " + r.getName() + ", Price: " + r.getTotalPrice()).forEach(System.out::println);
        return rooms;
    }

    public List<Clue> showClues() throws NoCluesException {
        List<Clue> clues = this.inventoryClues;
        if(clues.isEmpty()) {
            throw new NoCluesException("There are no clues at the moment.");
        }

        this.inventoryClues.stream().map(c -> "ID: " + c.getId() + ", Name: " + c.getName() + ", Price: " + c.getPrice()).forEach(System.out::println);
        return clues;
    }

    public List<DecorationItem> showDecoItems() throws NoDecoItemsException {
        List<DecorationItem> decoItems = this.inventoryDecoItems;
        if(this.inventoryDecoItems.isEmpty()) {
            throw new NoDecoItemsException("There are no decoration items at the moment.");
        }

        this.inventoryDecoItems.stream().map(d -> "ID: " + d.getId() + ", Name: " + d.getName() + ", Price: " + d.getPrice()).forEach(System.out::println);
        return decoItems;
    }

    public void removeRoom(Room room) throws NoRoomsException {
        if(this.inventoryRooms.isEmpty()) {
            throw new NoRoomsException("There are no rooms at the moment.");
        }
        this.inventoryRooms.remove(room);
    }

    public void removeClue(Clue clue) throws NoCluesException {
        if(this.inventoryClues.isEmpty()) {
            throw new NoCluesException("There are no clues at the moment.");
        }
        this.inventoryClues.remove(clue);
    }

    public void removeDecoItem(DecorationItem decoItem) throws NoDecoItemsException {
        if(this.inventoryDecoItems.isEmpty()) {
            throw new NoDecoItemsException("There are no decoration items at the moment.");
        }
        this.inventoryDecoItems.remove(decoItem);
    }

    public Room findRoom(String name) throws RoomNotFoundException {
        Room room = this.inventoryRooms.stream().filter(r -> r.getName().equalsIgnoreCase(name)).findFirst().orElseThrow(() ->
                new RoomNotFoundException("This room is not in the system."));
        return room;
    }

    public Clue findClue(String name) throws ClueNotFoundException {
        Clue clue = this.inventoryClues.stream().filter(r -> r.getName().equalsIgnoreCase(name)).findFirst().orElseThrow(() ->
                new ClueNotFoundException("This clue is not in the system."));
        return clue;
    }

    public DecorationItem findDecoItem(String name) throws DecoItemNotFoundException {
        DecorationItem decoItem = this.inventoryDecoItems.stream().filter(r -> r.getName().equalsIgnoreCase(name)).findFirst().orElseThrow(() ->
                new DecoItemNotFoundException("This decoration item is not in the system."));
        return decoItem;
    }

}
