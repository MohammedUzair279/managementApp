package com.jktech.managementApp.mangementApplication.controller;


import com.jktech.managementApp.mangementApplication.model.Document;
import com.jktech.managementApp.mangementApplication.service.DocumentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/document")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Document> upload(@RequestParam String title,
                                           @RequestParam String content,
                                           @RequestParam String author,
                                           @RequestParam String type){
        return ResponseEntity.ok(documentService.saveDocument(title, content, author, type));
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<Document>> filter(@RequestParam(required = false) String author,
                                       @RequestParam(required = false) String type,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(documentService.filterDocuments(author,type, PageRequest.of(page,size)));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Document>> search(@RequestParam String keyword){
        return ResponseEntity.ok(documentService.searchByKeyword(keyword));
    }
}
