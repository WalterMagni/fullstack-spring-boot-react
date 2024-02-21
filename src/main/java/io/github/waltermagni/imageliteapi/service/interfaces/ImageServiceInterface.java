package io.github.waltermagni.imageliteapi.service.interfaces;

import io.github.waltermagni.imageliteapi.model.entity.domain.Image;

import java.util.Optional;

public interface ImageServiceInterface {
    Image save(Image image);
    Optional<Image> findById(String id);
}
