package Infraestructure.Exception;

public class InvalidFilePathException extends RuntimeException{

    public InvalidFilePathException(String message){
        super(message);
    }

    public InvalidFilePathException(String message, Throwable exception){
        super(message, exception);
    }
}
