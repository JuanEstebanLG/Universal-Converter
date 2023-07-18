package ExceptionsModule;

/**
 * Esta clase es la encargada de la creación de las excepciones usadas durante todo el proyecto.
 */
public class IncorrectTransformTypeException extends RuntimeException{

    public IncorrectTransformTypeException(){
        super();
    }

    public IncorrectTransformTypeException(String message){
        super(message);
    }
}
