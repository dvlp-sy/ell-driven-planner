package ell.app.directory.domain;

import ell.app.directory.domain.document.DirectoryDocument;
import ell.app.shared.domain.document.MongoDocumentGenerator;

public interface DirectoryGenerator extends MongoDocumentGenerator {
    @Override
    DirectoryDocument toDocument();
}
