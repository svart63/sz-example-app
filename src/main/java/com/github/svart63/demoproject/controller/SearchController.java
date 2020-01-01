package com.github.svart63.demoproject.controller;

import com.github.svart63.demoproject.model.Profile;
import com.github.svart63.demoproject.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/profiles")
    public ResponseEntity<Collection<Profile>> searchProfiles(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok(searchService.find(params));
    }
}
