package com.blog.dto;

import com.blog.entity.User;
import com.blog.helper.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private UserRole role;
    private String token;

    public UserDto (User user){
        setId(user.getId());
        setEmail(user.getEmail());
        setName(user.getName());
        setRole(user.getRole());
        setId(user.getId());
    }
}
