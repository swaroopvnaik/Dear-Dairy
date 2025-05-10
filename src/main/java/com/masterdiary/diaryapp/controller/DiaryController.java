package com.masterdiary.diaryapp.controller;

import com.masterdiary.diaryapp.model.DiaryEntry;
import com.masterdiary.diaryapp.repository.DiaryEntryRepository;
import com.masterdiary.diaryapp.sentiment.SentimentAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/diary")
public class DiaryController {

    @Autowired
    private DiaryEntryRepository diaryEntryRepository;

    private final SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();

    // Adding a new diary entry
    @PostMapping("/add")
    public DiaryEntry addEntry(@RequestBody DiaryEntry entry) {
        entry.setDate(LocalDate.now());
        String sentiment = sentimentAnalyzer.analyzeSentiment(entry.getContent());
        entry.setSentiment(sentiment);
        return diaryEntryRepository.save(entry);
    }

    // Getting all diary entries
    @GetMapping("/all")
    public List<DiaryEntry> getAllEntries() {
        return diaryEntryRepository.findAll();
    }

    // Deleting a diary entry by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable String id) {
        Optional<DiaryEntry> entry = diaryEntryRepository.findById(id);
        if (entry.isPresent()) {
            diaryEntryRepository.deleteById(id);
            return new ResponseEntity<>("Entry deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Entry not found", HttpStatus.NOT_FOUND);
        }
    }
}
