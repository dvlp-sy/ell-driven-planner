package app.diary.domain.request;

import app.diary.domain.DiaryGenerator;
import app.diary.domain.enums.DiaryPermission;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

@RequiredArgsConstructor
public class DiaryCreateRequest {
    private final String directoryId;
    private final String writerId;
    private final String title;
    private final String content;
    private final DiaryPermission permission;

    public void validate() {
        Assert.hasText(directoryId, "Directory ID must not be empty");
        Assert.hasText(writerId, "Writer ID must not be empty");
        Assert.hasText(title, "Title must not be empty");
        Assert.hasText(content, "Content must not be empty");
        Assert.notNull(permission, "Permission must not be null");
    }

    public DiaryGenerator toGenerator() {
        return DiaryGenerator.of(directoryId, writerId, title, content, permission);
    }
}
