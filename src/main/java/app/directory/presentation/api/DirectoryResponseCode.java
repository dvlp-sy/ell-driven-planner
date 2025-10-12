package app.directory.presentation.api;

import app.shared.api.ApiResponseCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum DirectoryResponseCode implements ApiResponseCode {
    DIRECTORY_CREATED(HttpStatus.CREATED, "DIRECTORY2001", "디렉토리 생성 완료"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

    DirectoryResponseCode(HttpStatus staus, String code, String message) {
        this.status = staus;
        this.code = code;
        this.message = message;
    }
}
