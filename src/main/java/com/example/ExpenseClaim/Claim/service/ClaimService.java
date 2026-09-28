package com.example.ExpenseClaim.Claim.service;

import com.example.ExpenseClaim.Claim.entity.Claim;
import com.example.ExpenseClaim.Claim.repository.ClaimRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;

    public ClaimService(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    // Create a new claim
    public Claim createClaim(Claim claim) {

        if (claim.getStatus() == null ||
                claim.getStatus().isBlank()) {

            claim.setStatus("PENDING");
        }

        claim.setSubmittedDate(LocalDateTime.now());

        return claimRepository.save(claim);
    }

    // Get all claims
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    // Get claim by ID
    public Optional<Claim> getClaimById(Long id) {
        return claimRepository.findById(id);
    }

    // Get claims by employee
    public List<Claim> getClaimsByEmployee(Long employeeId) {
        return claimRepository.findByEmployeeId(employeeId);
    }

    // Get claims by status
    public List<Claim> getClaimsByStatus(String status) {
        return claimRepository.findByStatus(status);
    }

    // Get claims by type
    public List<Claim> getClaimsByType(String claimType) {
        return claimRepository.findByClaimType(claimType);
    }

    // Update claim
    public Claim updateClaim(Long id, Claim updatedClaim) {

        Claim existingClaim = claimRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Claim not found"));

        existingClaim.setEmployeeId(updatedClaim.getEmployeeId());
        existingClaim.setClaimType(updatedClaim.getClaimType());
        existingClaim.setTotalAmount(updatedClaim.getTotalAmount());
        existingClaim.setDescription(updatedClaim.getDescription());

        if (updatedClaim.getStatus() != null) {
            existingClaim.setStatus(updatedClaim.getStatus());
        }

        return claimRepository.save(existingClaim);
    }

    // Delete claim
    public void deleteClaim(Long id) {

        if (!claimRepository.existsById(id)) {
            throw new RuntimeException("Claim not found");
        }

        claimRepository.deleteById(id);
    }
}