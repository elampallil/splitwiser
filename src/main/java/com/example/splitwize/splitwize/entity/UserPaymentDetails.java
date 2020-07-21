package com.example.splitwize.splitwize.entity;

import javax.persistence.*;

@Entity
public class UserPaymentDetails {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name="custId")
    private UserRegiData userRegiData;
    private  String name;
    private long lent_amount;
    private  long borrowed_amount;

    public UserRegiData getUserRegiData() {
        return userRegiData;
    }

    public void setUserRegiData(UserRegiData userRegiData) {
        this.userRegiData = userRegiData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public String getCust_id() {
//        return cust_id;
//    }
//
//    public void setCust_id(String cust_id) {
//        this.cust_id = cust_id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLent_amount() {
        return lent_amount;
    }

    public void setLent_amount(long lent_amount) {
        this.lent_amount = lent_amount;
    }

    public long getBorrowed_amount() {
        return borrowed_amount;
    }

    public void setBorrowed_amount(long borrowed_amount) {
        this.borrowed_amount = borrowed_amount;
    }


}
