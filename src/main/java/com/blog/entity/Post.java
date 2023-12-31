package com.blog.entity;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    @NotNull(message = "the title failed is required")
    private String title;
    @NotNull(message = "the image failed is required")
    private String image;
    @NotNull(message = "the body failed is required")
    private String body;
}
