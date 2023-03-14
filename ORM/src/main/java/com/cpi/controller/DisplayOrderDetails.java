package com.cpi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cpi.dao.AddProduct;
import com.cpi.dao.UpdateOrderDetailsDao;
import com.cpi.model.UpdateOrderDetails;

@Controller
public class DisplayOrderDetails {
	@RequestMapping("pages/DisplayUpdateOrder")
	public ModelAndView displayUpdateOrder () {
		ModelAndView mv = new ModelAndView ();
		mv.addObject("updateorderdetails", UpdateOrderDetailsDao.displayUpdateOrderDetails(2));
		mv.setViewName("updateOrderDetails.jsp");
		return mv;
	}
	@RequestMapping("pages/DisplayProduct")
	public ModelAndView displayPorudct () throws ClassNotFoundException {
		ModelAndView mv = new ModelAndView ();
		mv.addObject("products", AddProduct.getProducts());
		mv.setViewName("display.jsp");
		return mv;
	}
	@RequestMapping("pages/UpdateOrderDetails")
	public ModelAndView updateOrderDetails(@RequestParam Map<String,String> allParams) {
	    List<UpdateOrderDetails> updatedItems = new ArrayList<>();

	    for (String itemId : allParams.keySet()) {
	        UpdateOrderDetails updatedItem = new UpdateOrderDetails();
	        String itemIdStr = allParams.get(itemId);
	        String quantityStr = allParams.get(itemId + "Id");
	        int itemIdInt = itemIdStr != null ? Integer.parseInt(itemIdStr) : 0;
	        int quantityInt = quantityStr != null ? Integer.parseInt(quantityStr) : 0;
	        updatedItem.setItem_id(itemIdInt);
	        updatedItem.setQuantity(quantityInt);
	        updatedItems.add(updatedItem);    
	    }
	    for (int i = 0; i < updatedItems.size(); i++) {
	        System.out.println(updatedItems.get(i));
	    }

	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("dashboard.jsp"); 
	    return mv;
	}


}
