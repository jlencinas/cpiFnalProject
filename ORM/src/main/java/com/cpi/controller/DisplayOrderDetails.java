package com.cpi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.cpi.dao.AddOrderDetailsDao;
import com.cpi.dao.AddProduct;
import com.cpi.dao.OrderDetailsDao;
import com.cpi.dao.UpdateOrderDetailsDao;
import com.cpi.model.OrderSummary;

@Controller
public class DisplayOrderDetails {

	@RequestMapping("pages/UpdateOrderDetails")
	public ModelAndView updateOrderDetails(@RequestParam Map<String, String> allParams) {
		List<Integer> quantity = new ArrayList<>();
		List<Integer> itemID = new ArrayList<>();
		List<Integer> oldquantity = new ArrayList<>();

		for (String key : allParams.keySet()) {
			if (key.startsWith("quantity")) {
				String index = key.substring(8);
				itemID.add(Integer.parseInt(allParams.get("itemId" + index)));
				oldquantity.add(Integer.parseInt(allParams.get("oldQuantity" + index)));
				quantity.add(Integer.parseInt(allParams.get(key)));
			}
		}

		for (int i = 0; i < quantity.size(); i++) {
			if (quantity.get(i) == 0) {
				System.out.println("The Item is Deleted");
				OrderDetailsDao.deleteOrderDetail(itemID.get(i));
			} else if (oldquantity.get(i) != quantity.get(i)) {
				System.out.println("The Item is Updated");
				OrderDetailsDao.updateOrderDetails(quantity.get(i), itemID.get(i));
			}
		}

		ModelAndView mv = new ModelAndView();
		mv.setViewName("dashboard.jsp");
		return mv;
	}

	@RequestMapping("pages/UpdateOrderSummary")
	public ModelAndView updateOrderSummary(@RequestParam Map<String, String> allParams, HttpSession session) {
		@SuppressWarnings("unchecked")
		Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<>();
		}

		for (String key : allParams.keySet()) {
			if (key.startsWith("quantity")) {
				String index = key.substring(8);
				int itemId = Integer.parseInt(allParams.get("itemId" + index));
				int oldQuantity = Integer.parseInt(allParams.get("oldQuantity" + index));
				int newQuantity = Integer.parseInt(allParams.get(key));
				if (newQuantity == 0) {
					System.out.println("The Item is Deleted");
					cart.remove(itemId);
				} else if (oldQuantity != newQuantity) {
					System.out.println("The Item is Updated");
					cart.put(itemId, newQuantity);
				}
			}
		}

		session.setAttribute("cart", cart);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("dashboard.jsp");
		return mv;
	}

	@RequestMapping("pages/DisplayOrderSummary")
	public ModelAndView displayOrderSummary(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		List<OrderSummary> listOrderSummary = new ArrayList<>();
		float totalprice = 0;
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>) session.getAttribute("cart");

		if (cart != null) {
			for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
				int productId = entry.getKey();
				int quantity = entry.getValue();
				// call AddOrderDetailsDao.displayOrderSummary() to get OrderSummary object
				OrderSummary orderSummary = AddOrderDetailsDao.displayOrderSummary(productId, quantity);
				totalprice += (orderSummary.getOrderPrice() * quantity);
				listOrderSummary.add(orderSummary);
			}
		}

		mv.addObject("orderDetails", listOrderSummary);
		mv.addObject("TotalPrice", totalprice);
		mv.setViewName("orderDetails.jsp");
		return mv;
	}
}