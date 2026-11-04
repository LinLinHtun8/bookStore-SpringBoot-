package a.a.bookstore.exceptionHandler;

public class categoryNotFoundException extends  RuntimeException{
    
    public  categoryNotFoundException(String message){
        super(message);
    }
}
