package com.example.Expense_Tracker;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name="expenses")
public class Expense 
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="exp_id")
    private Long expId; //primary key

    private Double amount;

    private String description;

    private String category;

    @Column(name="expense_date")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user; // foreign key

    //setters and getters

    public Long getExpId()
    {
        return expId;
    }
    public void setExpId(Long expId)
    {
        this.expId=expId;
    }

    public Double getAmount()
    {
        return amount;
    }
    public void setAmount(Double amount)
    {
        this.amount=amount;
    }

    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description=description;
    }

    public String getCategory()
    {
        return category;
    }
    public void setCategory(String category)
    {
        this.category=category;
    }

    public LocalDate getDate()
    {
        return date;
    }
    public void setDate(LocalDate date)
    {
        this.date=date;
    }

    public User getUser()
    {
        return user;
    }
    public void setUser(User user)
    {
        this.user=user;
    }
}
