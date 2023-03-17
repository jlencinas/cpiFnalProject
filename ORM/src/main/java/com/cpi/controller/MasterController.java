package com.cpi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cpi.dao.GetUserDetails;
import com.cpi.dao.OrderDao;
import com.cpi.dao.ProductDao;
import com.cpi.dao.ProductionDao;
import com.cpi.dao.UsersDao;
import com.cpi.model.Order;
import com.cpi.model.Product;
import com.cpi.model.Users;

@Controller
class Controllers {

	private OrderDao orderDao;
	private ProductDao productDao;
	private ProductionDao productionDao;

	@Autowired(required = false)
	public Controllers(OrderDao orderDao, ProductDao productDao, ProductionDao productionDao) {

		this.orderDao = orderDao;
		this.productDao = productDao;
		this.productionDao = productionDao;
	}
	
	@RequestMapping("goToLogin")
	public ModelAndView loginController(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Redirected to Login");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pages/login.jsp");
		return mv;
	}

	@RequestMapping("shopcontroller")
	public ModelAndView shopController(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pages/display.jsp");
		return mv;
	}
	
	@RequestMapping("Login")
	public ModelAndView login(HttpServletRequest request,
			@RequestParam(required = false, name = "username") String username,
			@RequestParam(required = false, name = "password") String password) {
		System.out.println(username);
		System.out.println(password);
		ModelAndView mv = new ModelAndView();
		UsersDao dao = new UsersDao();

		Users user = dao.getUser(username);
		if (user.getUserId() != 0) {
			if (user.getStatus().equals("ENABLED")) {
				if (user.getPassword().equals(password)) {
					HttpSession sesh = request.getSession();
					sesh.setAttribute("userAccount", user);
					mv.setViewName("pages/dashboard.jsp");
				} else {
					mv.addObject("msg", "Wrong Password");
					mv.setViewName("pages/login.jsp");
				}
			} else {
				mv.addObject("msg", "Account Disabled");
				mv.setViewName("pages/login.jsp");
			}
		} else {
			mv.addObject("msg", "Account Does Not Exist");
			mv.setViewName("pages/login.jsp");
		}
		return mv;
	}

	@RequestMapping("pages/Logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		mv.setViewName("forgotpassword.jsp");
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
			@RequestParam("newemail") String newmail, @RequestParam("newpass") String newpass,
			@RequestParam("conpass") String conpass) {
		ModelAndView mv = new ModelAndView();
		UsersDao dao = new UsersDao();
		String msg = "";
		Users user = dao.getUser(username);

		if (user.getUserId() != 0) {
			if (newpass.equals(conpass)) {
				if (password.isEmpty()) {
					msg = dao.updateUser(user, newpass, newmail);
					mv.addObject("msg", msg);
					mv.setViewName("dashboard.jsp");
				} else if (password.equals(user.getPassword())) {
					msg = dao.updateUser(user, newpass, newmail);
					mv.addObject("msg", msg);
					mv.setViewName("dashboard.jsp");
				} else {
					msg = "Password is incorrect";
					mv.addObject("msg", msg);
					mv.setViewName("dashboard.jsp");
				}
			} else {
				msg = "New Password and Confirm Password must be the Same!";
				mv.addObject("msg", msg);
				mv.setViewName("dashboard.jsp");
			}
		} else {
			msg = "User Does Not Exist";
			mv.addObject("msg", msg);
			mv.setViewName("dashboard.jsp");
		}
		return mv;
	}

	@RequestMapping("pages/Disable") // disable or enable account
	public ModelAndView disable(@RequestParam("uid") int uid, @RequestParam("stat") String stat) {
		ModelAndView mv = new ModelAndView();
		UsersDao dao = new UsersDao();

		if (stat.equals("ENABLED")) {
			dao.disableUser(uid, "DISABLED");
			mv.addObject("msg", "Account Disabled");
		} else if (stat.equals("DISABLED")) {
			dao.disableUser(uid, "ENABLED");
			mv.addObject("msg", "Account Enabled");
		}

		mv.setViewName("dashboard.jsp");
		return mv;
	}

	@RequestMapping("pages/Edit") // disable or enable account
	public ModelAndView edit(@RequestParam("uid") int uid, @RequestParam("roleid") int roleid) {
		ModelAndView mv = new ModelAndView();
		UsersDao dao = new UsersDao();

		if (roleid != 0) {
			dao.editUser(uid, roleid);
			mv.addObject("msg", "Account Role Changed");
		}
		mv.setViewName("dashboard.jsp");
		return mv;
	}

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

	@RequestMapping("pages/updateProduct")
	public ModelAndView updateProduct(@RequestParam("productName") String productName,
			@RequestParam("description") String productDescription, @RequestParam("url") String productPicture,
			@RequestParam("status") int productStatus, @RequestParam("price") Float price,
			@RequestParam("productID") String productIdString) {

		ModelAndView mv = new ModelAndView();
		Product product = new Product();

		product.setProductID(productIdString);
		product.setProductName(productName);
		product.setProductDescription(productDescription);
		product.setProductPicture(productPicture);
		product.setProductStatus(productStatus);
		product.setProductPrice(price);

		productDao.updateProduct(product);
		mv.addObject("product", product);
		mv.setViewName("dashboard.jsp");

		return mv;
	}

	@RequestMapping("pages/listOfProducts")
	public ModelAndView listOfProducts() {

		ModelAndView mv = new ModelAndView();
		List<Product> products = productDao.getProduct();
		mv.addObject("products", products);
		mv.setViewName("listOfProducts.jsp");
		return mv;
	}
	
	@RequestMapping("pages/availableProducts")
	public ModelAndView availableProducts() {
		ModelAndView mv = new ModelAndView();
		List <Product> products = productionDao.availableProducts();
		mv.addObject("products", products);
		mv.setViewName("availableProducts.jsp");
		return mv;
		}

	@RequestMapping("pages/orderDetails")
	public ModelAndView orderDetails(@RequestParam("mobileNumber") String mobileNumber) {
		Order order = orderDao.getOrderDetails(mobileNumber);
		ModelAndView mv = new ModelAndView();
		mv.addObject("order", order);
		mv.setViewName("orderDetails.jsp");

		return mv;
	}

	@RequestMapping("pages/orderTaker")
	public ModelAndView displayOrders() {
		ModelAndView mv = new ModelAndView();
		List<Order> orders = orderDao.getOrdersByDate();
		mv.addObject("allOrders", orders);
		mv.setViewName("orderTaker.jsp");

		return mv;
	}

	@RequestMapping("pages/updateOrders")
	public ModelAndView updateOrderStatusAndPayment(@RequestParam("orderStatus") Integer orderStatus,
			@RequestParam("paymentStatus") Integer paymentStatus, @RequestParam("orderID") int orderId) {

		Order order = orderDao.getOrder(orderId);

		order.setOrderStatus(orderStatus);
		order.setPaymentStatus(paymentStatus);
		orderDao.updateOrder(order);

		ModelAndView mv = new ModelAndView();
		List<Order> allOrders = orderDao.getOrdersByDate();
		mv.addObject("allOrders", allOrders);
		mv.setViewName("orderTaker.jsp");
		return mv;

	}

	@RequestMapping("pages/ordersToday")
	public ModelAndView getOrdersToday(@RequestParam(value = "filter", required = false) String filter) {

		ModelAndView mv = new ModelAndView();
		List<Order> ordersToday = new ArrayList<>();

		if (filter == null) {
			filter = "all";
		}

		if (filter != null & filter.equals("AM")) {
			ordersToday = productionDao.getOrdersTodayByTime(true);
		} else if (filter != null & filter.equals("PM")) {
			ordersToday = productionDao.getOrdersTodayByTime(false);
		} else {
			ordersToday = productionDao.getOrdersToday();
		}
		mv.addObject("ordersToday", ordersToday);
		mv.setViewName("ordersToday.jsp");
		return mv;
	}
}