package com.example.Expense_Tracker;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody User loginDetails)
    {
        Map<String, Object> response = new HashMap<>();
        User existingUser = repository.findByUserName(loginDetails.getUserName());

        if(existingUser == null)
        {
            response.put("message", "User NOT Found");
            return ResponseEntity.badRequest().body(response);
        }

        if(existingUser.getPassword().equals(loginDetails.getPassword()))//matches password
        {
            response.put("message", "Login Successful!");
            response.put("userId", existingUser.getUserId());
            return ResponseEntity.ok(response);
        }
        else
        {
            response.put("message", "Incorrect Password");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public List<User> viewUsers()
    {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id)
    {
        return repository.findById(id).orElse(null);
    }
}