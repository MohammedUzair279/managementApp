package com.jktech.managementApp.mangementApplication.controller;

import com.jktech.managementApp.mangementApplication.dto.QuestionRequest;
import com.jktech.managementApp.mangementApplication.model.Document;
import com.jktech.managementApp.mangementApplication.service.QAService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qa")
public class QAController {
    private final QAService qaService;

    public QAController(QAService qaService) {
        this.qaService = qaService;
    }

    @PostMapping("/ask")
    public ResponseEntity<List<Document>> ask(@RequestBody QuestionRequest questionRequest){
        return ResponseEntity.ok(qaService.answerQuestion(questionRequest.getQuestion()));
    }
}
