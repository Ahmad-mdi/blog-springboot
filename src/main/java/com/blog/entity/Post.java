package com.blog.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="posts")
public class Post {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pid;
    @Column(unique = true)
    @NotNull(message = "the title failed is required")
    private String title;
    @NotNull(message = "the image failed is required")
    private String image;
    @NotNull(message = "the body failed is required")
    private String body;
}
