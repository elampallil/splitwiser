package com.example.splitwize.splitwize.request;

public class PaymentDetailRequest {
    private  String oppo_name;
    private long lent_amount;
    private  long borrowed_amount;

    public String getOppo_name() {
        return oppo_name;
    }

    public void setOppo_name(String oppo_name) {
        this.oppo_name = oppo_name;
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
