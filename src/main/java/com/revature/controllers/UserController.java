package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.repo.UserRepoImpl;
import com.revature.services.UserService;

public class UserController {
	private UserService us = new UserService();
	private ObjectMapper om = new ObjectMapper();

	public void getAllUsers(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
		List<User> list = us.findAll();

		String json = om.writeValueAsString(list);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
		
		HttpSession ses=req.getSession();
		String username=(String) ses.getAttribute("username");
		UserRepoImpl userRepo= new UserRepoImpl();
		User user=userRepo.findByUsername(username);
		
		if((user.getRole().getRoleId()==1)||(user.getRole().getRoleId()==2)) {
				pw.print(json);
				resp.setStatus(200);
		}else {
			pw.print(om.writeValueAsString("you do not have access with this user"));
			resp.setStatus(401);
		}

	}

	public void findById(HttpServletRequest req,HttpServletResponse resp, int id) throws IOException {
		User u = us.findById(id);
		String json = om.writeValueAsString(u);
		System.out.println(json);
		PrintWriter pw = resp.getWriter();
	
		
		HttpSession ses=req.getSession();
		String username=(String) ses.getAttribute("username");
		UserRepoImpl userRepo= new UserRepoImpl();
		User user=userRepo.findByUsername(username);
		
		if((user.getRole().getRoleId()==1)||(user.getRole().getRoleId()==2)||u.getUserId()==id) {
				pw.print(json);
				resp.setStatus(200);
		}else {
			pw.print(om.writeValueAsString("you do not have access with this user"));
			resp.setStatus(401);
		}

	}
	
	public void addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		StringBuilder sb = new StringBuilder();

		BufferedReader reader = req.getReader();

		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line=reader.readLine();
		}

		String body = new String(sb);

		User u = om.readValue(body, User.class);
		

		if (us.addUser(u)) {
			resp.setStatus(201);
		} else {
			resp.setStatus(400);
		}
	}
	
//	public void findByUsername(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		User u = us.findByUsername();
//		String json = om.writeValueAsString(u);
//		System.out.println(json);
//
//		PrintWriter pw = resp.getWriter();
//		pw.print(json);
//		resp.setStatus(200);
//	}

	public void putUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		HttpSession ses=req.getSession();
		String username=(String) ses.getAttribute("username");
		UserRepoImpl userRepo= new UserRepoImpl();
		User user=userRepo.findByUsername(username);
		
		
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		PrintWriter pw = resp.getWriter();
		String body = new String(sb);
		User u = om.readValue(body, User.class);

		if((user.getRole().getRoleId()==1|| (user.getUserId()==u.getUserId()))) {
			if (us.updateUser(u)) {
				resp.setStatus(200);
			} else {
				resp.setStatus(400);
			}
		}else {
			pw.print(om.writeValueAsString("you do not have access with this user"));
			resp.setStatus(401);
		}

	}
	
	public void login(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException{
		UserDTO u = new UserDTO(); 
		UserRepoImpl urepo=new UserRepoImpl(); 
	
		
		
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			sb.append(line);
			line = reader.readLine();

		}
		
		String body = new String(sb); 
		u = om.readValue(body, UserDTO.class); 
		PrintWriter out = resp.getWriter();
		
		if(us.loginVerification(u)) {
			HttpSession ses=req.getSession();
			ses.setAttribute("username", u.username);
			resp.setStatus(200);
			
		}else {
			resp.setStatus(400);
			
		}
		
	
			
	}
	
	public void logout(HttpServletRequest req, HttpServletResponse resp)
	throws IOException, ServletException{
		if(req.getSession(false)==null) {
			return;
		}
		//this gets my session
		HttpSession ses=req.getSession(); 
		
		//writing my message
		PrintWriter out=resp.getWriter();
		//invalidating the session to logout
		if(ses!=null) {
			out.print("<span style='color:Blue; text-align:center'>You Logged out!</span>");
			resp.setStatus(200);
			ses.invalidate();
		}else {
			out.print("<span style='color:Red; text-align:center'>Failed to Logout</span>");
			resp.setStatus(400);
			
		}
		resp.sendRedirect("http://www.google.com");
	
		
		
	}

	public void service(HttpServletRequest req, HttpServletResponse resp)
	throws IOException,ServletException{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		//gets a currently active session if one exists.
		HttpSession ses = req.getSession(false);
		
		if (ses == null) {
			out.print("<h1>YOU ARE NOT LOGGED IN!!!!!!!!</h1>");
		} else {
			String name = (String) ses.getAttribute("username");
			out.print("<h2>Welcome "+name+", you are successfully logged in.</h2>");
			out.print("<a href='logout'>Click here to log out.</a>");
		}
		
	}
	
	
	
	
}
