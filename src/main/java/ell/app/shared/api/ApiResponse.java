package ell.app.shared.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@JsonPropertyOrder({"code", "message", "data", "isSuccess"})
public class ApiResponse<T> {

    private final Boolean isSuccess;
    private final String code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    private ApiResponse(Boolean isSuccess, String code, String message, T data) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseEntity<ApiResponse<T>> onSuccess(ApiResponseCode response, T data) {
        return ResponseEntity
                .status(response.getStatus().value())
                .body(new ApiResponse<>(true, response.getCode(), response.getMessage(), data));
    }

    public static ResponseEntity<ApiResponse<Void>> onSuccess(ApiResponseCode response) {
        return ResponseEntity
                .status(response.getStatus().value())
                .body(new ApiResponse<>(true, response.getCode(), response.getMessage(), null));
    }

    public static <T> ResponseEntity<ApiResponse<T>> onFailure(ApiResponseCode response, T data) {
        return ResponseEntity
                .status(response.getStatus().value())
                .body(new ApiResponse<>(false, response.getCode(), response.getMessage(), data));
    }

    public static ResponseEntity<ApiResponse<Void>> onFailure(ApiResponseCode response) {
        return ResponseEntity
                .status(response.getStatus().value())
                .body(new ApiResponse<>(false, response.getCode(), response.getMessage(), null));
    }
 }
