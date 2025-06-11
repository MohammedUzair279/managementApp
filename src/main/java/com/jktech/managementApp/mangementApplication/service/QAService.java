package com.jktech.managementApp.mangementApplication.service;

import com.jktech.managementApp.mangementApplication.model.Document;
import com.jktech.managementApp.mangementApplication.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QAService {
    private final DocumentRepository documentRepository;

    public QAService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<Document> answerQuestion(String keyword){
        return documentRepository.findByContentContainingIgnoreCase(keyword, null).getContent();
    }
}
