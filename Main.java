import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import enums.NewspaperType;
import exceptions.NoFreeSeatsException;
import exceptions.ObservationException;
import model.characters.DavilonCitizen;
import model.characters.FatShorty;
import model.characters.Goat;
import model.characters.Neznayka;
import model.characters.Passenger;
import model.characters.RegularPassenger;
import model.items.Newspaper;
import model.items.Suitcase;
import model.locations.Carriage;

public class Main {
    private static final Random random = new Random();

    private static int randomInRange(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }
    
    public static void main(String[] args) {
        
        System.out.println("СОЗДАНИЕ ПЕРСОНАЖЕЙ:");
        
        List<Passenger> allPassengers = new ArrayList<>();
        int id = 1;
        
        //незнайка
        String[] neznaykaNames = {"Незнайка", "Незнайка-проказник", "Незнайка-любопытный"};
        String neznaykaName = neznaykaNames[random.nextInt(neznaykaNames.length)];
        int neznaykaWeight = randomInRange(25, 44);
        Neznayka neznayka = new Neznayka(neznaykaName, neznaykaWeight, id++);
        allPassengers.add(neznayka);
        System.out.println("  Создан: " + neznayka);
        

        //козлик
        String[] goatNames = {"Козлик", "Козлик-любопытный", "Козлик-наблюдатель"};
        String goatName = goatNames[random.nextInt(goatNames.length)];
        int goatWeight = randomInRange(20, 34);
        int curiosityLevel = randomInRange(1, 10);
        Goat goat = new Goat(goatName, goatWeight, curiosityLevel, id++);
        allPassengers.add(goat);
        System.out.println("  Создан: " + goat + " (любопытство: " + goat.getCuriosityLevel() + ")");
        
        //толстенький коротышка
        int fatShortyCount = randomInRange(1, 2);
        for (int i = 0; i < fatShortyCount; i++) {
            String fatName = i == 0 ? "Толстенький коротышка" : "Толстячок-" + (i+1);
            int fatWeight = randomInRange(44, 70);
            FatShorty fatShorty = new FatShorty(fatName, fatWeight, id++);
            
            Suitcase suitcase = new Suitcase(
                "ЧМ-" + fatShorty.getId(), 
                random.nextInt(15) + 10,   
                "Коричневый"          
                );
            fatShorty.setSuitcase(suitcase);

            int paperCount = randomInRange(2, 4);
            for (int j = 0; j < paperCount; j++) {
                Newspaper paper = createRandomNewspaper();
                fatShorty.addNewspaper(paper);
            }
            
            allPassengers.add(fatShorty);
            System.out.println("  Создан: " + fatShorty + " (газет: " + paperCount + ")");
        }
        
        //давилонцы
        int davilonCount = randomInRange(2, 4);
        for (int i = 0; i < davilonCount; i++) {
            String citizenName = i == 0 ? "Давилонец" : "Житель-" + (i+1);
            int citizenWeight = randomInRange(35, 59);
            boolean buysFoolPaper = random.nextBoolean();
            DavilonCitizen citizen = new DavilonCitizen(citizenName, citizenWeight, buysFoolPaper, id++);
            allPassengers.add(citizen);
            System.out.println("  Создан: " + citizen + " (покупает газету для дураков: " + buysFoolPaper + ")");
        }
        
        //остальные пассажиры
        int regularCount =randomInRange(3, 6);
        for (int i = 0; i < regularCount; i++) {
            String regularName = "Пассажир-" + (i+1);
            int regularWeight = randomInRange(50, 70);
            RegularPassenger regular = new RegularPassenger(regularName, regularWeight, id++);
            allPassengers.add(regular);
            System.out.println("  Создан: " + regular);
        }
        
        System.out.println("Всего создано персонажей: " + allPassengers.size());
        

        System.out.println("\nСОЗДАНИЕ ВАГОНА:");
        
        int capacity = randomInRange(8, 15);
        String carriageNumber = "ВА-" + (randomInRange(10, 59));
        
        Carriage carriage = new Carriage(carriageNumber, capacity);
        System.out.println("Создан вагон " + carriageNumber + " вместимостью " + capacity + " мест");
        
        System.out.println("\nЗАПОЛНЕНИЕ ВАГОНА:");
        
        // Перемешиваем список для случайного порядка
        List<Passenger> shuffled = new ArrayList<>(allPassengers);
        for (int i = 0; i < shuffled.size(); i++) {
            int swapIndex = random.nextInt(shuffled.size());
            Passenger temp = shuffled.get(i);
            shuffled.set(i, shuffled.get(swapIndex));
            shuffled.set(swapIndex, temp);
        }
        
        // Добавляем пассажиров в вагон
        int added = 0;
        for (Passenger passenger : shuffled) {
            try {
                carriage.addPassenger(passenger);
                added++;
            } catch (NoFreeSeatsException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
        
        System.out.println("В вагоне: " + added + " пассажиров, свободных мест: " + carriage.getFreeSeats());
        

        System.out.println("\nПассажиры входят в вагон:");
        for (Passenger passenger : allPassengers) {
            passenger.enterCarriage();
            
            if (random.nextBoolean()) {
                passenger.takeSeat();
            }
        }
        
        neznayka.climbToShelf();
        goat.climbToShelf();
        
        neznayka.setHidden(random.nextBoolean());
        
        //толстенький коротышка
        for (Passenger p : allPassengers) {
            if (p instanceof FatShorty) {
                FatShorty fatShorty = (FatShorty) p;

                fatShorty.putSuitcaseUnderSeat();
                Suitcase suitcase = new Suitcase(
                "ЧЕМ-" + fatShorty.getId(),
                random.nextInt(15) + 10,
                random.nextBoolean() ? "Коричневый" : "Черный");
                fatShorty.setSuitcase(suitcase);
                
                if (fatShorty.hasNewspapers()) {
                    fatShorty.takeNewspapersFromPocket();
                    fatShorty.readRandomNewspaper();
                } else {
                    System.out.println(fatShorty.getName() + " не имеет газет :(");
                }
            }
        }
        
        System.out.println("\nДавилонские жители покупают газеты:");
        for (Passenger p : allPassengers) {
            if (p instanceof DavilonCitizen) {
                DavilonCitizen citizen = (DavilonCitizen) p;
                if (citizen.isBuysFoolNewspaper() || random.nextBoolean()) {
                    Newspaper paper = createRandomNewspaper();
                    citizen.buyNewspaper(paper);
                }
            }
        }

        
        System.out.println("\nНАБЛЮДЕНИЕ ЗА ПАССАЖИРАМИ:");
        
        List<Passenger> inCarriage = carriage.getAllPassengers();
        
        // Незнайка наблюдает
        if (inCarriage.contains(neznayka)) {
            System.out.println("Незнайка наблюдает:");
            neznayka.observeAll(inCarriage);
        }
        
        // Козлик наблюдает
        if (inCarriage.contains(goat)) {
            System.out.println("\nКозлик наблюдает (любопытство: " + goat.getCuriosityLevel() + "):");
            // Случайно выбирает кого наблюдать
            if (!inCarriage.isEmpty()) {
                for (int i = 0; i < Math.min(3, inCarriage.size()); i++) {
                    Passenger target = inCarriage.get(random.nextInt(inCarriage.size()));
                    if (!target.equals(goat)) {
                        goat.observe(target);
                    }
                }
            }
            
            // Случайно может стать более любопытным
            if (random.nextBoolean()) {
                goat.increaseCuriosity();
                System.out.println("Козлик стал более любопытным: " + goat.getCuriosityLevel());
            }
        }
        
        // 6.2 ObservationException
        System.out.println("\nПопытка наблюдать за null:");
        try {
            neznayka.observe(null);
        } catch (ObservationException e) {
            System.out.println("  Поймано исключение: " + e.getMessage());
        }
    }
    
    //Создает случайную газету
    private static Newspaper createRandomNewspaper() {
        NewspaperType type;
        
        // 40% шанс создать "газету для дураков", 60% - случайную другую
        if (random.nextInt(100) < 40) {
            type = NewspaperType.FOR_FOOLS;
        } else {
            NewspaperType[] types = NewspaperType.values();
            type = types[random.nextInt(types.length)];
        }
        
        String title;

        if (type == NewspaperType.FOR_FOOLS){
            title = "Газета для дураков";
        } else{
            String[] titles = {
            "Утренний выпуск", "Вечерний дайджест", "Спецвыпуск", 
            "Экспресс-новости", "Информационный бюллетень", "Сводка"};
        title = titles[random.nextInt(titles.length)];
        }   

        // "Газета для дураков" всегда понятна, остальные - случайно
        boolean intelligible = (type == NewspaperType.FOR_FOOLS) ? true : random.nextBoolean();
        
        return new Newspaper(type, title, intelligible);
    }

}
