package com.blog.entity;

import com.blog.helper.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="users")
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
    @OneToMany(targetEntity = Post.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id" ,referencedColumnName = "id")
    private List<Post> posts;


}
