package pl.factoryofthefuture.factorymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends NoSuchElementException {

    public NotFoundException(Long id) {
        super(String.format("Element with ID %d hasn't been found.", id));
    }
}