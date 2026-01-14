package model.characters;

import exceptions.ObservationException;
import java.util.List;

public class Neznayka extends Passenger implements Observer{
    private boolean isHidden; //скрытость

    public Neznayka(String name, int weight, int id) {
        super(name, weight, id);
        this.isHidden = true;
    }
    
    @Override
    public void enterCarriage() {
        System.out.println(name + " Топает к своему месту");
    }

    public void climbToShelf(){
        System.out.println(name + " забирается на верхнюю полку");
        isHidden = true;
    }

    @Override
    public void takeSeat() {
        System.out.println(name + " Растягивается на своем месте");
    }

    @Override
    public void observe(Passenger target) {
        if (target == null) {
            throw new ObservationException("Магия вне Хогварца запрещена, пустым пассажирам нельзя ездить зайцем", name);
        }

        if (isHidden) {
            System.out.println(name + " украдкой наблюдает за " + target.getName());
        } else {
            System.out.println(name + " открыто наблюдает за " + target.getName());
        }
    }
    @Override
    public void observeAll(List<Passenger> passengers) {
        System.out.println(name + " наблюдает за всеми пассажирами");
        for (Passenger pas : passengers){
            if (!pas.equals(passengers)) {
                observe(pas);
            }
        }
    }

    public boolean isHidden() { return isHidden; }
    public void setHidden(boolean hidden) { isHidden = hidden; }
}
