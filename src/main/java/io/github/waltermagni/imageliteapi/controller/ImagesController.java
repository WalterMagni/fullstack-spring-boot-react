package io.github.waltermagni.imageliteapi.controller;

import io.github.waltermagni.imageliteapi.mapper.ImageMapper;
import io.github.waltermagni.imageliteapi.model.entity.domain.Image;
import io.github.waltermagni.imageliteapi.model.enums.ImageExtension;
import io.github.waltermagni.imageliteapi.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("v1/images")
@Slf4j
@RequiredArgsConstructor
public class ImagesController {

    private final ImageService service;
    private final ImageMapper mapper;

/*
    public ImagesController(ImageService service) {
        this.service = service;
    }*/

    @PostMapping
    public ResponseEntity save(@RequestParam("file") MultipartFile file,
                               @RequestParam("name") String name,
                               @RequestParam("tags") List<String> tags) throws IOException {

        log.info("Imagem recebida: nome: {}, size: {}", file.getOriginalFilename(), file.getSize());

        Image image = mapper.mapToImage(file, name, tags);
        Image savedImage = service.save(image);
        URI uri = buildImageURL(savedImage);


        return ResponseEntity.created(uri).build();
    }

    //localhost:8080/v1/images/asdasdadas
    private URI buildImageURL(Image image) {
        String imagePath = '/' + image.getId();
        return ServletUriComponentsBuilder.fromCurrentRequest().path(imagePath).build().toUri();
    }

}
