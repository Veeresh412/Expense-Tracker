package com.example.Expense_Tracker;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> 
{

    List<Expense> findByUserUserId(Long userId); // method to get expenses using user id
    
    // Custom SQL Query to group expenses by category for the Analytics chart
    @Query("SELECT e.category, SUM(e.amount) FROM Expense e WHERE e.user.userId = :userId AND e.type = 'DEBIT' GROUP BY e.category")
    List<Object[]> getCategoryWiseExpenses(@Param("userId") Long userId);

    // Custom SQL Query to get the average transaction size by category
    @Query("SELECT e.category, AVG(e.amount) FROM Expense e WHERE e.user.userId = :userId AND e.type = 'DEBIT' GROUP BY e.category")
    List<Object[]> getAverageCostPerCategory(@Param("userId") Long userId);
}
