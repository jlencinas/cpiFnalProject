package com.cpi.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cpi.dao.OrderDao;
import com.cpi.dao.ProductDao;
import com.cpi.dao.UsersDao;
import com.cpi.model.Order;
import com.cpi.model.Product;
import com.cpi.model.Users;

@Controller
class Controllers {
	
	private OrderDao orderDao;
	
	@Autowired(required = false)
	public Controllers (OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	@RequestMapping("pages/Login")
	public ModelAndView login(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password) {
		ModelAndView mv = new ModelAndView();
		UsersDao dao = new UsersDao();
		
		
		Users user = dao.getUser(username, password);

		if (user.getStatus().equals("ENABLED")) {
			HttpSession sesh = request.getSession(); 
			sesh.setAttribute("userAccount", user);
			mv.setViewName("dashboard.jsp");
		} 
		
		else {
			mv.addObject("message", "Account Disabled");
			mv.setViewName("../index.jsp");
		}
		return mv;

	}

	@RequestMapping("pages/Logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.logout();
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Logged Out Successfully");
		mv.setViewName("../index.jsp");
		session.invalidate();
		return mv;
	}

	@RequestMapping("pages/Forgot")
	public ModelAndView forgot(@RequestParam("username") String username, @RequestParam("email") String email) {
		ModelAndView mv = new ModelAndView();
		UsersDao dao = new UsersDao();
		String msg = dao.forgotUser(username, email);

		mv.addObject("message", msg);
		mv.setViewName("../index.jsp");
		return mv;

	}

	@RequestMapping("pages/Register")
	public ModelAndView register(@RequestParam("username") String username, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("roleid") int roleid) {
		ModelAndView mv = new ModelAndView();

		Users u = new Users();
		
		u.setUsername(username);
		u.setEmail(email);
		u.setPassword(password);
		u.setRoleId(roleid);

		UsersDao dao = new UsersDao();
		String msg = dao.createUser(u);

		mv.addObject("msg", msg);
		mv.setViewName("dashboard.jsp");
		return mv;

	}

	@RequestMapping("pages/Update")
	public ModelAndView update(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("new email") String newmail, @RequestParam("new pass") String newpass,
			@RequestParam("con pass") String conpass) {
		ModelAndView mv = new ModelAndView();
		UsersDao dao = new UsersDao();
		System.out.println(username);
		System.out.println(password);
		System.out.println(newmail);
		System.out.println(newpass);
		System.out.println(conpass);
		
		String msg = "";
		Users user = dao.getUser(username, password);

		if (user != null) {
			if (newpass.equals(conpass)) {
				msg = dao.updateUser(user, newpass, newmail);
				mv.addObject("msg", msg);
				mv.setViewName("dashboard.jsp");
			} 
			else {
				msg = "New Password and Confirm Password must be the Same!";
				mv.addObject("msg", msg);
				mv.setViewName("dashboard.jsp");
			}
			
		} 
		else {
			msg = "Password is Incorrect";
			mv.addObject("msg", msg);
			mv.setViewName("dashboard.jsp");
		}
		return mv;
	}

	@RequestMapping("pages/Edit") //disable or enable account
	public ModelAndView edit(@RequestParam("uid") int uid, @RequestParam("stat") String stat) {
		ModelAndView mv = new ModelAndView();
		UsersDao dao = new UsersDao();
		
		if(stat.equals("ENABLED")) {
			dao.editUser(uid,"DISABLED");
		}
		else if (stat.equals("DISABLED")) {
			dao.editUser(uid, "ENABLED");
		}
		
		mv.setViewName("disable.jsp");
		return mv;
	}

	/* @RequestMapping("newProduct") */

	@RequestMapping("pages/NewProduct")
	public ModelAndView newProduct(@RequestParam("productName") String productName,
			@RequestParam("description") String productDescription, @RequestParam("url") String productPicture,
			@RequestParam("status") int productStatus, @RequestParam("price") float price) {

		ModelAndView mv = new ModelAndView();
		Product product = null;
		int prodId = 0;

		ProductDao dao = new ProductDao();
		product = dao.newProduct(prodId, productName, productDescription, productPicture, productStatus, price);
		mv.addObject("product", product);
		mv.setViewName("dashboard.jsp");

		return mv;
	}

	@RequestMapping("pages/UpdateProduct")
	public ModelAndView updateProduct(@RequestParam("productID") int productId,
			@RequestParam("productName") String productName, @RequestParam("description") String productDescription,
			@RequestParam("url") String productPicture, @RequestParam("status") int productStatus,
			@RequestParam("price") Float price) {

		ModelAndView mv = new ModelAndView();
		ProductDao dao = new ProductDao();
		dao.updateProduct(productId, productName, productDescription, productPicture, productStatus, price);
		mv.addObject("updatedProduct", dao);
		mv.setViewName("dashboard.jsp");

		return mv;
	}
	
	@RequestMapping("pages/orderDetails")
	public ModelAndView orderDetails (@RequestParam("mobileNumber") int mobileNumber) 
	{
		Order order = orderDao.getOrderDetails(mobileNumber);	
		ModelAndView mv = new ModelAndView();
		mv.addObject("order", order);
		mv.setViewName("pages/orderDetails");
		
		return mv;
	}
}