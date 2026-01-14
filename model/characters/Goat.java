package model.characters;

import java.util.List;

public class Goat extends Passenger implements Observer{
    private int curiosityLevel; //уровень любопытсва

    public Goat(String name, int weight, int curiosityLevel, int id) {
        super(name, weight, id);    
        this.curiosityLevel = Math.max(1, Math.min(curiosityLevel, 10));
    }

    @Override
    public void enterCarriage() {
        System.out.println(name + " прыгает в вагон с любопытством " + curiosityLevel);
    }

    public void climbToShelf(){
        System.out.println(name + " забирается на верхнюю полку");
    }

    @Override
    public void takeSeat() {
        System.out.println(name + "Растягивается на своем месте");
    }

    @Override
    public void observe(Passenger target) {
        String observation = curiosityLevel > 7 ? "внимаельно изучает" : curiosityLevel > 4 ? "смотрит на" : "мельком взглянул на" ;
        System.out.println(name + " " + observation + " " + target.getName());
    }

    @Override
    public void observeAll(List<Passenger> passengers) {
        System.out.println(name + " с любопытсвом остматривет всех (" + curiosityLevel + "/10)");

        for (Passenger pas : passengers){
            if (!pas.equals(passengers)) {
                observe(pas);
            }
        }
    }

    public void increaseCuriosity() {
        if (curiosityLevel < 10) {
            curiosityLevel++;
            System.out.println(name + " стал более любопытным (" + curiosityLevel + "/10)");
        }
    }
    public int getCuriosityLevel() { return curiosityLevel;}
}