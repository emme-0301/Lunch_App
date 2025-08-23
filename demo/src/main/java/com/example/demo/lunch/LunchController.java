package com.example.demo.lunch;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.user.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class LunchController {

    @Autowired
    LunchService service;

    // 💡 全ハンドラメソッドで "loginUser" がModelに入る
    @ModelAttribute("loginUser")
    public User setUpLoginUser(HttpSession session) {
        return (User) session.getAttribute("loginUserInfo");
    }

    @GetMapping("/lunchRegist")
    public String showForm(Model model) {
        model.addAttribute("lunchForm", new LunchRegistForm());
        return "lunch-form";
    }

    @GetMapping("/lunchList")
    public String showLunchList(Model model) {
        List<LunchMenu> lunchList = service.findAllLunch();
        List<LunchDto> dtoList = service.convertToDTOList(lunchList);
        model.addAttribute("lunchList", dtoList);
        return "lunch-list";
    }

    @PostMapping("/register")
    public String registerLunch(
            @ModelAttribute("lunchForm") LunchRegistForm lunchForm,
            @RequestParam("image") MultipartFile imageFile,
            @ModelAttribute("loginUser") User loginUser) throws IOException {

        LunchMenu lunch = new LunchMenu();
        lunch.setUser_id(loginUser.getId());
        lunch.setMenuName(lunchForm.getMenuName());
        lunch.setCost(lunchForm.getCost());
        lunch.setMenuCategory(lunchForm.getMenuCategory());

        if (!imageFile.isEmpty()) {
            lunch.setImage(imageFile.getBytes());
            lunch.setImageType(imageFile.getContentType());
        }

        service.saveLunch(lunch);
        return "lunchRegistResult";
    }
}
