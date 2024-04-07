package com.sjain.invoicegenerator.entity;

import java.math.BigDecimal;

/**
 * @author sakshamjain
 *
 *
 */
public class Item {
    private String name;
    private String quantity;
    private BigDecimal rate;
    private BigDecimal amount;

    public Item(String name, String quantity, BigDecimal rate, BigDecimal amount) {
        this.name = name;
        this.quantity = quantity;
        this.rate = rate;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", rate=" + rate +
                ", amount=" + amount +
                '}';
    }
}
