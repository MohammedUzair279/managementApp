package com.jktech.managementApp.mangementApplication.repository;

import com.jktech.managementApp.mangementApplication.model.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    Page<Document> findByAuthorContainingIgnoreCase(String author, Pageable pageable);
    Page<Document> findByTypeContainingIgnoreCase(String type, Pageable pageable);
    Page<Document> findByContentContainingIgnoreCase(String keyword, Pageable pageable);
}
