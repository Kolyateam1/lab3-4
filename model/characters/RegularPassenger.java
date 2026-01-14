package model.characters;
import java.util.Random;

public class RegularPassenger extends Passenger{
    public RegularPassenger(String name, int weight, int id) {
        super(name, weight, id);
    }
    
    private static final Random random = new Random();

    @Override
    public void enterCarriage() {
        String[] variations = {
                "спокойно входит в вагон",
                "торопливо входит в вагон", 
                "заходит в вагон, оглядываясь",
                "медленно входит в вагон"
        };
        System.out.println(getName() + " " + variations[random.nextInt(variations.length)]);
    }
    
    @Override
    public void takeSeat() {
        String[] variations = {
                "занимает место",
                "садится на свободное место",
                "устраивается поудобнее",
                "присаживается"
            };
            System.out.println(getName() + " " + variations[random.nextInt(variations.length)]);
    }
}
