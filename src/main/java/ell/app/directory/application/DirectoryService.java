package ell.app.directory.application;

import ell.app.directory.domain.Directory;
import ell.app.directory.domain.DirectoryGenerator;
import ell.app.directory.domain.DirectoryUpdater;
import ell.app.directory.infrastructure.DirectoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectoryService {

    private final DirectoryRepository directoryRepository;

    public void createDirectory(DirectoryGenerator directoryGenerator) {
        directoryRepository.save(directoryGenerator.toDocument());
    }

    public Directory getDirectory(String id) {
        return Directory.from(directoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Directory not found with id: " + id)));
    }

    public void updateDirectory(DirectoryUpdater directoryUpdater) {
        directoryRepository.save(directoryUpdater.toDocument());
    }

    public void deleteDirectory(String id) {
        directoryRepository.deleteById(id);
    }
}
