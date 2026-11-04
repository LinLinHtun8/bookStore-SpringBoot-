package a.a.bookstore.exceptionHandler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice 
public class GlobalExceptionHandler {

    @ExceptionHandler (Exception.class)
    public  String ExceptionHandler(Exception e, Model model){
        model.addAttribute("eMessage", e.getMessage());
        return "error";

    }

    @ExceptionHandler (categoryNotFoundException.class)
    public  String CategoryNotFoundExceptionHandler(Exception e, Model model){
        model.addAttribute("eMessage", e.getMessage());
        model.addAttribute("eCode", "404");
        return  "error";    }
    
}
