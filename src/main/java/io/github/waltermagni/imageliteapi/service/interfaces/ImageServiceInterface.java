package io.github.waltermagni.imageliteapi.service.interfaces;

import io.github.waltermagni.imageliteapi.model.entity.domain.Image;
import io.github.waltermagni.imageliteapi.model.enums.ImageExtension;

import java.util.List;
import java.util.Optional;

public interface ImageServiceInterface {
    Image save(Image image);
    Optional<Image> findById(String id);

    List<Image> search(ImageExtension extension, String query);
}
