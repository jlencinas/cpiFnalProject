package com.cpi.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cpi.dao.AddOrderDetailsDao;
import com.cpi.dao.CreateOrderDao;
import com.cpi.dao.GetUserDetails;
import com.cpi.model.Order;
import com.cpi.model.OrderSummary;
import com.cpi.model.Users;

@Controller
class Ordering {
	
	@ResponseBody
	@RequestMapping(value = {"pages/PopTrackOrder", "PopTrackOrder"})
	public ModelAndView showPopTrackPopup() {
		ModelAndView mv = new ModelAndView ();
		mv.setViewName("pages/inputTrackingNumberPopup.jsp");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = {"pages/ConfOrder", "ConfOrder"})
	public ModelAndView showConfirmationPopup() {
        String confirmationNum = "";
        confirmationNum = CreateOrderDao.getConfirmationNumber();
		ModelAndView mv = new ModelAndView ();
		mv.addObject("confNumber", confirmationNum);
		mv.setViewName("pages/confirmationNumber.jsp");
		return mv;
	}
	
    @RequestMapping(value={"pages/NewOrder", "NewOrder"})
    public ModelAndView newOrdering (@RequestParam("t1") String orderFname, @RequestParam("t2") String orderLname, 
                                    @RequestParam("t3") String orderMobileNumber, @RequestParam("dates") String orderDeliver,
                                    @RequestParam("times") String orderTime, @RequestParam Map<String, String> allParams,
                                    		HttpServletRequest request) {
        
        ModelAndView mv = new ModelAndView ();
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("userAccount");
        int roleId = 0;
        Order order = new Order();
        float totalprice = 0;
        float discount = 0;
        String confirmationNum;
        if (user != null) {
        	roleId = user.getRoleId();
        }
        updateOrderSummaries(allParams, session);
        @SuppressWarnings("unchecked")
		Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<>();
		}else{
			
			for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
				int productId = entry.getKey();
				int quantity = entry.getValue();
				OrderSummary orderSummary = AddOrderDetailsDao.displayOrderSummary(productId, quantity);
				totalprice += (orderSummary.getOrderPrice() * quantity);
			}
		}
		discount = (float) (totalprice - (totalprice * .20));
        
        
        order.setCustomerFn(orderFname);
        order.setSourceName("CPIBakery.com");                        
        order.setOrderSource("W");    
        order.setCustomerLn(orderLname);
        order.setMobileNumber(orderMobileNumber);
        order.setDeliveryDate(orderDeliver + " " + orderTime);
        order.setPrice(totalprice);
        order.setOrderStatus(1);
        order.setPaymentStatus(1);                            
        order.setDiscount(roleId == 3 ? discount : 0);                            
        order.setRemarks(" ");                            
        
        CreateOrderDao.createOrder(order);
        confirmationNum = CreateOrderDao.getConfirmationNumber();
        
        if (cart != null) {
			for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
				int productId = entry.getKey();
				int quantity = entry.getValue();
				AddOrderDetailsDao.createOrderDetails(productId, quantity);
			}
        }
        session.removeAttribute("cart");
        session.removeAttribute("price_cart");
        mv.addObject("message", "Successfully Ordered");
        mv.setViewName("DisplayProduct");
        return mv;
    }
    
    
    public void updateOrderSummaries(Map<String, String> allParams, HttpSession session) {
		@SuppressWarnings("unchecked")
		Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<>();
		}
		System.out.println(allParams);

        int indexes = 0;
        int oldQuantity = 0;
        int itemId = 0;
        int newQuantity =0;
		for (Map.Entry<String, String> entry : allParams.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            if (k.startsWith("allParams")) {
                System.out.println("Index: " + indexes + " Key: " + k + ", Value: " + v);
                if (k.contains("itemId")) {
                	itemId = Integer.parseInt(v);
                	System.out.println("Item Id : " + itemId);
                }
                if(k.contains("oldQuantity")) {
                	oldQuantity = Integer.parseInt(v);
                	System.out.println("Item Id : " + oldQuantity);
                }
                if (k.contains("quantity")) {
                	newQuantity = Integer.parseInt(v);
                	if (newQuantity == 0) {
    					System.out.println("The Item is Deleted");
    					cart.remove(itemId);
    				} else if (oldQuantity != newQuantity) {
    					System.out.println("The Item is Updated");
    					cart.put(itemId, newQuantity);
    				}
                }
            }
        }

		session.setAttribute("cart", cart);
		System.out.println("Updated Session: " + cart);
	}
}