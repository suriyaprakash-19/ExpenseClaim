package com.example.ExpenseClaim.Claim.repository;

import com.example.ExpenseClaim.Claim.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

    List<Claim> findByEmployeeId(Long employeeId);

    List<Claim> findByStatus(String status);

    List<Claim> findByClaimType(String claimType);
}