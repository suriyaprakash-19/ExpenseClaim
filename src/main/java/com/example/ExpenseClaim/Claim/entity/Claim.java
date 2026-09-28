package com.example.ExpenseClaim.Claim.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "claim")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;

    private String claimType;

    private Double totalAmount;

    private String status;

    private String description;

    private LocalDateTime submittedDate;

    public Claim() {
    }

    public Claim(Long employeeId, String claimType, Double totalAmount,
                 String status, String description) {
        this.employeeId = employeeId;
        this.claimType = claimType;
        this.totalAmount = totalAmount;
        this.status = status;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(LocalDateTime submittedDate) {
        this.submittedDate = submittedDate;
    }
}