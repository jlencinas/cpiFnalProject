package com.cpi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DisplayOrderDetails {
	@RequestMapping("pages/DisplayOrder")
	public ModelAndView displayOrder () {
//		OrderDetails order = new OrderDetails();
				
		ModelAndView mv = new ModelAndView ();
//		mv.addObject("orderdetails", CreateOrderDao.displayUpdateOrder());
		mv.setViewName("orderingDetails.jsp");
		return mv;
	}
}
