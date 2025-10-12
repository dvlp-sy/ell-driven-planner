package ell.app.shared.api;

import org.springframework.http.HttpStatus;

public interface ApiResponseCode {
    HttpStatus getStatus();
    String getCode();
    String getMessage();
}
