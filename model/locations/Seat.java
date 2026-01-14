package model.locations;

import model.characters.Passenger;
import model.items.Suitcase;
import java.util.Objects;

public class Seat {
    private final int seatNumber;
    private boolean isOccupied;
    private Passenger passenger;
    private Suitcase suitcaseUnderneath;
    
    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.isOccupied = false;
        this.passenger = null;
        this.suitcaseUnderneath = null;
    }
    
    public boolean occupy(Passenger passenger) {
        if (isOccupied) {
            return false;
        }
        this.passenger = passenger;
        this.isOccupied = true;
        System.out.println("Сиденье " + seatNumber + " занято пассажиром " + passenger.getName());
        return true;
    }
    
    public boolean vacate() {
        if (!isOccupied) {
            return false;
        }
        System.out.println("Сиденье " + seatNumber + " освобождено");
        this.passenger = null;
        this.isOccupied = false;
        return true;
    }
    
    public boolean putSuitcase(Suitcase suitcase) {
        if (suitcaseUnderneath != null) {
            return false;
        }
        this.suitcaseUnderneath = suitcase;
        System.out.println("Чемодан помещен под сиденье " + seatNumber);
        return true;
    }
    
    public boolean removeSuitcase() {
        if (suitcaseUnderneath == null) {
            return false;
        }
        System.out.println("Чемодан убран из-под сиденья " + seatNumber);
        this.suitcaseUnderneath = null;
        return true;
    }
    
    public int getSeatNumber() { return seatNumber; }
    public boolean isOccupied() { return isOccupied; }
    public Passenger getPassenger() { return passenger; }
    public Suitcase getSuitcaseUnderneath() { return suitcaseUnderneath; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return seatNumber == seat.seatNumber;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(seatNumber);
    }
    
    @Override
    public String toString() {
        return "Seat{number=" + seatNumber + ", occupied=" + isOccupied + (passenger != null ? ", passenger=" + passenger.getName() : "") + "}";
    }
}
