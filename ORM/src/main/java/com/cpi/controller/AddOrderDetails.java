package com.cpi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cpi.dao.AddOrderDetailsDao;
import com.cpi.dao.AddProduct;


@Controller
public class AddOrderDetails {
	@RequestMapping("pages/AddOrderDetails")
	public ModelAndView addOrderDetails (@RequestParam("quantity") int quantity,
											@RequestParam("itemnum") int productID,
											HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView ();
		
		String productid = Integer.toString(productID);
		String quantities = Integer.toString(quantity);
        Cookie[] cookies = request.getCookies();
        boolean found = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    found = true;
                    String cartValue = cookie.getValue();
                    if (!cartValue.contains(productid)) {
                        cookie.setValue(cartValue + "c" + productid);
                        response.addCookie(cookie);
                    }else {
                    	break;
                    }
                    continue;
                }
                if (cookie.getName().equals("quan")) {
                    found = true;
                    String cartValue1 = cookie.getValue();
                        cookie.setValue(cartValue1 + "q" + quantity);
                        response.addCookie(cookie);
                        AddOrderDetailsDao.createOrderDetails(productID, quantity);
                    continue;
                }
            }
        }
        if (!found) {
            Cookie cookie = new Cookie("cart", productid);
            response.addCookie(cookie);
            Cookie cookie2 = new Cookie("quan", quantities);
            response.addCookie(cookie2);
            AddOrderDetailsDao.createOrderDetails(productID, quantity);
        }
       
       mv.setViewName("DisplayProduct");
		return mv;
	}
	
	@RequestMapping("pages/ShowOrderDetails")
	public ModelAndView showOrderDetails (HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mv = new ModelAndView ();
	    List<String> productid = new ArrayList<>();
	    List<String> quantity = new ArrayList<>();
	    Cookie[] cookies = request.getCookies();
	    boolean found = false;
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals("cart")) {
	                found = true;
	                String[] productIds = cookie.getValue().split("c");
	                for(String productId: productIds) {
	                    for(char c: productId.toCharArray()) {
	                        productid.add(Character.toString(c));
	                    }
	                }
	            }
	            if (cookie.getName().equals("quan")) {
	                found = true;
	                String[] quantities = cookie.getValue().split("q");
	                for(String qty: quantities) {
	                    for(char c: qty.toCharArray()) {
	                        quantity.add(Character.toString(c));
	                    }
	                }
	            }
	        }
	    }
	    for (int x = 0; x < productid.size(); x++) {
	        System.out.println("Product ID " + productid.get(x));
	        System.out.println("Quantity: " +quantity.get(x));
	    }
//	    mv.addObject("orderDetails", UpdateOrderDetailsDao.displayUpdateOrderDetails(productid, quantity));
	    mv.setViewName("DisplayProduct");
	    return mv;
	}
	
	 @RequestMapping("pages/destroyCookies")
	    public ModelAndView destroyCookies(HttpServletRequest request, HttpServletResponse response) {
	        ModelAndView mv = new ModelAndView();
	        
	        Cookie[] cookies = request.getCookies();
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                cookie.setMaxAge(0);
	                response.addCookie(cookie);
	            }
	        }
	        
	        mv.setViewName("dashboard.jsp");
	        return mv;
	    }


}