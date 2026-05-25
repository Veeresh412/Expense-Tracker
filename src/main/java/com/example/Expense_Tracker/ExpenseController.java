package com.example.Expense_Tracker;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

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

    @DeleteMapping("/{expId}")
    public void deleteExpense(@PathVariable Long expId)
    {
        repository.deleteById(expId);
    }

    // GET /api/expenses/analytics/{userId}
    @GetMapping("/analytics/{userId}")
    public List<Object[]> getAnalytics(@PathVariable Long userId)
    {
        return repository.getCategoryWiseExpenses(userId);
    }

    // GET /api/expenses/analytics/average/{userId}
    @GetMapping("/analytics/average/{userId}")
    public List<Object[]> getAverageCostAnalytics(@PathVariable Long userId)
    {
        return repository.getAverageCostPerCategory(userId);
    }

    // GET /api/expenses/analytics/runrate/{userId}
    @GetMapping("/analytics/runrate/{userId}")
    public Map<String, Object> getRunRate(@PathVariable Long userId)
    {
        Map<String, Object> response = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if (user == null || user.getLimit() == null) return response;

        List<Expense> allExpenses = repository.findByUserUserId(userId);
        
        LocalDate today = LocalDate.now();
        int currentMonth = today.getMonthValue();
        int currentYear = today.getYear();
        int currentDay = today.getDayOfMonth();
        int daysInMonth = YearMonth.of(currentYear, currentMonth).lengthOfMonth();

        double totalSpentThisMonth = 0.0;

        for (Expense exp : allExpenses) {
            if ("DEBIT".equals(exp.getType()) && exp.getDate() != null) {
                if (exp.getDate().getMonthValue() == currentMonth && exp.getDate().getYear() == currentYear) {
                    if (exp.getDate().getDayOfMonth() <= currentDay) { 
                        totalSpentThisMonth += exp.getAmount();
                    }
                }
            }
        }

        double projectedTotal = 0.0;
        if (currentDay > 0) {
            projectedTotal = (totalSpentThisMonth / currentDay) * daysInMonth;
        }

        response.put("limit", user.getLimit());
        response.put("spentThisMonth", totalSpentThisMonth);
        response.put("projectedTotal", projectedTotal);
        response.put("isOverLimit", projectedTotal > user.getLimit());

        return response;
    }
}
