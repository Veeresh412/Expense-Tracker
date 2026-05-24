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

    @PostMapping // sign up post mapping
    public User createUser(@RequestBody User newUser)
    {
        return repository.save(newUser);
    }

    @PostMapping("/login") //log in post mapping
    public String loginUser(@RequestBody User loginDetails)
    {
        User existingUser = repository.findByUserName(loginDetails.getUserName());

        if(existingUser == null)
        {
            return "User NOT Found"; //check if user exists
        }

        if(existingUser.getPassword().equals(loginDetails.getPassword()))//matches password
        {
            return "Login Successful!";
        }
        else
        {
            return "Incorrect Password";
        }
    }

    @GetMapping
    public List<User> viewUsers()
    {
        return repository.findAll();
    }

}