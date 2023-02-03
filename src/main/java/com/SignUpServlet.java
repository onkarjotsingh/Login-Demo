package com;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String UserName = (request.getParameter("UserName"));
		String Password = (request.getParameter("Password"));
		if(UserName.length()==0) {
			List<String> l=new LinkedList<String>();
			l.add("UserName can not be null");
			request.setAttribute("error", l);
			RequestDispatcher rd=request.getRequestDispatcher("SignUp.jsp");
			rd.forward(request, response);
		}
		if(Password.length()==0) {
			List<String> l=new LinkedList<String>();
			l.add("Password can not be null");
			request.setAttribute("error", l);
			RequestDispatcher rd=request.getRequestDispatcher("SignUp.jsp");
			rd.forward(request, response);
		}
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
			int flag=1;
			while(rs.next()) {
				String UN=rs.getString("UserName");
				if(UN.equals(UserName)) {
					flag=0;
					break;
				}
			}
			if(flag==0) {
				List<String> l=new LinkedList<String>();
				l.add("UserName already exist");
				request.setAttribute("error", l);
				RequestDispatcher rd=request.getRequestDispatcher("SignUp.jsp");
				rd.forward(request, response);
//				PrintWriter out=response.getWriter();
//				out.println("UserName already exist");
			}
			else {
			// insert query
			String insert = "insert into usertable(UserName,Password) values(?,?)";
			PreparedStatement pstmt = con.prepareStatement(insert);
			pstmt.setString(1, UserName);
			pstmt.setString(2, Password);
			pstmt.executeUpdate();
			PrintWriter out=response.getWriter();
			out.println("User Registered");
			}
			
		}catch(Exception e) {
			
		}
	}

}
