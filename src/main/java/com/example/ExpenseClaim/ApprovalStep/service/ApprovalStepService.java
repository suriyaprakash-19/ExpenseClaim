package com.example.ExpenseClaim.ApprovalStep.service;

import com.example.ExpenseClaim.ApprovalStep.entity.ApprovalStep;
import com.example.ExpenseClaim.ApprovalStep.repository.ApprovalStepRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ApprovalStepService {

    private final ApprovalStepRepository approvalStepRepository;

    public ApprovalStepService(ApprovalStepRepository approvalStepRepository) {
        this.approvalStepRepository = approvalStepRepository;
    }

    // Create approval step
    public ApprovalStep createApprovalStep(ApprovalStep approvalStep) {

        if (approvalStep.getStatus() == null ||
                approvalStep.getStatus().isBlank()) {

            approvalStep.setStatus("PENDING");
        }

        if (approvalStep.getStage() == null ||
                approvalStep.getStage().isBlank()) {

            approvalStep.setStage("MANAGER");
        }

        return approvalStepRepository.save(approvalStep);
    }

    // Get all approval steps
    public List<ApprovalStep> getAllApprovalSteps() {
        return approvalStepRepository.findAll();
    }

    // Get approval step by ID
    public Optional<ApprovalStep> getApprovalStepById(Long id) {
        return approvalStepRepository.findById(id);
    }

    // Get approval steps for a claim
    public List<ApprovalStep> getByClaimId(Long claimId) {
        return approvalStepRepository.findByClaimId(claimId);
    }

    // Get approval steps for an approver
    public List<ApprovalStep> getByApproverId(Long approverId) {
        return approvalStepRepository.findByApproverId(approverId);
    }

    // Get approval steps by status
    public List<ApprovalStep> getByStatus(String status) {
        return approvalStepRepository.findByStatus(status);
    }

    // Approve a claim
    public ApprovalStep approveStep(Long id, String remarks) {

        ApprovalStep approvalStep = approvalStepRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Approval step not found"));

        if (!"PENDING".equalsIgnoreCase(approvalStep.getStatus())) {
            throw new RuntimeException(
                    "Only pending approval steps can be approved");
        }

        approvalStep.setStatus("APPROVED");
        approvalStep.setRemarks(remarks);
        approvalStep.setActionDate(LocalDateTime.now());

        return approvalStepRepository.save(approvalStep);
    }

    // Reject a claim
    public ApprovalStep rejectStep(Long id, String remarks) {

        ApprovalStep approvalStep = approvalStepRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Approval step not found"));

        if (!"PENDING".equalsIgnoreCase(approvalStep.getStatus())) {
            throw new RuntimeException(
                    "Only pending approval steps can be rejected");
        }

        approvalStep.setStatus("REJECTED");
        approvalStep.setRemarks(remarks);
        approvalStep.setActionDate(LocalDateTime.now());

        return approvalStepRepository.save(approvalStep);
    }

    // Delete approval step
    public void deleteApprovalStep(Long id) {

        if (!approvalStepRepository.existsById(id)) {
            throw new RuntimeException("Approval step not found");
        }

        approvalStepRepository.deleteById(id);
    }
}