package ru.jm.jmspringboot.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.jm.jmspringboot.model.User;
import ru.jm.jmspringboot.service.UserService;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(path = "/", produces = "application/json;charset=UTF-8")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/user")
	public String user(ModelMap model){

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user=userService.getUser(auth.getName());
		List<User> users = new LinkedList<>();
		users.add(user);
		model.addAttribute("userinfo",user);
		model.addAttribute("users", users );
		return "user/userinfo";
	}


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
		return "login";
    }


}