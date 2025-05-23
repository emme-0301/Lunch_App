package com.example.demo;

import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpendingService {

	@Autowired
	SpendingRepository repo;
	Spending spending;
	
	public void addSpending(SpendingForm spendingForm) {
		
		Spending spending = new Spending(); 	
		spending.setUserName(spendingForm.getUserName());
		spending.setDate(spendingForm.getDate());
		spending.setTotal_spent(spendingForm.getTotal_spent());
		
		repo.save(spending);
	}
	
	public List<Spending> getAllSpending(){
		
		return repo.findAll();
	}
	
	public Map<YearMonth, Integer> getMonthlySpending(String userName) {
	    List<Spending> list = repo.findByUserName(userName);
	    return list.stream()
	        .collect(Collectors.groupingBy(
	            lm -> YearMonth.from(lm.getDate()),
	            Collectors.summingInt(Spending::getTotal_spent)
	        ));
	}
	
	public Map<Integer, Integer> getWeeklySpending(String userName) {
	    List<Spending> list = repo.findByUserName(userName);
	    return list.stream()
	        .collect(Collectors.groupingBy(
	            lm -> lm.getDate().get(ChronoField.ALIGNED_WEEK_OF_MONTH),
	            Collectors.summingInt(Spending::getTotal_spent)
	        ));
	}
	
}
