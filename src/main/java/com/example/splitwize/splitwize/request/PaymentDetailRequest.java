package com.example.splitwize.splitwize.request;

public class PaymentDetailRequest {
    private  String oppoName;
    private Double lentAmount;
    private  Double borrowedAmount;

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
