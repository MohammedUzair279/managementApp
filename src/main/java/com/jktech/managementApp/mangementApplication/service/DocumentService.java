package com.jktech.managementApp.mangementApplication.service;

import com.jktech.managementApp.mangementApplication.model.Document;
import com.jktech.managementApp.mangementApplication.repository.DocumentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document saveDocument(String title, String content, String author, String type){
        Document doc = new Document(null, title, content, author, type, LocalDateTime.now());
        return documentRepository.save(doc);
    }

    public Page<Document> filterDocuments(String author, String type, Pageable pageable){
        if (author != null) return documentRepository.findByAuthorContainingIgnoreCase(author, pageable);
        if (type != null) return documentRepository.findByTypeContainingIgnoreCase(type, pageable);
        return documentRepository.findAll(pageable);
    }

    public List<Document> searchByKeyword(String keyword){
        return documentRepository.findByContentContainingIgnoreCase(keyword, Pageable.unpaged()).getContent();
    }
}
