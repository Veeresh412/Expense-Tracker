package com.example.Expense_Tracker;


//imported jakarta persistance api which maps objects in java to relational dbs

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table; 

@Entity
@Table(name="Users")
public class User
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="userid")
    private Long userId; // primary key assigned to each user.  we use Long for so that userId can be null

    @Column(name="username",length=30,nullable=false,unique=true) //nullable=false is NOT NULL constraint
    private String userName;

    @Column(name="password",length=26,nullable=false)
    private String password;

    @Column(name="monthly_limit")
    private Double limit;

    //setters and getters

    public Long getUserId()
    {
        return userId;
    }
    public void setUserId(Long userId)
    {
        this.userId=userId;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName=userName;
    }

    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }

    public Double getLimit()
    {
        return limit;
    }
    public void setLimit(Double limit)
    {
        this.limit=limit;
    }
}