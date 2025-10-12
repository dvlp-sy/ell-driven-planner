package app.diary.domain;

import app.diary.domain.document.DiaryDocument;
import app.diary.domain.enums.DiaryPermission;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Diary {
    private final String id;
    private final String directoryId;
    private final String writerId;
    private final String title;
    private final String content;
    private final DiaryPermission permission;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private Diary(String id, String directoryId, String writerId, String title, String content,
                  DiaryPermission permission, LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        this.id = id;
        this.directoryId = directoryId;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
        this.permission = permission;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Diary from(DiaryDocument diaryDocument) {
        return new Diary(diaryDocument.getId(), diaryDocument.getDirectoryId(),
                diaryDocument.getWriterId(), diaryDocument.getTitle(), diaryDocument.getContent(),
                diaryDocument.getPermission(), diaryDocument.getCreatedAt(), diaryDocument.getUpdatedAt());
    }
}
