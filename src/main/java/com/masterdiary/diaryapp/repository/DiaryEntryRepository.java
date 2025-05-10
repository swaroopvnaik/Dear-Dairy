package com.masterdiary.diaryapp.repository;

import com.masterdiary.diaryapp.model.DiaryEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiaryEntryRepository extends MongoRepository<DiaryEntry, String> {
    // Add custom query methods if needed
}
