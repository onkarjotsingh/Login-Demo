package com;

import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String UserName = (request.getParameter("UserName"));
		String Password = (request.getParameter("Password"));
		System.out.println(UserName);
		System.out.println(Password);
		String op=request.getParameter("button");
		if("SignUp".equalsIgnoreCase(op)) {
			response.sendRedirect("SignUp.jsp");
		}
		else {
			try {
				// Load
				String driver = "com.mysql.cj.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306/logindemo";
				String username = "root";
				String password = "root";
				
				// Register
				Class.forName(driver);
				
				// Establish the Connection
				Connection con = DriverManager.getConnection(url, username, password);
				System.out.println("connection");
				
				//check query
				String check="select * from usertable";
				Statement check1=con.createStatement();
				ResultSet rs=check1.executeQuery(check);
				int flag=0;
				while(rs.next()) {
					String UN=rs.getString("UserName");
					if(UN.equals(UserName)) {
						System.out.println("UserName"+UN);
						String PS=rs.getString("Password");
						if(PS.equals(Password)) {
							System.out.println("Password"+PS);
							flag=1;
							break;
						}
					}
				}
				if(flag==0) {
					List<String> l=new LinkedList<String>();
					l.add("UserName or Password is wrong");
					request.setAttribute("error", l);
					RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
					rd.forward(request, response);
				}
				if(flag==1) {
					HttpSession session=request.getSession();
					session.setAttribute("UserName", UserName);
					//session.setAttribute("Password", Password);
					response.sendRedirect("Welcome.jsp");
				}
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}

}
