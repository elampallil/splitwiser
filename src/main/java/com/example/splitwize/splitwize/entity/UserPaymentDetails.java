package com.example.splitwize.splitwize.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserPaymentDetails {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    @ManyToOne
    private User user;
    private String oppoName;
    private Double lentAmount;
    private Double borrowedAmount;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOppoName() {
        return oppoName;
    }

    public void setOppoName(String oppoName) {
        this.oppoName = oppoName;
    }

    public Double getLentAmount() {
        return lentAmount;
    }

    public void setLentAmount(Double lentAmount) {
        this.lentAmount = lentAmount;
    }

    public Double getBorrowedAmount() {
        return borrowedAmount;
    }

    public void setBorrowedAmount(Double borrowedAmount) {
        this.borrowedAmount = borrowedAmount;
    }

    
}
