package com.example.todo.model;

public class Expense {
    private final long id;
    private final double amount;
    private final String currency;
    private final String category;
    private final String remark;
    private final String createdDate;

    public Expense(long id, double amount, String currency, String category, String remark, String createdDate) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.category = category;
        this.remark = remark;
        this.createdDate = createdDate;
    }

    // --- Add Getters for all fields ---
    public long getId() { return id; }
    public double getAmount() { return amount; }
    public String getCurrency() { return currency; }
    public String getCategory() { return category; }
    public String getRemark() { return remark; }
    public String getCreatedDate() { return createdDate; }
}
