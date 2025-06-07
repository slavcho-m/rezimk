package mk.rezi.rezimk.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TownWithNameNotFoundException extends RuntimeException {
    public TownWithNameNotFoundException(String name) {
        super(String.format("Town with name: %s was not found", name));
    }
}
