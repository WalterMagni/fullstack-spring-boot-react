package io.github.waltermagni.imageliteapi.controller;

import io.github.waltermagni.imageliteapi.dto.ImageDTO;
import io.github.waltermagni.imageliteapi.mapper.ImageMapper;
import io.github.waltermagni.imageliteapi.model.entity.domain.Image;
import io.github.waltermagni.imageliteapi.model.enums.ImageExtension;
import io.github.waltermagni.imageliteapi.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/images")
@Slf4j
@RequiredArgsConstructor
public class ImagesController {

    private final ImageService service;
    private final ImageMapper mapper;


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

    @GetMapping(path = "{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id){
        var obj = service.findById(id);

        if(obj.isPresent()){
            var img = obj.get();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(img.getExtension().getMediaType());
            headers.setContentLength(img.getSize());
            headers.setContentDispositionFormData("inline;filename=\"" + img.getFileName() + "\"", img.getFileName());

            return new ResponseEntity<>(img.getFile(), headers, HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<ImageDTO>> search(
            @RequestParam(value = "extension", required = false, defaultValue = "" ) String extension,
            @RequestParam(value = "query", required = false) String query){

        var result = service.search(ImageExtension.valueOf(extension), query);

        var images = result.stream().map(image -> {
                var url = buildImageURL(image).toString();
                return mapper.ImageToDTO(image, url);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(images);
    }

    private URI buildImageURL(Image image) {
        String imagePath = '/' + image.getId();
        return ServletUriComponentsBuilder.fromCurrentRequest().path(imagePath).build().toUri();
    }

}
