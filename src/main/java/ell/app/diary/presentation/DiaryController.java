package ell.app.diary.presentation;

import ell.app.diary.application.DiaryService;
import ell.app.diary.domain.request.DiaryCreateRequest;
import ell.app.diary.presentation.api.DiaryResponseCode;
import ell.app.shared.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diaries")
@Tag(name = "DIARY", description = "다이어리 관련 API")
public class DiaryController {

    private final DiaryService diaryService;

    @Operation(summary = "다이어리 생성", description = "다이어리를 생성합니다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createDiary(@RequestBody DiaryCreateRequest diaryCreateRequest) {
        diaryCreateRequest.validate();
        diaryService.createDiary(diaryCreateRequest.toGenerator());
        return ApiResponse.onSuccess(DiaryResponseCode.DIARY_CREATED);
    }
}
