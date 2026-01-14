package model.locations;

import model.characters.Passenger;
import java.util.Objects;

public class Shelf {
    private final int shelfId;
    private boolean isOccupied;
    private Passenger passenger;
    
    public Shelf(int shelfId) {
        this.shelfId = shelfId;
        this.isOccupied = false;
        this.passenger = null; 
    }
    
    public boolean occupy(Passenger passenger) {
        if (isOccupied) {
            return false;
        }
        this.passenger = passenger;
        this.isOccupied = true;
        System.out.println("Полка " + shelfId + " занята пассажиром " + passenger.getName());
        return true;
    }
    
    public boolean vacate() {
        if (!isOccupied) {
            return false;
        }
        System.out.println("Полка " + shelfId + " освобождена");
        this.passenger = null;
        this.isOccupied = false;
        return true;
    }
    
    public boolean isOccupied() {
        return isOccupied;
    }
    
    public int getShelfId() { return shelfId; }
    public Passenger getPassenger() { return passenger; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shelf shelf = (Shelf) o;
        return shelfId == shelf.shelfId;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(shelfId);
    }
    
    @Override
    public String toString() {
        return "Shelf{id=" + shelfId + ", occupied=" + isOccupied + (passenger != null ? ", passenger=" + passenger.getName() : "") + "}";
    }
}
