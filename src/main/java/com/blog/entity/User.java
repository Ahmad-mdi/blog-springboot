package com.blog.entity;

import com.blog.helper.enums.UserRole;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "the name failed is required")
    private String name;
    @Column(unique = true)
    @NotNull(message = "the email failed is required")
    private String email;
    private UserRole role;
    @NotNull(message = "the password failed is required")
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Post> posts;
   /* @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;*/

}
