package mk.rezi.rezimk.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApartmentNotFoundException extends RuntimeException {

    public ApartmentNotFoundException(Long id) {
        super(String.format("Apartment with id: %d was not found", id));
    }
}
