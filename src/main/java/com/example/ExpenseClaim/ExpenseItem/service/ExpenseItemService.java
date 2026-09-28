package com.example.ExpenseClaim.ExpenseItem.service;

import com.example.ExpenseClaim.ExpenseItem.entity.ExpenseItem;
import com.example.ExpenseClaim.ExpenseItem.repository.ExpenseItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseItemService {

    private final ExpenseItemRepository expenseItemRepository;

    public ExpenseItemService(ExpenseItemRepository expenseItemRepository) {
        this.expenseItemRepository = expenseItemRepository;
    }

    // Create expense item
    public ExpenseItem createExpenseItem(ExpenseItem expenseItem) {

        if (expenseItem.getAmount() == null ||
                expenseItem.getAmount() <= 0) {

            throw new RuntimeException(
                    "Expense amount must be greater than zero");
        }

        if (expenseItem.getPolicyLimit() == null ||
                expenseItem.getPolicyLimit() <= 0) {

            throw new RuntimeException(
                    "Policy limit must be greater than zero");
        }

        // Check whether expense exceeds policy limit
        if (expenseItem.getAmount() >
                expenseItem.getPolicyLimit()) {

            expenseItem.setExceedsLimit(true);
        } else {
            expenseItem.setExceedsLimit(false);
        }

        return expenseItemRepository.save(expenseItem);
    }

    // Get all expense items
    public List<ExpenseItem> getAllExpenseItems() {
        return expenseItemRepository.findAll();
    }

    // Get expense item by ID
    public Optional<ExpenseItem> getExpenseItemById(Long id) {
        return expenseItemRepository.findById(id);
    }

    // Get expense items by claim
    public List<ExpenseItem> getExpenseItemsByClaim(Long claimId) {
        return expenseItemRepository.findByClaimId(claimId);
    }

    // Get expense items by category
    public List<ExpenseItem> getExpenseItemsByCategory(String category) {
        return expenseItemRepository.findByCategory(category);
    }

    // Get items exceeding policy limit
    public List<ExpenseItem> getItemsExceedingLimit() {
        return expenseItemRepository.findByExceedsLimit(true);
    }

    // Update expense item
    public ExpenseItem updateExpenseItem(
            Long id,
            ExpenseItem updatedExpenseItem) {

        ExpenseItem existingItem =
                expenseItemRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Expense item not found"));

        existingItem.setClaimId(updatedExpenseItem.getClaimId());
        existingItem.setCategory(updatedExpenseItem.getCategory());
        existingItem.setDescription(updatedExpenseItem.getDescription());
        existingItem.setAmount(updatedExpenseItem.getAmount());
        existingItem.setPolicyLimit(updatedExpenseItem.getPolicyLimit());

        // Recalculate policy violation
        existingItem.setExceedsLimit(
                existingItem.getAmount() >
                        existingItem.getPolicyLimit()
        );

        return expenseItemRepository.save(existingItem);
    }

    // Delete expense item
    public void deleteExpenseItem(Long id) {

        if (!expenseItemRepository.existsById(id)) {
            throw new RuntimeException(
                    "Expense item not found");
        }

        expenseItemRepository.deleteById(id);
    }
}