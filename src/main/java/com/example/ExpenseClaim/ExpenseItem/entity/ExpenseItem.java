package com.example.ExpenseClaim.ExpenseItem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "expense_item")
public class ExpenseItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long claimId;

    private String category;

    private String description;

    private Double amount;

    private Double policyLimit;

    private boolean exceedsLimit;

    public ExpenseItem() {
    }

    public ExpenseItem(Long claimId, String category, String description,
                       Double amount, Double policyLimit) {

        this.claimId = claimId;
        this.category = category;
        this.description = description;
        this.amount = amount;
        this.policyLimit = policyLimit;
    }

    public Long getId() {
        return id;
    }

    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getPolicyLimit() {
        return policyLimit;
    }

    public void setPolicyLimit(Double policyLimit) {
        this.policyLimit = policyLimit;
    }

    public boolean isExceedsLimit() {
        return exceedsLimit;
    }

    public void setExceedsLimit(boolean exceedsLimit) {
        this.exceedsLimit = exceedsLimit;
    }
}