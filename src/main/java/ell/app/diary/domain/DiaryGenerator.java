package ell.app.diary.domain;

import ell.app.diary.domain.document.DiaryDocument;
import ell.app.diary.domain.enums.DiaryPermission;
import ell.app.shared.domain.document.MongoDocumentGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

public class DiaryGenerator implements MongoDocumentGenerator {
    private final String directoryId;
    private final String writerId;
    private final String title;
    private final String content;
    private final DiaryPermission permission;

    private DiaryGenerator(String directoryId, String writerId, String title, String content, DiaryPermission permission) {
        this.directoryId = directoryId;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
        this.permission = permission;
    }

    public static DiaryGenerator of(String directoryId, String writerId, String title, String content, DiaryPermission permission) {
        return new DiaryGenerator(directoryId, writerId, title, content, permission);
    }

    @Override
    public DiaryDocument toDocument() {
        return new DiaryDocument(UUID.randomUUID().toString(), directoryId, writerId,
                title, content, permission, LocalDateTime.now(), LocalDateTime.now());
    }
}
