package io.github.waltermagni.imageliteapi.service;


import io.github.waltermagni.imageliteapi.model.entity.domain.Image;
import io.github.waltermagni.imageliteapi.repository.ImageRepository;
import io.github.waltermagni.imageliteapi.service.interfaces.ImageServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ImageService implements ImageServiceInterface {

    private final ImageRepository repository;

    @Override
    @Transactional
    public Image save(Image image) {
        return repository.save(image);
    }
}
