package mk.rezi.rezimk.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TownNotFoundException extends RuntimeException {
    public TownNotFoundException(Long id) {
        super(String.format("Town with id: %d was not found", id));
    }
}
