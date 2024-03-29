package com.blog.controller;

import com.blog.entity.User;
import com.blog.helper.ServiceApiResponse;
import com.blog.helper.enums.ResponseStatus;
import com.blog.helper.utils.JwtTokenUtil;
import com.blog.helper.utils.SecurityUtils;
import com.blog.repository.UserRepository;
import com.blog.service.UserService;
import com.blog.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;
    private final JwtTokenUtil jwtTokenUtil;
    private final SecurityUtils securityUtils;

    @GetMapping("/getAll")
    public ServiceApiResponse<User> getAll() {
        List<User> dataList = service.findAll();
        return new ServiceApiResponse<>(dataList, ResponseStatus.SUCCESS);
    }

    @PostMapping("/login")
    public ServiceApiResponse<UserDto> login(@RequestBody User user) {
        User userData = service.login(user.getEmail(), user.getPassword());
        if (userData == null) {
            return new ServiceApiResponse<>("Please fill in the blank field", ResponseStatus.FAILED);
        }
        UserDto userDto = new UserDto(userData);
        String token = jwtTokenUtil.generateToken(userDto);
        userDto.setToken(token);
        return new ServiceApiResponse<>(userDto, ResponseStatus.SUCCESS);
    }

    @PostMapping("/signup")
    public ServiceApiResponse<UserDto> add(@RequestBody @Valid User data) {
        try {
            data.setPassword(securityUtils.encryptSHA1(data.getPassword()));
            service.signup(data);
            UserDto userDto = new UserDto(data);
            String token = jwtTokenUtil.generateToken(userDto);
            userDto.setToken(token);
            return new ServiceApiResponse<>(userDto, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceApiResponse<>(e);
        }
    }
}
