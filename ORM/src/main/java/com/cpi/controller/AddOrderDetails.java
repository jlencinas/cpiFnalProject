package com.cpi.controller;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddOrderDetails {
    @RequestMapping("pages/AddOrderDetails")
    public ModelAndView addOrderDetails(@RequestParam("quantity") int quantity, 
    		@RequestParam("itemnum") int productID, @RequestParam("price") float productPrice,
            HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        @SuppressWarnings("unchecked")
		Map<Integer, Float> price_cart = (Map<Integer, Float>) session.getAttribute("price_cart");
        
        // If the cart doesn't exist yet, create it
        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }
        
        // If the price_cart doesn't exist yet, create it
        if (price_cart == null) {
        	price_cart = new HashMap<>();
            session.setAttribute("price_cart", price_cart);
        }
        
        // Add the productID and quantity to the cart
        if (cart.containsKey(productID)) {
            int oldQuantity = cart.get(productID);
            float oldPrice = price_cart.get(productID);
            cart.put(productID, oldQuantity + quantity);
            price_cart.put(productID, oldPrice + productPrice);
        } else {
            cart.put(productID, quantity);
            price_cart.put(productID, productPrice);
        }
        System.out.println("Session Cart: " + cart);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("DisplayProduct");
        return mv;
    }
    
    @RequestMapping("pages/deleteItem")
    public ModelAndView deleteItem(@RequestParam("itemid") int ItemID, HttpSession session) {
        @SuppressWarnings("unchecked")
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
		@SuppressWarnings("unchecked")
		Map<Integer, Float> price_cart = (Map<Integer, Float>) session.getAttribute("price_cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        cart.remove(ItemID);
        session.setAttribute("cart", cart);
        System.out.println("Session Cart: " + cart);
        if (price_cart == null) {
        	price_cart = new HashMap<>();
        }
        price_cart.remove(ItemID);
        session.setAttribute("price_cart", price_cart);
        System.out.println("Session Cart: " + price_cart);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("dashboard.jsp");
        return mv;
    }
}

