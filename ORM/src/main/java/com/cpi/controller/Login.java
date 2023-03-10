package com.cpi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cpi.dao.UsersDao;
import com.cpi.model.Users;

@Controller
class Controllers {

	@RequestMapping("Login")
	public ModelAndView login (@RequestParam("username") String username, @RequestParam("password") String password){
		ModelAndView mv = new ModelAndView();
		
		UsersDao dao = new UsersDao();
		Users user = dao.getUser(username, password);
		
		mv.addObject("user", user);
		mv.setViewName("pages/dashboard.jsp");
		return mv;
		
	}
	
	@RequestMapping("Logout")
	public ModelAndView logout (){
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Logged Out Successfully");
		mv.setViewName("index.jsp");
		return mv;
		
	}
	
	@RequestMapping("pages/Register")
	public ModelAndView register (@RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("roleid") int roleid){
		ModelAndView mv = new ModelAndView();
		int uid = 0;
		Users u = new Users();
		u.setUserId(uid);
		u.setUsername(username);
		u.setEmail(email);
		u.setPassword(password);
		u.setRoleId(roleid);
		
		UsersDao dao = new UsersDao();
		String msg = dao.createUser(u);
		/*
		 * mv.addObject("message", "Logged Out Successfully");
		 * mv.setViewName("index.jsp");
		 */
		mv.addObject("msg", msg);
		mv.setViewName("dashboard.jsp");
		return mv;
		
	}
}
