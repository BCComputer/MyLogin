package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cofig.DbConnection;

@WebServlet("/regForm")

public class Register extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {

			Connection con = DbConnection.getConnection();

			String insert_sql_query = "INSERT INTO user VALUES(?,?)";
			PreparedStatement ps = con.prepareStatement(insert_sql_query);
			ps.setString(1, username);
			ps.setString(2, password);
			int count = ps.executeUpdate();
			System.out.print(count);
			if (count > 0) {
				out.println("<h3 style = 'color: green'> Registered Successfully </h3>");
				RequestDispatcher rd = request.getRequestDispatcher("/login.html");
				rd.include(request, response);
			} else {
				out.println("<h3 style = 'color: red'> User not registered because of error </h3>");
				RequestDispatcher rd = request.getRequestDispatcher("/registration.html");
				rd.include(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
