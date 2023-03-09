package com.cpi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cpi.dao.UsersDao;
import com.cpi.model.Users;

@Controller
public class Login {

	@RequestMapping("Login")
	public ModelAndView login (@RequestParam("username") String username, @RequestParam("password") String password)
	{
		ModelAndView mv = new ModelAndView();
		
		UsersDao dao = new UsersDao();
		Users user = dao.getUser(username, password);
		
		mv.addObject("user", user);
		mv.setViewName("dashboard.jsp");
		
		return mv;
		
	}
}
