package model.items;

import java.util.Objects;

public class Suitcase {
    private final String id;
    private final int size;
    private final String color;
    
    public Suitcase(String id, int size, String color) {
        this.id = id;
        this.size = size;
        this.color = color;
    }

    public void putUnderSeat() {
        System.out.println("Чемодан " + id + " помещен под сиденье");
    }

    public String getId() { return id; }
    public int getSize() { return size; }
    public String getColor() { return color; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Suitcase suitcase = (Suitcase) o;
        return Objects.equals(id, suitcase.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Suitcase{id='" + id + "', size=" + size + ", color='" + color + "'}";
    }
}
