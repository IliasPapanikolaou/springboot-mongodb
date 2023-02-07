package com.ipap.springbootmongodb.controller;

import com.ipap.springbootmongodb.entity.Photograph;
import com.ipap.springbootmongodb.service.PhotographService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/photo")
public class PhotographController {

    private final PhotographService photographService;

    public PhotographController(PhotographService photographService) {
        this.photographService = photographService;
    }

    @PostMapping
    public ResponseEntity<String> addPhotograph(@RequestParam("image")MultipartFile image) throws IOException {
        return ResponseEntity.ok(photographService.addPhoto(image.getOriginalFilename(), image));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> downloadPhoto(@PathVariable String id) {
        Photograph photograph = photographService.getPhotograph(id);
        Resource resource = new ByteArrayResource(photograph.getPhotograph().getData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                        + photograph.getTitle() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
