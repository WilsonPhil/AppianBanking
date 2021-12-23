package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.repo.UserRepoImpl;
import com.revature.services.AccountService;

public class AccountController {

	private AccountService as = new AccountService();
	private ObjectMapper om = new ObjectMapper();

	public void getAllAccounts(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		List<Account> list = as.findAll();

		String json = om.writeValueAsString(list);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();

		HttpSession ses = req.getSession();
		String username = (String) ses.getAttribute("username");
		UserRepoImpl userRepo = new UserRepoImpl();
		User user = userRepo.findByUsername(username);

		if ((user.getRole().getRoleId() == 1) || (user.getRole().getRoleId() == 2)) {
			pw.print(json);
			resp.setStatus(200);
		} else {
			pw.print(om.writeValueAsString("you do not have access with this user"));
			resp.setStatus(401);
		}

	}

	public void getAccount(HttpServletRequest req, HttpServletResponse resp, int id) throws IOException {

		Account a = as.findById(id);

		// Convert Java object into a JSON string that can be written to the body of an
		// HTTP response
		String json = om.writeValueAsString(a);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();

		HttpSession ses = req.getSession();
		String username = (String) ses.getAttribute("username");
		UserRepoImpl userRepo = new UserRepoImpl();
		User user = userRepo.findByUsername(username);

		if ((user.getRole().getRoleId() == 1) || (user.getRole().getRoleId() == 2)
				|| user.getUserId() == a.getUser().getUserId()) {
			pw.print(json);
			resp.setStatus(200);
		} else {
			pw.print(om.writeValueAsString("you do not have access with this user"));
			resp.setStatus(401);
		}
	}

	public void AccountByStatus(HttpServletRequest req,HttpServletResponse resp, int id) throws IOException {

		List<Account> a = as.findByStatus(id);

		// Convert Java object into a JSON string that can be written to the body of an
		// HTTP response
		String json = om.writeValueAsString(a);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		
		HttpSession ses = req.getSession();
		String username = (String) ses.getAttribute("username");
		UserRepoImpl userRepo = new UserRepoImpl();
		User user = userRepo.findByUsername(username);
		
		if ((user.getRole().getRoleId() == 1) || (user.getRole().getRoleId() == 2)||user.getUserId()==id) {
			pw.print(json);
			resp.setStatus(200);
		} else {
			pw.print(om.writeValueAsString("you do not have access with this user"));
			resp.setStatus(401);
		}
	}

	public void addAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		HttpSession ses = req.getSession();
		String username = (String) ses.getAttribute("username");
		UserRepoImpl userRepo = new UserRepoImpl();
		User user = userRepo.findByUsername(username);

		StringBuilder sb = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line = reader.readLine();
		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		String body = new String(sb);
		Account a = om.readValue(body, Account.class);
		PrintWriter pw = resp.getWriter();
		if ((user.getRole().getRoleId() == 1 || (user.getRole().getRoleId() == 2)
				|| (a.getUser().getUserId() == user.getUserId()))) {
			if (as.addAccount(a)) {
				resp.setStatus(201);
			} else {
				resp.setStatus(400);
			}
		} else {
			pw.print(om.writeValueAsString("you do not have access with this user"));
			resp.setStatus(401);
		}
	}

	public void addFullAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		StringBuilder sb = new StringBuilder();

		BufferedReader reader = req.getReader();

		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = new String(sb);

		Account a = om.readValue(body, Account.class);

		if (as.addFullAccount(a)) {
			resp.setStatus(201);
		} else {
			resp.setStatus(400);
		}
	}

	public void putAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession ses = req.getSession();
		String username = (String) ses.getAttribute("username");
		UserRepoImpl userRepo = new UserRepoImpl();
		User user = userRepo.findByUsername(username);
		PrintWriter pw = resp.getWriter();
		
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		String body = new String(sb);
		Account a = om.readValue(body, Account.class);
		
		if ((user.getRole().getRoleId() == 1)) {
			if (as.updateAccount(a)) {
				resp.setStatus(201);
			} else {
				resp.setStatus(400);
			}
		} else {
			pw.print(om.writeValueAsString("you do not have access with this user"));
			resp.setStatus(401);
		}
}

	public void patchAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = new String(sb);

		Account a = om.readValue(body, Account.class);

		if (as.updatePartialAccount(a)) {
			resp.setStatus(200);
		} else {
			resp.setStatus(400);
		}
	}

	public void patchWithdrawAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = new String(sb);

		Account a = om.readValue(body, Account.class);
		System.out.println(a);
		if (as.withdraw(a)) {
			resp.setStatus(200);
		} else {
			resp.setStatus(400);
		}
	}

	public void patchDepositAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = new String(sb);

		Account a = om.readValue(body, Account.class);

		if (as.deposit(a)) {
			resp.setStatus(200);
		} else {
			resp.setStatus(400);
		}
	}

	public void deleteAccount(HttpServletResponse resp, String mark) {
		try {
			int id = Integer.parseInt(mark);
			if (as.deleteAccount(id)) {
				resp.setStatus(204);
			} else {
				resp.setStatus(400);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			resp.setStatus(418);
		}
	}

}
