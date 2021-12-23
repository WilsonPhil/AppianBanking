package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.controllers.AccountController;
import com.revature.controllers.UserController;

public class FrontControllerServlet extends HttpServlet {

	private String BaseURL = null;
	private AccountController accountControl = new AccountController();
	private UserController userControl = new UserController();

	@Override
	public void init(ServletConfig config) throws ServletException {
		BaseURL = config.getInitParameter("BaseURL");
	}
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setStatus(404);

		final String URL = req.getRequestURI().replace(BaseURL, "");

		System.out.println(URL);

		String[] sections = URL.split("/");

		System.out.println(sections);

		switch (sections[0]) {
		case "accounts":
			if (req.getMethod().equals("GET")) {
				if((sections.length==3)&&sections[1].equals("status")) {
						int id = Integer.parseInt(sections[2]);
						accountControl.AccountByStatus(req,resp, id);
				}
				else if (sections.length == 2) {
					int id = Integer.parseInt(sections[1]);
					accountControl.getAccount(req,resp, id);
				} else {
					accountControl.getAllAccounts(req,resp);
				}

			} else if (req.getMethod().equals("POST")) {
				accountControl.addAccount(req, resp);
			} else if (req.getMethod().equals("PUT") && sections.length == 2) {
				accountControl.putAccount(req, resp);
			} else if (req.getMethod().equals("DELETE") && sections.length == 2) {
				accountControl.deleteAccount(resp, sections[1]);
			} else if (req.getMethod().equals("PATCH") && sections.length == 3) {
				if (sections[1].equals("withdraw")) {
					accountControl.patchWithdrawAccount(req, resp);
				} else if (sections[1].equals("deposit")) {
					accountControl.patchDepositAccount(req, resp);
				}
			}
			break;
		case "users":
			if (req.getMethod().equals("GET")) {
				if (sections.length == 2) {
					int id = Integer.parseInt(sections[1]);
					userControl.findById(req,resp, id);
				} else {
					userControl.getAllUsers(req,resp);
				}
			} else if (req.getMethod().equals("PUT") && sections.length == 2) {
				userControl.putUser(req, resp);

			}else if (req.getMethod().equals("POST")) {
				userControl.addUser(req, resp);
			}
			break;
		case "login":
			if(req.getMethod().equals("POST")) {
				userControl.login(req, resp);
			}
			break;
		case"logout":
			if(req.getMethod().equals("POST")) {
				userControl.logout(req, resp);
			}
			break;
		case"success":
			if(req.getMethod().equals("GET")) {
				userControl.service(req,resp);
			}
			break;
		
			
			
			
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getMethod().equals("PATCH")) {
			doPatch(req, resp);
		} else {
			super.service(req, resp);
		}
	}

}
