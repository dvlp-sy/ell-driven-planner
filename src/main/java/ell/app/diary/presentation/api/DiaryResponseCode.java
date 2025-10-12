package ell.app.diary.presentation.api;

import ell.app.shared.api.ApiResponseCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum DiaryResponseCode implements ApiResponseCode {
    DIARY_CREATED(HttpStatus.CREATED, "DIARY2001", "다이어리 생성 완료"),
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;

    DiaryResponseCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
