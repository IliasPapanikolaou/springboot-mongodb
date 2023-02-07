package com.ipap.springbootmongodb.service;

import com.ipap.springbootmongodb.entity.Photograph;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface PhotographService {

    String addPhoto(String originalFilename, MultipartFile image) throws IOException;

    Photograph getPhotograph(String id);
}
