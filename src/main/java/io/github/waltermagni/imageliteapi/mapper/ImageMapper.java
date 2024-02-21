package io.github.waltermagni.imageliteapi.mapper;

import io.github.waltermagni.imageliteapi.dto.ImageDTO;
import io.github.waltermagni.imageliteapi.model.entity.domain.Image;
import io.github.waltermagni.imageliteapi.model.enums.ImageExtension;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class ImageMapper {


    public Image mapToImage(MultipartFile file, String name, List<String> tags) throws IOException {

        return Image.builder()
                .name(name)
                .tags(String.join(",", tags))
                .size(file.getSize())
                .extension(ImageExtension.valueOf(MediaType.valueOf(file.getContentType())))
                .file(file.getBytes())
                .build();
    }

    public ImageDTO domainToDTO(Image image, String url) {
        return ImageDTO.builder()
                .url(url)
                .extension(image.getExtension().name())
                .size(image.getSize())
                .name(image.getName())
                .uploadDate(image.getUploadDate().toLocalDate())
                .build();
    }

}
