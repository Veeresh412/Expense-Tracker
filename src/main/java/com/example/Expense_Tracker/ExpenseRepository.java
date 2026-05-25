package com.example.Expense_Tracker;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> 
{

    List<Expense> findByUserUserId(Long userId); // method to get expenses using user id
    
}
