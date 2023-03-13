package com.cpi.controller;


import java.text.ParseException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cpi.dao.CreateOrderDao;
import com.cpi.model.Order;

@Controller
class Ordering {
	@RequestMapping("pages/NewOrder")
	public ModelAndView newOrdering (@RequestParam("t1") String orderFname, @RequestParam("t2") String orderLname, 
									@RequestParam("t3") int orderMobileNumber, @RequestParam("dates") String orderDeliver,
									@RequestParam("times") String orderTime) throws ParseException {
		
		ModelAndView mv = new ModelAndView ();
		Order order = new Order();
		
		order.setCustomerFn(orderFname);
		order.setSourceName("Source");						//PlaceHolder
		order.setOrderSource("A");	
		order.setCustomerLn(orderLname);
		order.setMobileNumber(orderMobileNumber);
		order.setCustomerLn(orderLname);
		order.setDeliveryDate(orderDeliver + " " + orderTime);
		order.setDeliveryDate(orderDeliver);
		order.setPaymentStatus(1);							//PlaceHolder 1 payed 0 notpayed
		order.setDiscount(15000);							//PlaceHolder
		order.setPrice(500010);								//PlaceHolder price + quantity
		order.setRemarks("BAD");							//PlaceHolder
		
		CreateOrderDao.createOrder(order);
		
		mv.addObject("order", order);
		
		mv.setViewName("dashboard.jsp");
		
		return mv;
	}
}
