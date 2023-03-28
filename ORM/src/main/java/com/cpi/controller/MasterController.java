package com.cpi.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.cpi.dao.AddProduct;
import com.cpi.dao.AuditOrderDao;
import com.cpi.dao.AuditOrderDetailsDao;
import com.cpi.dao.GetSummaryDetails;
import com.cpi.dao.GetUserDetails;
import com.cpi.dao.OrderDao;
import com.cpi.dao.ProductDao;
import com.cpi.dao.ProductionDao;
import com.cpi.dao.UpdateOrderDetailsDao;
import com.cpi.dao.UsersDao;
import com.cpi.model.AuditOrder;
import com.cpi.model.AuditOrderDetails;
import com.cpi.model.Order;
import com.cpi.model.Product;
import com.cpi.model.Summary;
import com.cpi.model.Users;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

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
	
	@RequestMapping(value = {"pages/checkSession", "checkSession"})
	public ModelAndView checkSession(HttpServletRequest request, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Users user = (Users) session.getAttribute("userAccount");
		if (user!=null) {
			System.out.println("There's Session");
		}
		return mv;
	}
	

	@RequestMapping(value = { "goToLogin", "pages/goToLogin" })
	public ModelAndView loginController(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pages/login.jsp");
		return mv;
	}

	@RequestMapping("goToForgot")
	public String forgotController(Model model) {
		System.out.println("Redirected to Forgot");
		return "pages/forgotpassword.jsp";
	}

	@RequestMapping("shopcontroller")
	public ModelAndView shopController(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pages/display.jsp");
		return mv;
	}

	@RequestMapping(value = { "pages/Login", "Login" })
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
		ModelAndView mv = new ModelAndView();
		UsersDao dao = new UsersDao();
		Users user = dao.getUser(username);
		if (user.getUserId() != 0) {
			if (user.getStatus().equals("DISABLED")) {
				response.addHeader("REQUIRES_AUTH", "3");
				mv.setViewName("DisabledAccount");
			} else {
				if (user.getPassword().equals(password)) {
					HttpSession sesh = request.getSession();
					sesh.setAttribute("userAccount", user);
					response.addHeader("REQUIRES_AUTH", "1");
					mv.setViewName("roleSelect");
				} else {
					response.addHeader("REQUIRES_AUTH", "2");
					mv.setViewName("WrongUsernameOrPass");
				}
			}
		} else {
			response.addHeader("REQUIRES_AUTH", "4");
			mv.setViewName("AccountDoesNotExist");
		}
		return mv;
	}

	@RequestMapping(value = { "pages/roleSelect", "roleSelect" })
	public ModelAndView roleSelect(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		HttpSession sesh = request.getSession();
		Users user = (Users) sesh.getAttribute("userAccount");
		if (user.getRoleId() == 1) {
			response.addHeader("USER_ACCOUNT", "1");
			System.out.println("Admin Here");
			mv.setViewName("adminAccount");
		} else if (user.getRoleId() == 2) {
			response.addHeader("USER_ACCOUNT", "2");
			System.out.println("Producer Here");
			mv.setViewName("producerAccount");
		} else if (user.getRoleId() == 3) {
			response.addHeader("USER_ACCOUNT", "3");
			System.out.println("Order Taker Here");
			mv.setViewName("orderTakerAccount");
		} else if (user.getRoleId() == 4) {
			response.addHeader("USER_ACCOUNT", "4");
			System.out.println("Auditor Here");
			mv.setViewName("auditorAccount");
		}
		return mv;
	}
	
	@RequestMapping(value = { "pages/roleSelectSession", "roleSelectSession" })
	public ModelAndView roleSelectSession(@RequestParam("roleID") int roleid, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		System.out.println(roleid);
		if (roleid == 1) {
			response.addHeader("USER_ACCOUNT", "1");
			System.out.println("Admin Here");
			mv.setViewName("adminAccount");
		} else if (roleid == 2) {
			response.addHeader("USER_ACCOUNT", "2");
			System.out.println("Producer Here");
			mv.setViewName("producerAccount");
		} else if (roleid == 3) {
			response.addHeader("USER_ACCOUNT", "3");
			System.out.println("Order Taker Here");
			mv.setViewName("orderTakerAccount");
		} else if (roleid == 4) {
			response.addHeader("USER_ACCOUNT", "4");
			System.out.println("Auditor Here");
			mv.setViewName("auditorAccount");
		}
		return mv;
	}

	@RequestMapping(value = { "pages/WrongUsernameOrPass", "WrongUsernameOrPass" })
	public ModelAndView wrongUnameOrPass(HttpServletRequest request, HttpServletResponse reponse) {
		System.out.println("Redirected to Login");
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "Wrong Username or Password");
		mv.setViewName("pages/login.jsp");
		return mv;
	}

	@RequestMapping(value = { "pages/DisabledAccount", "DisabledAccount" })
	public ModelAndView disabledAccount(HttpServletRequest request, HttpServletResponse reponse) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "Account Disabled");
		mv.setViewName("pages/login.jsp");
		return mv;
	}

	@RequestMapping(value = { "pages/AccountDoesNotExist", "AccountDoesNotExist" })
	public ModelAndView accountNotExist(HttpServletRequest request, HttpServletResponse reponse) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "Account Not Exist");
		mv.setViewName("pages/login.jsp");
		return mv;
	}

	@RequestMapping(value = { "pages/adminAccount", "adminAccount" })
	public ModelAndView adminAccount(HttpServletRequest request, HttpServletResponse reponse) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "Welcome! Admin");
		mv.setViewName("pages/adminTable.jsp");
		return mv;
	}

	@RequestMapping(value = { "pages/orderTakerAccount", "orderTakerAccount" })
	public ModelAndView orderTakerAccount(HttpServletRequest request, HttpServletResponse reponse) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "Welcome! Order Taker!");
		mv.setViewName("pages/auditTable.jsp");
		return mv;
	}

	@RequestMapping(value = { "pages/producerAccount", "producerAccount" })
	public ModelAndView producerAccount(HttpServletRequest request, HttpServletResponse reponse) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "Welcome! Producer!");
		mv.setViewName("pages/productionTable.jsp");
		return mv;
	}

	@RequestMapping(value = { "pages/auditorAccount", "auditorAccount" })
	public ModelAndView auditorAccount(HttpServletRequest request, HttpServletResponse reponse) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "Welcome! Auditor!");
		mv.setViewName("pages/productionTable.jsp");
		return mv;
	}

	@RequestMapping(value = { "pages/Logout", "Logout" })
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.logout();
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		session.removeAttribute("userAccount");
		mv.setViewName("redirect:/");

		return mv;
	}

	@RequestMapping(value = { "pages/Forgot", "Forgot" })
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
		mv.setViewName("register.jsp");
		return mv;

	}

	@RequestMapping("pages/UpdateEmail")
	public ModelAndView updateEmail(@RequestParam("username") String username,
			@RequestParam("newemail") String newmail) {
		ModelAndView mv = new ModelAndView();
		UsersDao dao = new UsersDao();
		String msg = "";
		Users user = dao.getUser(username);
		if (user.getUserId() != 0) {
			msg = dao.updateEmail(username, newmail);
		}
		return mv;
	}

	@RequestMapping("pages/UpdatePassword")
	public ModelAndView updatePassword(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("newpass") String newpass,
			@RequestParam("conpass") String conpass) {
		ModelAndView mv = new ModelAndView();
		UsersDao dao = new UsersDao();
		String msg = "";
		Users user = dao.getUser(username);
		if (user.getUserId() != 0) {
			if (user.getPassword().equals(password)) {
				if (newpass.equals(conpass)) {
					msg = dao.updatePassword(username, newpass);
					mv.addObject("msg", msg);
				} else {
					msg = "New Password and Confirm Password must be the same";
					mv.addObject("msg", msg);
				}
			} else {
				msg = "Password is incorrect";
				mv.addObject("msg", msg);
			}
		}

		return mv;
	}

	@RequestMapping("pages/Disable") // disable or enable account
	public ModelAndView disable(@RequestParam("username") String uid, @RequestParam("status") String stat) {
		ModelAndView mv = new ModelAndView();
		UsersDao dao = new UsersDao();

		if (stat.equals("ENABLED")) {
			dao.disableUser(uid, "DISABLED");
		} else if (stat.equals("DISABLED")) {
			dao.disableUser(uid, "ENABLED");
		}

		// mv.setViewName("ShowUsers");
		return mv;
	}

	@RequestMapping("pages/Edit") // disable or enable account
	public ModelAndView edit(@RequestParam("username") String uid, @RequestParam("roleid") int roleid) {
		ModelAndView mv = new ModelAndView();
		UsersDao dao = new UsersDao();

		if (roleid != 0) {
			dao.editUser(uid, roleid);
		}
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
		return mv;
	}

	@RequestMapping("pages/updateProduct")
	public ModelAndView updateProduct(@RequestParam("productName") String productName,
			@RequestParam("description") String productDescription, @RequestParam("url") String productPicture,
			@RequestParam("status") int productStatus, @RequestParam("price") Float price,
			@RequestParam("product_id") String productIdString) {

		ModelAndView mv = new ModelAndView();
		Product product = new Product();

		product.setProductID(productIdString);
		product.setProductName(productName);
		product.setProductDescription(productDescription);
		product.setProductPicture(productPicture);
		product.setProductStatus(productStatus);
		product.setProductPrice(price);

		productDao.updateProduct(product);

		return mv;
	}

	@RequestMapping("pages/listOfProducts")
	@ResponseBody
	public String listOfProducts(@RequestParam("page") int page, @RequestParam("rows") int rows) {

		List<Product> products = productDao.getProduct(page, rows);
		JsonObject json = new JsonObject();
		int total = ProductDao.getTotalProductCount();
		json.addProperty("total", total);

		JsonArray rowsJson = new JsonArray();
		for (Product p : products) {
			JsonObject productJson = new JsonObject();

			productJson.addProperty("product_name", p.getProductName());
			productJson.addProperty("product_id", p.getProductID());
			productJson.addProperty("product_description", p.getProductDescription());
			productJson.addProperty("product_picture", p.getProductPicture());

			if (p.getProductStatus() == 0) {
				productJson.addProperty("product_status", "DISABLED");
			} else if (p.getProductStatus() == 1) {
				productJson.addProperty("product_status", "AVAILABLE");
			} else if (p.getProductStatus() == 2) {
				productJson.addProperty("product_status", "REMOVED");
			}
			productJson.addProperty("product_price", p.getProductPrice());
			rowsJson.add(productJson);

		}
		json.add("rows", rowsJson);
		return json.toString();
	}

	@RequestMapping("pages/availableProducts")
	public ModelAndView availableProducts() {
		ModelAndView mv = new ModelAndView();
		List<Product> products = productionDao.availableProducts();
		mv.addObject("products", products);
		mv.setViewName("availableProducts.jsp");
		return mv;
	}

	@RequestMapping(value = { "pages/orderDetails", "orderDetails" })
	public ModelAndView orderDetails(@RequestParam("confirmationNum") String confirmationNum) {
		String[] parts = confirmationNum.split("-");

		String orderSource = parts[0];
		String orderID = parts[1];
		Order order = orderDao.getOrderDetails(orderSource, orderID);
		ModelAndView mv = new ModelAndView();
		mv.addObject("confNum", confirmationNum);
		mv.addObject("order", order);
		mv.setViewName("pages/trackingOrder.jsp");
		return mv;
	}

	@RequestMapping("pages/listOfOrders")
	@ResponseBody
	public String displayOrders(@RequestParam("page") int page, @RequestParam("rows") int rows) {

		List<Order> orders = orderDao.getOrdersByDate(page, rows);
		int total = orderDao.getTotalOrdersCount();

		JsonObject json = new JsonObject();
		json.addProperty("total", total);
		JsonArray rowsJson = new JsonArray();

		for (Order o : orders) {
			JsonObject orderJson = new JsonObject();

			orderJson.addProperty("order_id", o.getOrderId());
			if (o.getOrderStatus() == 1) {
				orderJson.addProperty("order_status", "Pending");
			} else if (o.getOrderStatus() == 2) {
				orderJson.addProperty("order_status", "Ready for Pick up");
			} else if (o.getOrderStatus() == 3) {
				orderJson.addProperty("order_status", "Completed");
			} else if (o.getOrderStatus() == 50) {
				orderJson.addProperty("order_status", "Cancelled");
			} else if (o.getOrderStatus() == 90) {
				orderJson.addProperty("order_status", "Rejected");
			}
			orderJson.addProperty("delivery_date", o.getDeliveryDate());
			if (o.getPaymentStatus() == 1) {
				orderJson.addProperty("payment_status", "Not Paid");
			} else if (o.getPaymentStatus() == 2) {
				orderJson.addProperty("payment_status", "Paid");
			}
			rowsJson.add(orderJson);
		}
		json.add("rows", rowsJson);
		return json.toString();
	}

	@RequestMapping(value = { "pages/updateOrders", "updateOrders" })
	public ModelAndView updateOrderStatusAndPayment(@RequestParam("order_status") String currStatus,
			@RequestParam("payment_status") String currPayment, @RequestParam("status") int orderStatus,
			@RequestParam("paymentStatus") Integer paymentStatus, @RequestParam("order_id") int orderId,
			@RequestParam("remarks") String remarks, HttpSession session) {

		Order order = new Order();
		int intCurrPay = 0;
		int intCurrStatus = 0;
		order.setOrderId(orderId);

		// Audit OrderStatus
		order.setOrderStatus(orderStatus);
		if (currStatus.equals("Not Paid")) {
			intCurrStatus = 1;
		} else if (currStatus.equals("Paid")) {
			intCurrStatus = 2;
		}

		if (intCurrStatus != orderStatus) {
			auditOrderChanges(orderId, "Order Status", orderStatus, intCurrStatus, session);
		}

		// Audit PaymentStatus
		order.setPaymentStatus(paymentStatus);
		if (currPayment.equals("Pending")) {
			intCurrPay = 1;
		} else if (currPayment.equals("Ready for Pick up")) {
			intCurrPay = 2;
		} else if (currPayment.equals("Completed")) {
			intCurrPay = 3;
		} else if (currPayment.equals("Cancelled")) {
			intCurrPay = 50;
		} else if (currPayment.equals("Rejected")) {
			intCurrPay = 90;
		}
		if (intCurrPay != paymentStatus) {
			auditOrderChanges(orderId, "Payment Status", paymentStatus, intCurrPay, session);
		}
		order.setRemarks(remarks);
		orderDao.updateOrder(order);

		ModelAndView mv = new ModelAndView();
		mv.addObject(order);
		return mv;
	}

	private void auditOrderChanges(int orderid, String field, int newvalue, int oldvalue, HttpSession session) {
		AuditOrder auditorder = new AuditOrder();
		Users user = (Users) session.getAttribute("userAccount");
		auditorder.setUsername(user.getUsername());
		auditorder.setItemID(orderid);
		auditorder.setFieldChanged(field);
		auditorder.setNewValue(newvalue);
		auditorder.setOldValue(oldvalue);

		AuditOrderDao.setAuditOrder(auditorder);
	}

	@RequestMapping(value = { "pages/updateProductionsOrder", "updateProductionsOrder" })
	public ModelAndView updateOrderProductStatusAndPayment(@RequestParam("status") String orderStatus,
			@RequestParam("paymentStatus") Integer paymentStatus, @RequestParam("order_id") int orderId,
			@RequestParam("remarks") String remarks) {

		Order order = new Order();
		order.setOrderId(orderId);
		order.setOrderStatus(Integer.parseInt(orderStatus));
		order.setPaymentStatus(paymentStatus);
		order.setRemarks(remarks);
		orderDao.updateOrder(order);

		ModelAndView mv = new ModelAndView();
		mv.addObject(order);
		return mv;
	}

	@RequestMapping(value = { "pages/ordersToday", "ordersToday" })
	@ResponseBody
	public String getOrdersToday(@RequestParam("page") int page, @RequestParam("rows") int rows,
			@RequestParam(value = "filter", required = false) String filter) {
		List<Order> ordersToday = new ArrayList<>();
		System.out.println("Filtering");
		if (filter == null) {
			filter = "all";
			System.out.println("Filtering All");
		}

		if (filter != null & filter.equals("AM")) {
			System.out.println("Filtering AM");
			ordersToday = productionDao.getOrdersTodayByTime(true);
		} else if (filter != null & filter.equals("PM")) {
			System.out.println("Filtering PM");
			ordersToday = productionDao.getOrdersTodayByTime(false);
		} else {
			ordersToday = productionDao.getOrdersToday(page, rows);
		}

		int total = productionDao.getTodayOrdersCount();

		JsonObject json = new JsonObject();
		json.addProperty("total", total);
		JsonArray rowsJson = new JsonArray();

		for (Order o : ordersToday) {
			JsonObject orderJson = new JsonObject();

			orderJson.addProperty("order_id", o.getOrderId());
			if (o.getOrderStatus() == 1) {
				orderJson.addProperty("order_status", "Pending");
			} else if (o.getOrderStatus() == 2) {
				orderJson.addProperty("order_status", "Ready for Pick up");
			} else if (o.getOrderStatus() == 3) {
				orderJson.addProperty("order_status", "Completed");
			} else if (o.getOrderStatus() == 50) {
				orderJson.addProperty("order_status", "Cancelled");
			} else if (o.getOrderStatus() == 90) {
				orderJson.addProperty("order_status", "Rejected");
			}
			orderJson.addProperty("delivery_date", o.getDeliveryDate());
			if (o.getPaymentStatus() == 1) {
				orderJson.addProperty("payment_status", "Not Paid");
			} else if (o.getPaymentStatus() == 2) {
				orderJson.addProperty("payment_status", "Paid");
			}
			rowsJson.add(orderJson);
		}
		json.add("rows", rowsJson);
		return json.toString();
	}

	@RequestMapping("pages/ordersReport")
	@ResponseBody
	public String ordersReport(@RequestParam("page") int page, @RequestParam("rows") int rows) {

		List<Order> orders = orderDao.getOrdersByDate(page, rows);
		int total = orderDao.getTotalOrdersCount();

		JsonObject json = new JsonObject();
		json.addProperty("total", total);
		JsonArray rowsJson = new JsonArray();

		for (Order o : orders) {
			JsonObject orderJson = new JsonObject();

			orderJson.addProperty("order_id", o.getOrderId());
			orderJson.addProperty("customer_name", o.getCustomerFn() + " " + o.getCustomerLn());
			orderJson.addProperty("mobile_number", o.getMobileNumber());
			orderJson.addProperty("order_date", o.getOrderDate().toString());
			orderJson.addProperty("delivery_date", o.getDeliveryDate());
			// orderJson.addProperty("ORDER_DETAILS", o.getMobileNumber());
			orderJson.addProperty("payment_status", o.getPaymentStatus());
			orderJson.addProperty("price", o.getPrice());
			orderJson.addProperty("remarks", o.getRemarks());
			rowsJson.add(orderJson);
			System.out.println("Test");
		}
		json.add("rows", rowsJson);
		return json.toString();
	}

	@RequestMapping("pages/ShowUsers")
	@ResponseBody
	public String showUsers(@RequestParam("page") int page, @RequestParam("rows") int rows)
			throws ClassNotFoundException {

		System.out.println("BEFORE");
		List<Users> users = GetUserDetails.getUsers(page, rows);
		int total = GetUserDetails.getTotalUserCount();

		JsonObject json = new JsonObject();
		json.addProperty("total", total);

		JsonArray rowsJson = new JsonArray();
		for (Users u : users) {
			JsonObject userJson = new JsonObject();

			if (u.getRoleId() == 1) {
				userJson.addProperty("role_id", "Administrator");
			} else if (u.getRoleId() == 2) {
				userJson.addProperty("role_id", "Producer");
			} else if (u.getRoleId() == 3) {
				userJson.addProperty("role_id", "Order Taker");
			} else if (u.getRoleId() == 4) {
				userJson.addProperty("role_id", "Auditor");
			}

			userJson.addProperty("username", u.getUsername());
			userJson.addProperty("email", u.getEmail());
			userJson.addProperty("status", u.getStatus());
			rowsJson.add(userJson);

		}

		json.add("rows", rowsJson);

		return json.toString();
	}

	@RequestMapping("pages/ShowSummary")
	@ResponseBody
	public String showSummary(@RequestParam("page") int page, @RequestParam("rows") int rows)
			throws ClassNotFoundException {

		List<Summary> summary = GetSummaryDetails.getSummary(page, rows);

		int total = GetSummaryDetails.getTotalCount();

		JsonObject json = new JsonObject();
		json.addProperty("total", total);
		JsonArray rowsJson = new JsonArray();

		for (Summary sum : summary) {
			JsonObject sumJson = new JsonObject();
			sumJson.addProperty("prod_id", sum.getProdId());
			sumJson.addProperty("delivery_date", sum.getDeliveryDate());

			if (sum.getPaymentStatus() == 1) {
				sumJson.addProperty("payment_status", "Not Paid");
			} else if (sum.getPaymentStatus() == 2) {
				sumJson.addProperty("payment_status", "Paid");
			}
			sumJson.addProperty("quantity", sum.getQuantity());
			sumJson.addProperty("price", sum.getPrice());
			rowsJson.add(sumJson);
		}
		json.add("rows", rowsJson);
		return json.toString();
	}

	@RequestMapping(value = { "pages/CSV", "CSV" })
	@ResponseBody
	public void downloadSummaryCSV(HttpServletResponse response) throws ClassNotFoundException, IOException {
		String csvFileName = "OrderSummary.csv";
		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
		response.setHeader(headerKey, headerValue);
		List<Summary> summarylist = GetSummaryDetails.getSummaryDownload();
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		String[] header = { "prodId", "deliveryDate", "paymentStatus", "quantity", "price" };
		csvWriter.writeHeader(header);
		for (Summary aSum : summarylist) {
			csvWriter.write(aSum, header);
		}
		csvWriter.close();

	}

	@RequestMapping(value = { "pages/PDF", "PDF" })
	@ResponseBody
	public ModelAndView downloadPDF() throws ClassNotFoundException {
		ModelAndView mv = new ModelAndView();
		List<Summary> summarylist = GetSummaryDetails.getSummaryDownload();
		mv.addObject("summary", summarylist);

		try {
			Document pdfdoc = new Document();
			String home = System.getProperty("user.home");
			PdfWriter writer = PdfWriter.getInstance(pdfdoc, new FileOutputStream(home + "/Downloads/summary.pdf"));
			pdfdoc.open();

			pdfdoc.add(new Paragraph("PRODUCT ID  DELIVERY DATE  PAYMENT STATUS  QUANTITY  PRICE"));
			pdfdoc.add(new Paragraph("___________  _______________  _______________  __________  ______"));
			for (Summary aSum : summarylist) {
				pdfdoc.add(new Paragraph(aSum.getProdId() + "  |  " + aSum.getDeliveryDate() + "  |  "
						+ aSum.getPaymentStatus() + "  |  " + aSum.getQuantity() + "  |  " + aSum.getPrice()));
			}
			pdfdoc.close();
			writer.close();
		} catch (Exception e) {
			System.out.println("Exception Occured PDF");
		}
		return mv;
	}

	@RequestMapping("pages/listOfAuditOrder")
	@ResponseBody
	public String displayAuditOrders(@RequestParam("page") int page, @RequestParam("rows") int rows) {
		List<AuditOrder> auditOrders = AuditOrderDao.getAuditOrders(page, rows);
		int total = AuditOrderDao.getTotalAuditOrdersCount();

		JsonObject json = new JsonObject();
		json.addProperty("total", total);
		JsonArray rowsJson = new JsonArray();

		for (AuditOrder au : auditOrders) {
			JsonObject orderJson = new JsonObject();

			orderJson.addProperty("order_id", au.getItemID());
			orderJson.addProperty("fieldchanged", au.getFieldChanged());
			orderJson.addProperty("oldval", au.getOldValue());
			orderJson.addProperty("newval", au.getNewValue());
			orderJson.addProperty("username", au.getUsername());

			rowsJson.add(orderJson);
		}
		json.add("rows", rowsJson);
		return json.toString();
	}

	@RequestMapping("pages/listOfAuditOrderDetails")
	@ResponseBody
	public String displayAuditOrderDetails(@RequestParam("page") int page, @RequestParam("rows") int rows) {
		List<AuditOrderDetails> auditOrderDetails = AuditOrderDetailsDao.getAuditOrderDetails(page, rows);
		int total = AuditOrderDetailsDao.getTotalAuditOrdersCount();

		JsonObject json = new JsonObject();
		json.addProperty("total", total);
		JsonArray rowsJson = new JsonArray();

		for (AuditOrderDetails au : auditOrderDetails) {
			JsonObject orderJson = new JsonObject();

			orderJson.addProperty("order_id", au.getItemChangeID());
			orderJson.addProperty("item_id", au.getItemID());
			orderJson.addProperty("oldval", au.getOldQuantity());
			orderJson.addProperty("newval", au.getNewQuantity());
			orderJson.addProperty("username", au.getUsername());

			rowsJson.add(orderJson);
		}
		json.add("rows", rowsJson);
		return json.toString();
	}
	

	@RequestMapping("pages/DisplayUpdateOrder")
	public ModelAndView displayUpdateOrder() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("updateorderdetails", UpdateOrderDetailsDao.displayUpdateOrderDetails());
		mv.setViewName("updateOrderDetails.jsp");
		return mv;
	}

	@RequestMapping(value = {"pages/DisplayProduct", "DisplayProduct"})
	public ModelAndView displayPorudct() throws ClassNotFoundException {
		ModelAndView mv = new ModelAndView();
		mv.addObject("products", AddProduct.getProducts());
		mv.setViewName("display.jsp");
		return mv;
	}

}