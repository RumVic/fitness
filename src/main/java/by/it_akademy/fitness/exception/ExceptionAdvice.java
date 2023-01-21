package by.it_akademy.fitness.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.OptimisticLockException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OptimisticLockException.class)
    public ResponseEntity<SingleErrorResponse> processingException (OptimisticLockException e) {
        SingleErrorResponse response = new SingleErrorResponse();
        response.setLogref("error");
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LockException.class)
    public ResponseEntity<SingleErrorResponse> processingException (LockException l){
        SingleErrorResponse response = new SingleErrorResponse();
        response.setLogref("error");
        response.setMessage(l.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<SingleErrorResponse> processingException (IllegalStateException i){
        SingleErrorResponse response = new SingleErrorResponse();
        response.setLogref("error");
        response.setMessage(i.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<SingleErrorResponse> processingException (NoSuchElementException n){
        SingleErrorResponse response = new SingleErrorResponse();
        response.setLogref("error");
        response.setMessage(n.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

}
