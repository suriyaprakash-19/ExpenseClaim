package com.example.ExpenseClaim.ExpenseItem.controller;

import com.example.ExpenseClaim.ExpenseItem.entity.ExpenseItem;
import com.example.ExpenseClaim.ExpenseItem.service.ExpenseItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense-items")
public class ExpenseItemController {

    private final ExpenseItemService expenseItemService;

    public ExpenseItemController(
            ExpenseItemService expenseItemService) {

        this.expenseItemService = expenseItemService;
    }

    // Create expense item
    @PostMapping
    public ResponseEntity<ExpenseItem> createExpenseItem(
            @RequestBody ExpenseItem expenseItem) {

        return ResponseEntity.ok(
                expenseItemService.createExpenseItem(expenseItem)
        );
    }

    // Get all expense items
    @GetMapping
    public ResponseEntity<List<ExpenseItem>> getAllExpenseItems() {

        return ResponseEntity.ok(
                expenseItemService.getAllExpenseItems()
        );
    }

    // Get expense item by ID
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseItem> getExpenseItemById(
            @PathVariable Long id) {

        return expenseItemService.getExpenseItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get expense items by claim
    @GetMapping("/claim/{claimId}")
    public ResponseEntity<List<ExpenseItem>> getExpenseItemsByClaim(
            @PathVariable Long claimId) {

        return ResponseEntity.ok(
                expenseItemService.getExpenseItemsByClaim(claimId)
        );
    }

    // Get expense items by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ExpenseItem>> getExpenseItemsByCategory(
            @PathVariable String category) {

        return ResponseEntity.ok(
                expenseItemService.getExpenseItemsByCategory(category)
        );
    }

    // Get items exceeding policy limit
    @GetMapping("/exceeding-limit")
    public ResponseEntity<List<ExpenseItem>> getItemsExceedingLimit() {

        return ResponseEntity.ok(
                expenseItemService.getItemsExceedingLimit()
        );
    }

    // Update expense item
    @PutMapping("/{id}")
    public ResponseEntity<ExpenseItem> updateExpenseItem(
            @PathVariable Long id,
            @RequestBody ExpenseItem expenseItem) {

        return ResponseEntity.ok(
                expenseItemService.updateExpenseItem(
                        id,
                        expenseItem
                )
        );
    }

    // Delete expense item
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpenseItem(
            @PathVariable Long id) {

        expenseItemService.deleteExpenseItem(id);

        return ResponseEntity.ok(
                "Expense item deleted successfully"
        );
    }
}