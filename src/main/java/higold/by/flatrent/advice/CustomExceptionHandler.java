package higold.by.flatrent.advice;

import higold.by.flatrent.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException exception) {
        Map<String, String> map = new HashMap<>();
        exception.getBindingResult()
                 .getFieldErrors()
                 .forEach(fieldError ->
                                  map.put(fieldError.getField(), fieldError.getDefaultMessage()));

        return map;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public Map<String, String> handleUserAlreadyExistsException(
            UserAlreadyExistsException exception) {
        Map<String, String> map = new HashMap<>();
        map.put("error", "User already exists");

        return map;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({BadCredentialsException.class, UsernameNotFoundException.class})
    public Map<String, String> handleBadCredentialsException() {
        Map<String, String> map = new HashMap<>();
        map.put("error", "Wrong email or password");

        return map;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public Map<String, String> handleAccessDeniedException(AccessDeniedException exception) {
        Map<String, String> map = new HashMap<>();
        map.put("error", exception.getMessage());

        return map;
    }
}

