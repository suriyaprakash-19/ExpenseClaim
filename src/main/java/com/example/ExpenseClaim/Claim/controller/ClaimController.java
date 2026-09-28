package com.example.ExpenseClaim.Claim.controller;

import com.example.ExpenseClaim.Claim.entity.Claim;
import com.example.ExpenseClaim.Claim.service.ClaimService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    // Create claim
    @PostMapping
    public ResponseEntity<Claim> createClaim(
            @RequestBody Claim claim) {

        return ResponseEntity.ok(
                claimService.createClaim(claim)
        );
    }

    // Get all claims
    @GetMapping
    public ResponseEntity<List<Claim>> getAllClaims() {

        return ResponseEntity.ok(
                claimService.getAllClaims()
        );
    }

    // Get claim by ID
    @GetMapping("/{id}")
    public ResponseEntity<Claim> getClaimById(
            @PathVariable Long id) {

        return claimService.getClaimById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get claims by employee
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Claim>> getClaimsByEmployee(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                claimService.getClaimsByEmployee(employeeId)
        );
    }

    // Get claims by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Claim>> getClaimsByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                claimService.getClaimsByStatus(status)
        );
    }

    // Get claims by type
    @GetMapping("/type/{claimType}")
    public ResponseEntity<List<Claim>> getClaimsByType(
            @PathVariable String claimType) {

        return ResponseEntity.ok(
                claimService.getClaimsByType(claimType)
        );
    }

    // Update claim
    @PutMapping("/{id}")
    public ResponseEntity<Claim> updateClaim(
            @PathVariable Long id,
            @RequestBody Claim claim) {

        return ResponseEntity.ok(
                claimService.updateClaim(id, claim)
        );
    }

    // Delete claim
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClaim(
            @PathVariable Long id) {

        claimService.deleteClaim(id);

        return ResponseEntity.ok(
                "Claim deleted successfully"
        );
    }
}