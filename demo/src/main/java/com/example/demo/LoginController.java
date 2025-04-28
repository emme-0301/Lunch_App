package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

@Autowired
	
	UserService service;
	
	@GetMapping("/userLogin")
	public String getLoginPage(Model model){
		
		model.addAttribute("userLoginForm", new UserLoginForm());
		
		return "userLogin";
		
	}
	
	@GetMapping("/home")
    public String home() {
        return "home";  // home.html を表示
    }
	
	@PostMapping("/login")
	public String doLogin(@ModelAttribute UserLoginForm userLoginForm) {
		
		List<User> result = service.login(userLoginForm);
		
		if(CollectionUtils.isEmpty(result)) {
			userLoginForm.setCheck(false);
		}else {
			userLoginForm.setCheck(true);
			
			User users = result.get(0);

			userLoginForm.setEmail(users.getEmail());
			userLoginForm.setPassword(users.getPassword());
		}
		
		return "userLoginResult";
		
	}
}
