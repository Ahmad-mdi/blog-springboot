package com.blog.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="posts")
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    /*@OneToMany(mappedBy = "post")
    private List<User> user;*/

}
