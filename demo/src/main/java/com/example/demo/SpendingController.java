package com.example.demo;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class SpendingController {

    @Autowired
    private SpendingService service;

    @GetMapping("/spending/input")
    public String showSpendingInputForm(Model model, HttpSession session) {
        
        // セッションからログインユーザー名を取得
        User loginUser = (User) session.getAttribute("loginUserInfo");
        SpendingForm form = new SpendingForm();
        form.setUserName(loginUser.getUserName());  // Thymeleafなどで表示用に渡す
        
        model.addAttribute("spendingForm", form);
        return "spendingInput";
    }

    @PostMapping("/spending/add")
    public String addSpending(@ModelAttribute SpendingForm spendingForm) {
    	service.addSpending(spendingForm);
        return "redirect:/home"; // 登録後はホームに戻る
    }

    @GetMapping("/spending/list")
    public String viewSpendingList(Model model) {
    	
    	List<Spending> spentList = service.getAllSpending();
        model.addAttribute("spendingList", spentList);
        return "viewSpending";
    }
    
    @GetMapping("/lunch/stats")
    public String showLunchStats(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loginUserInfo");
        String userName = user.getUserName();

        Map<YearMonth, Integer> monthlyStats = service.getMonthlySpending(userName);
        Map<Integer, Integer> weeklyStats = service.getWeeklySpending(userName);

        model.addAttribute("monthlyStats", monthlyStats);
        model.addAttribute("weeklyStats", weeklyStats);

        // 表示用にキー（年月）と値（金額）を分離
        List<String> labels = monthlyStats.keySet().stream()
            .map(ym -> ym.toString()) // "2024-12" みたいな形式
            .toList();
        List<Integer> data = monthlyStats.values().stream().toList();

        model.addAttribute("labels", labels);
        model.addAttribute("data", data);
        
        return "lunchStats";
    }
}
