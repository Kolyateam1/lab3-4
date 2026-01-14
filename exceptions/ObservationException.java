package exceptions;

public class ObservationException extends RuntimeException{
    private final String observerName;

    public ObservationException(String message, String observerName) {
        super(message);
        this.observerName = observerName;
    }

    @Override
    public String getMessage() {
        return "Ошибка наблюдения от " + observerName + ":" + super.getMessage();
    }
}
