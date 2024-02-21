package io.github.waltermagni.imageliteapi.repository;

import io.github.waltermagni.imageliteapi.model.entity.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String> {

}
