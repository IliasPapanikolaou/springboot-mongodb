package com.ipap.springbootmongodb.service;

import com.ipap.springbootmongodb.entity.Photograph;
import com.ipap.springbootmongodb.repository.PhotographRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class PhotographServiceImpl implements PhotographService {

    private final PhotographRepository photographRepository;

    public PhotographServiceImpl(PhotographRepository photographRepository) {
        this.photographRepository = photographRepository;
    }

    @Override
    public String addPhoto(String originalFilename, MultipartFile image) throws IOException {

        Photograph photograph = new Photograph();
        photograph.setTitle(originalFilename);
        photograph.setPhotograph(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
        return photographRepository.save(photograph).getId();
    }

    @Override
    public Photograph getPhotograph(String id) {
        return photographRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Photo not found"));
    }
}
