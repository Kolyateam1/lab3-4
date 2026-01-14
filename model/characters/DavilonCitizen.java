package model.characters;

import model.items.Newspaper;

public class DavilonCitizen extends Passenger{
    private boolean buysFoolNewspaper;
    
    public DavilonCitizen(String name, int weight, boolean buysFoolNewspaper, int id) {
        super(name, weight, id);
        this.buysFoolNewspaper = buysFoolNewspaper;
    }
    
    @Override
    public void enterCarriage() {
        System.out.println(name + " (давилонский житель) входит в вагон");
    }
    
    @Override
    public void takeSeat() {
        System.out.println(name + " занимает место");
    }
    
    public void buyNewspaper(Newspaper newspaper) {
        System.out.println(name + " покупает газету: " + newspaper.title());
        
        if (newspaper.isForFools()) {
            System.out.println("  " + explainPurchase());
        }
    }
    
    public String explainPurchase() {
        return "Покупаю не потому что считаю себя дураком, а потому что интересно узнать, о чем там пишут!";
    }
    
    public boolean isBuysFoolNewspaper() {
        return buysFoolNewspaper;
    }
}