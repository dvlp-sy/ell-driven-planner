package app.diary.domain;

import app.diary.domain.document.DiaryDocument;
import app.diary.domain.enums.DiaryPermission;
import app.shared.domain.document.MongoDocumentUpdater;

import java.time.LocalDateTime;

public class DiaryUpdater implements MongoDocumentUpdater {
    private final String id;
    private final String directoryId;
    private final String writerId;
    private final String title;
    private final String content;
    private final DiaryPermission permission;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private DiaryUpdater(String id, String directoryId, String writerId, String title, String content,
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

    public static DiaryUpdater ofWithDirectoryId(Diary diary, String directoryId) {
        return new DiaryUpdater(diary.getId(), directoryId, diary.getWriterId(), diary.getTitle(),
                diary.getContent(), diary.getPermission(), diary.getCreatedAt(), LocalDateTime.now());
    }

    public static DiaryUpdater ofWithDiaryInfo(Diary diary, String title, String content) {
        return new DiaryUpdater(diary.getId(), diary.getDirectoryId(), diary.getWriterId(), title,
                content, diary.getPermission(), diary.getCreatedAt(), LocalDateTime.now());
    }

    public static DiaryUpdater ofWithPermission(Diary diary, DiaryPermission permission) {
        return new DiaryUpdater(diary.getId(), diary.getDirectoryId(), diary.getWriterId(), diary.getTitle(),
                diary.getContent(), permission, diary.getCreatedAt(), LocalDateTime.now());
    }

    @Override
    public DiaryDocument toDocument() {
        return new DiaryDocument(id, directoryId, writerId, title, content, permission, createdAt, updatedAt);
    }
}
