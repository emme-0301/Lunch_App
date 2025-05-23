package com.example.demo;

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

import jakarta.servlet.http.HttpSession;

@Controller
public class LunchController {

	@Autowired

	LunchService service;

	@GetMapping("/lunchRegist")
	public String showForm(Model model, HttpSession session) {
		model.addAttribute("lunchForm", new LunchRegistForm());

		// セッションからログインユーザー名を取得
		User loginUser = (User) session.getAttribute("loginUserInfo");
		model.addAttribute("loginUser", loginUser.getUserName()); // Thymeleafなどで表示用に渡す

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
	public String registerLunch(@ModelAttribute("lunchForm") LunchRegistForm lunchForm,
			@RequestParam("image") MultipartFile imageFile) throws IOException {
		LunchMenu lunch = new LunchMenu();
		lunch.setMenuName(lunchForm.getMenuName());
		lunch.setCost(lunchForm.getCost());
		lunch.setMenuCategory(lunchForm.getMenuCategory());

		if (!imageFile.isEmpty()) {
			lunch.setImage(imageFile.getBytes()); // バイト配列に変換して保存
			lunch.setImageType(imageFile.getContentType()); // MIMEタイプをセット
		}

		service.saveLunch(lunch);
		return "lunchRegistResult";
	}

}
