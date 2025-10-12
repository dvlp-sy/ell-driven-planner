package ell.app.diary.application;

import ell.app.diary.domain.Diary;
import ell.app.diary.domain.DiaryGenerator;
import ell.app.diary.domain.DiaryUpdater;
import ell.app.diary.infrastructure.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public void createDiary(DiaryGenerator diaryGenerator) {
        diaryRepository.save(diaryGenerator.toDocument());
    }

    public Diary getDiary(String id) {
        return Diary.from(diaryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Diary not found with id: " + id)));
    }

    public void updateDiary(DiaryUpdater diaryUpdater) {
        diaryRepository.save(diaryUpdater.toDocument());
    }

    public void deleteDiary(String id) {
        diaryRepository.deleteById(id);
    }
}
