package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cofig.DbConnection;
import com.model.User;

@WebServlet("/logInForm")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println(username);
		System.out.println(password);
		

		try {

			Connection con = DbConnection.getConnection();

			String insert_sql_query = "SELECT * FROM user WHERE username=? AND password=?";
			PreparedStatement ps = con.prepareStatement(insert_sql_query);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				
				HttpSession session = request.getSession();
				session.setAttribute("session_user", user);
				
				RequestDispatcher rd = request.getRequestDispatcher("/profile.jsp");
				rd.forward(request, response);
				
			} else {
				out.println("<h3 style = 'color: red'> Email and password did not matched. </h3>");
				RequestDispatcher rd = request.getRequestDispatcher("/login.html");
				rd.include(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
