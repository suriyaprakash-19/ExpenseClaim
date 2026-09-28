package com.example.ExpenseClaim.ApprovalStep.controller;

import com.example.ExpenseClaim.ApprovalStep.entity.ApprovalStep;
import com.example.ExpenseClaim.ApprovalStep.service.ApprovalStepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/approval-steps")
public class ApprovalStepController {

    private final ApprovalStepService approvalStepService;

    public ApprovalStepController(ApprovalStepService approvalStepService) {
        this.approvalStepService = approvalStepService;
    }

    // Create approval step
    @PostMapping
    public ResponseEntity<ApprovalStep> createApprovalStep(
            @RequestBody ApprovalStep approvalStep) {

        return ResponseEntity.ok(
                approvalStepService.createApprovalStep(approvalStep)
        );
    }

    // Get all approval steps
    @GetMapping
    public ResponseEntity<List<ApprovalStep>> getAllApprovalSteps() {

        return ResponseEntity.ok(
                approvalStepService.getAllApprovalSteps()
        );
    }

    // Get approval step by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApprovalStep> getApprovalStepById(
            @PathVariable Long id) {

        return approvalStepService.getApprovalStepById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get approval steps by claim
    @GetMapping("/claim/{claimId}")
    public ResponseEntity<List<ApprovalStep>> getByClaimId(
            @PathVariable Long claimId) {

        return ResponseEntity.ok(
                approvalStepService.getByClaimId(claimId)
        );
    }

    // Get approval steps by approver
    @GetMapping("/approver/{approverId}")
    public ResponseEntity<List<ApprovalStep>> getByApproverId(
            @PathVariable Long approverId) {

        return ResponseEntity.ok(
                approvalStepService.getByApproverId(approverId)
        );
    }

    // Get approval steps by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ApprovalStep>> getByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                approvalStepService.getByStatus(status)
        );
    }

    // Approve
    @PutMapping("/{id}/approve")
    public ResponseEntity<ApprovalStep> approveStep(
            @PathVariable Long id,
            @RequestParam(required = false) String remarks) {

        return ResponseEntity.ok(
                approvalStepService.approveStep(id, remarks)
        );
    }

    // Reject
    @PutMapping("/{id}/reject")
    public ResponseEntity<ApprovalStep> rejectStep(
            @PathVariable Long id,
            @RequestParam(required = false) String remarks) {

        return ResponseEntity.ok(
                approvalStepService.rejectStep(id, remarks)
        );
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApprovalStep(
            @PathVariable Long id) {

        approvalStepService.deleteApprovalStep(id);

        return ResponseEntity.ok(
                "Approval step deleted successfully"
        );
    }
}