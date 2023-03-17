package com.cpi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cpi.dao.GetUserDetails;

@Controller
public class ShowUserDetails {
	@RequestMapping("pages/ShowUsers")
	public ModelAndView showUsers () throws ClassNotFoundException {
		ModelAndView mv = new ModelAndView ();
		mv.addObject("userprofile", GetUserDetails.getUsers());
		mv.setViewName("edituser.jsp");
		return mv;
	}
}
