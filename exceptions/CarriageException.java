package exceptions;

public class CarriageException extends Exception{
    private final String carriageNumber;

    public CarriageException(String message, String carriageNumber) {
        super(message);
        this.carriageNumber = carriageNumber;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "[Вагон: " + carriageNumber + "]"; 
    }
}
