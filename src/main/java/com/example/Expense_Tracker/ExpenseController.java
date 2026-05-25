package com.example.Expense_Tracker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*") //for CORS error
public class ExpenseController 
{
    @Autowired
    ExpenseRepository repository;

    // We also need the UserRepository so we can find the User to attach to the new Expense
    @Autowired
    UserRepository userRepository;

    // GET /api/expenses/{userId}
    @GetMapping("/{userId}") // view all expenses of a user
    public List<Expense> viewExpenses(@PathVariable Long userId)
    {
        return repository.findByUserUserId(userId);
    }

    // POST /api/expenses/{userId}
    @PostMapping("/{userId}")
    public Expense createExpense(@PathVariable Long userId, @RequestBody Expense newExpense)
    {
        //  Find the user who is creating this expense
        User user = userRepository.findById(userId).orElse(null);
        
        if (user != null) {
            //  Attach the user to the expense
            newExpense.setUser(user);
            
            // Save the expense to the database
            return repository.save(newExpense);
        }
        
        return null; // we throw an error here if the user wasn't found
    }
}
