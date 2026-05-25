package com.example.Expense_Tracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    
    // Spring Data JPA is magic. By simply naming the method "findByUser",
    // Spring Boot automatically knows to write a SQL query that finds all
    // expenses where the foreign key matches the provided User object!
    List<Expense> findByUser(User user);
    
}
