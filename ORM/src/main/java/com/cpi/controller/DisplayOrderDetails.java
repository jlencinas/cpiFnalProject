package com.cpi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cpi.dao.AddProduct;
import com.cpi.dao.UpdateOrderDetailsDao;

@Controller
public class DisplayOrderDetails {
	@RequestMapping("pages/DisplayUpdateOrder")
	public ModelAndView displayOrder () {
		ModelAndView mv = new ModelAndView ();
		mv.addObject("updateorderdetails", UpdateOrderDetailsDao.displayUpdateOrderDetails());
		mv.setViewName("orderingDetails.jsp");
		return mv;
	}
	@RequestMapping("pages/DisplayProduct")
	public ModelAndView displayPorudct () throws ClassNotFoundException {
		ModelAndView mv = new ModelAndView ();
		mv.addObject("products", AddProduct.getProducts());
		mv.setViewName("display.jsp");
		return mv;
	}
}
