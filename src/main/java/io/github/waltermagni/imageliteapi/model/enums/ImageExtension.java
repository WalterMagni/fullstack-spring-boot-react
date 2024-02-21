package io.github.waltermagni.imageliteapi.model.enums;

import org.springframework.http.MediaType;

import java.util.Arrays;

public enum ImageExtension {

    PNG(MediaType.IMAGE_PNG),
    JPG(MediaType.IMAGE_JPEG),
    JPEG(MediaType.IMAGE_JPEG),
    GIF(MediaType.IMAGE_GIF),
    ;

    private final MediaType mediaType;

    ImageExtension(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public static ImageExtension valueOf(MediaType mediaType) {
        return Arrays.stream(values())
                .filter(img -> img
                        .getMediaType().equals(mediaType))
                        .findFirst()
                .orElse(null);
    }
}
