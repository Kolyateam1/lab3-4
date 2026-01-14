package model.characters;

import java.util.Objects;

public abstract class Passenger {
    protected final String name;
    protected final int weight;
    protected final int id;

    public Passenger(String name, int weight, int id){
        this.name = name;
        this.weight = weight;
        this.id = id;
    }

    public abstract void enterCarriage();
    public abstract void takeSeat();

    public String getName() {return name;}
    public int getWeight() {return weight;}
    public int getID() {return id;}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Passenger passenger = (Passenger) obj;
        return id == passenger.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{name='" + name + "', weight=" + weight + "}";
    }
}
