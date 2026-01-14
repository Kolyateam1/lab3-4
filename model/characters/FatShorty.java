package model.characters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import model.items.Newspaper;
import model.items.Suitcase;


public class FatShorty extends Passenger {
    private final List<Newspaper> newspapers;
    private Newspaper currentNewspaper;
    private Suitcase suitcase;
    private final Random random = new Random();
    
    public FatShorty(String name, int weight, int id) {
        super(name, weight, id);
        this.newspapers = new ArrayList<>();
        this.currentNewspaper = null;
        this.suitcase = null;
    }
    
    @Override
    public void enterCarriage() {
        System.out.println(name + " тяжело входит в вагон" + (suitcase != null ? " с чемоданом" : ""));
    }
    

    @Override
    public void takeSeat() {
        System.out.println(name + " занимает место под полкой");
    }
    

    public void addNewspaper(Newspaper newspaper) {
        if (newspaper != null) {
            newspapers.add(newspaper);
        }
    }
    
    public void takeNewspapersFromPocket() {
        System.out.println(name + " достает газеты из кармана:");
        if (newspapers.isEmpty()) {
            System.out.println("  В кармане пусто!");
        } else {
            for (Newspaper paper : newspapers) {
                System.out.println("  - " + paper.title() + " (" + paper.type().getDisplayName() + ")");
            }
        }
    }
    
    public void readNewspaper(Newspaper newspaper) {
        this.currentNewspaper = newspaper;
        System.out.println(name + " читает: " + newspaper.title());
        
        // Особенность из текста: газета для дураков очень понятная
        if (newspaper.isForFools()) {
            System.out.println("  (Газета очень понятная, даже для дураков)");
        }
    }
    

    public void readRandomNewspaper() {
        if (!newspapers.isEmpty()) {
            Newspaper randomPaper = newspapers.get(random.nextInt(newspapers.size()));
            readNewspaper(randomPaper);
        } else {
            System.out.println(name + " не имеет газет для чтения");
        }
    }
    

    public void putSuitcaseUnderSeat() {
        if (suitcase != null) {
            suitcase.putUnderSeat();
            System.out.println(name + " положил чемодан под сиденье");
        } else {
            System.out.println(name + " не имеет чемодана");
        }
    }
    

    public List<Newspaper> getNewspapers() { 
        return new ArrayList<>(newspapers);
    }
    
    public Newspaper getCurrentNewspaper() {
        return currentNewspaper;
    }
    
    public void setSuitcase(Suitcase suitcase) {
        this.suitcase = suitcase;
    }
    
    public Suitcase getSuitcase() {
        return suitcase;
    }
    
    public boolean hasSuitcase() {
        return suitcase != null;
    }
    
    public boolean hasNewspapers() {
        return !newspapers.isEmpty();
    }
    
    public int getNewspaperCount() {
        return newspapers.size();
    }
    
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FatShorty)) return false;
    
        FatShorty other = (FatShorty) obj;
        return this.id == other.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return super.toString() + " [газет: " + newspapers.size() + 
               (suitcase != null ? ", с чемоданом" : "") + "]";
    }
}