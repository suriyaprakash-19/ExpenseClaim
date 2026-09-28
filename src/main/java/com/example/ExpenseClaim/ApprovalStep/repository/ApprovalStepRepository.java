package com.example.ExpenseClaim.ApprovalStep.repository;

import com.example.ExpenseClaim.ApprovalStep.entity.ApprovalStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApprovalStepRepository extends JpaRepository<ApprovalStep, Long> {

    List<ApprovalStep> findByClaimId(Long claimId);

    List<ApprovalStep> findByApproverId(Long approverId);

    List<ApprovalStep> findByStatus(String status);

    List<ApprovalStep> findByStage(String stage);
}