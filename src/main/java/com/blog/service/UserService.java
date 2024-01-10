package com.blog.service;

import com.blog.entity.User;
import com.blog.helper.utils.SecurityUtils;
import com.blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }
    public User login(String email,String password){
        try {
            password = SecurityUtils.encryptSHA1(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return repository.findFirstByEmailAndPassword(email,password);
    }

    public void signup(User data) throws Exception {
        try {
            if (data.getEmail()==null || data.getPassword()==null)
                throw new Exception("email or password is incorrect!");
            repository.save(data);
        }catch (DataIntegrityViolationException e) {
            throw new Exception("the email has already been taken!", e);
        }
    }
}
