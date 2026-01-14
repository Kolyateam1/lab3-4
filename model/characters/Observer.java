package model.characters;

import java.util.List;

public interface Observer {
    void observe(Passenger target);
    void observeAll(List<Passenger> passengers);
}
