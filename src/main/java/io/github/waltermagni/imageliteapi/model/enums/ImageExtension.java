package io.github.waltermagni.imageliteapi.model.enums;

import lombok.Getter;
import org.springframework.http.MediaType;

import java.util.Arrays;

public enum ImageExtension {

    PNG(MediaType.IMAGE_PNG),
    JPG(MediaType.IMAGE_JPEG),
    JPEG(MediaType.IMAGE_JPEG),
    GIF(MediaType.IMAGE_GIF),
    ;

    @Getter
    private final MediaType mediaType;

    ImageExtension(MediaType mediaType) {
        this.mediaType = mediaType;
    }

/*    public static ImageExtension valueOf(MediaType mediaType) {
        return Arrays.stream(values())
                .filter(img -> img
                        .getMediaType().equals(mediaType))
                        .findFirst()
                .orElse(null);
    }*/
}
