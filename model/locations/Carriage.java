package model.locations;

import model.characters.Passenger;
import exceptions.NoFreeSeatsException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Carriage {
    private final String carriageNumber;
    private final int capacity;
    private int currentOccupancy;
    private List<Passenger> passengers;

    public Carriage(String carriageNumber, int capacity) {
        this.carriageNumber = carriageNumber;
        this.capacity = capacity;
        this.currentOccupancy = 0;
        this.passengers = new ArrayList<>();
    }

    public void addPassenger(Passenger passenger) throws NoFreeSeatsException {
        if (currentOccupancy >= capacity) {
            throw new NoFreeSeatsException(carriageNumber);
        }
        
        passengers.add(passenger);
        currentOccupancy++;
        System.out.println(passenger.getName() + " сел в вагон " + carriageNumber);
    }
    
    public boolean removePassenger(Passenger passenger) {
        boolean removed = passengers.remove(passenger);
        if (removed) {
            currentOccupancy--;
        }
        return removed;
    }
    
    public void fillRandomly(List<Passenger> passengerList) {
        Collections.shuffle(passengerList);
        int seatsToFill = Math.min(capacity, passengerList.size());
        
        System.out.println("Случайное заполнение вагона " + carriageNumber + ":");
        
        for (int i = 0; i < seatsToFill; i++) {
            try {
                addPassenger(passengerList.get(i));
            } catch (NoFreeSeatsException e) {
                System.out.println("Ошибка: " + e.getMessage());
                break;
            }
        }
    }
    
    public List<Passenger> getAllPassengers() {
        return new ArrayList<>(passengers);
    }
    
    public int getFreeSeats() {
        return capacity - currentOccupancy;
    }
    
    public String getCarriageNumber() { return carriageNumber; }
    public int getCapacity() { return capacity; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carriage carriage = (Carriage) o;
        return Objects.equals(carriageNumber, carriage.carriageNumber);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(carriageNumber);
    }
    
    @Override
    public String toString() {
        return "Carriage{number='" + carriageNumber + "', capacity=" + capacity + ", occupied=" + currentOccupancy + "/" + capacity + "}";
    }
}
