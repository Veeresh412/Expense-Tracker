package com.example.Expense_Tracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Custom method to find a user by their username
    User findByUserName(String userName);

}
