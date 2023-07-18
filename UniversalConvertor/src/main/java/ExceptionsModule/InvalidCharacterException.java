package ExceptionsModule;

public class InvalidCharacterException extends RuntimeException{
    public InvalidCharacterException(){
        super();
    }

    public InvalidCharacterException(String message){
        super(message);
    }
}
