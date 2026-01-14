package exceptions;

public class NoFreeSeatsException extends CarriageException{
    public NoFreeSeatsException(String carriageNumber) {
        super("Упс, билетов оказалось больше, чем мест в вагоне:)", carriageNumber);
    }
}
