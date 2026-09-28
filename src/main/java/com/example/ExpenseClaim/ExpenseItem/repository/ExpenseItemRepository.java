package com.example.ExpenseClaim.ExpenseItem.repository;

import com.example.ExpenseClaim.ExpenseItem.entity.ExpenseItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseItemRepository
        extends JpaRepository<ExpenseItem, Long> {

    List<ExpenseItem> findByClaimId(Long claimId);

    List<ExpenseItem> findByCategory(String category);

    List<ExpenseItem> findByExceedsLimit(boolean exceedsLimit);
}