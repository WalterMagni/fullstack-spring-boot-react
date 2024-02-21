package io.github.waltermagni.imageliteapi.domain.entity.domain;

import io.github.waltermagni.imageliteapi.domain.enums.ImageExtension;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "db_image")
@EntityListeners(AuditingEntityListener.class)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private Long size;

    @Column(name = "extension")
    @Enumerated(EnumType.STRING)
    private ImageExtension extension;

    @Column(name = "upload_date")
    @CreatedDate
    private LocalDateTime uploadDate;

    @Column(name = "tags")
    private String tags;

    @Column(name = "file")
    @Lob
    private byte[] file;

}
