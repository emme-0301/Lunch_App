package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {

	@Autowired
	BudgetRepository repo;

	public void RegistBudget(BudgetForm budgetForm) {
		
		Budget budget = new Budget(); 	
		budget.setUserName(budgetForm.getUserName());
		budget.setMonth(budgetForm.getMonth());
		budget.setAmount(budgetForm.getAmount());
		
		repo.save(budget);
	}
	
}
