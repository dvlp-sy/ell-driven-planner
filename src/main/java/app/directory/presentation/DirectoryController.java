package app.directory.presentation;

import app.directory.application.DirectoryService;
import app.directory.domain.DirectoryGenerator;
import app.directory.presentation.api.DirectoryResponseCode;
import app.shared.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/directories")
@Tag(name = "DIRECTORY", description = "디렉토리 관련 API")
public class DirectoryController {

    private final DirectoryService directoryService;

    @Operation(summary = "디렉토리 생성", description = "디렉토리를 생성합니다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createDirectory(
            @RequestParam(required = false) String groupId,
            @RequestParam(required = false) String parentId, @RequestParam String name
    ) {
        directoryService.createDirectory(DirectoryGenerator.of(groupId, parentId, name));
        return ApiResponse.onSuccess(DirectoryResponseCode.DIRECTORY_CREATED);
    }
}
