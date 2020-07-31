package com.oyeRickshaw.ratingSystem.entity.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author sourabhrana
 */

@MappedSuperclass
@Data
public abstract class GenericAbstractEntity<T> implements Serializable {
    private static final long serialVersionUID = 5187594290000165679L;

    @Column(name = "created_at", nullable = false)
    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null)
            this.createdAt = LocalDateTime.now();
        if (this.updatedAt == null)
            this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
