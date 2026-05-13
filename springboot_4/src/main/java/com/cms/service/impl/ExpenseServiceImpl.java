package com.cms.service.impl;

import com.cms.entity.Expense;
import com.cms.excHandler.ExpenseNotFoundException;
import com.cms.repository.ExpenseRepository;
import com.cms.service.Interface.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Override
    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
            .orElseThrow(() ->
                    new ExpenseNotFoundException(
                            "Expense not found with id: " + id
                    ));
    }

    @Override
    public Expense updateExpense(Long id, Expense expense) {

        Expense existingExpense = expenseRepository.findById(id)
        .orElseThrow(() ->
                new ExpenseNotFoundException(
                        "Expense not found with id: " + id
                ));

        if (existingExpense != null) {
            existingExpense.setTitle(expense.getTitle());
            existingExpense.setAmount(expense.getAmount());
            existingExpense.setCategory(expense.getCategory());
            existingExpense.setExpenseDate(expense.getExpenseDate());
            existingExpense.setDescription(expense.getDescription());

            return expenseRepository.save(existingExpense);
        }

        return null;
    }

    @Override
    public void deleteExpense(Long id) {
        Expense existingExpense = expenseRepository.findById(id).orElseThrow(() ->
                new ExpenseNotFoundException(
                        "Expense not found with id: " + id
                ));
                
        expenseRepository.delete(existingExpense);
    }
}