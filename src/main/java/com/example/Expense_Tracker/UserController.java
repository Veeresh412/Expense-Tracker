package com.example.Expense_Tracker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*") 
public class UserController
{
    @Autowired
    UserRepository repository;

    @PostMapping
    public User createUser(@RequestBody User newUser)
    {
        return repository.save(newUser);
    }

    @GetMapping
    public List<User> viewUsers()
    {
        return repository.findAll();
    }

}