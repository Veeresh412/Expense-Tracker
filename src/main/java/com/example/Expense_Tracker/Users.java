package com.example.Expense_Tracker;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name="users")
public class Users 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_ID")
    private Long userId;//primary key

    @Column(name="username",length=30)
    private String username;

    @Column (name="password",length=26)
    private String password;

    @Column(name="name",length=30)
    private String name;

    public Users(){};

    public Users(String username,String password,String name)
    {
        this.username=username;
        this.password=password;
        this.name=name;
    }

    //setters and getters

    public Long getUserId()
    {
        return userId;
    }
    public void setUserId(Long userId)
    {
        this.userId=userId;
    }

    public String getUsername()
    {
        return username;
    }
    public void setUserId(String username)
    {
        this.username=username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password=password;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name=name;
    }

}
