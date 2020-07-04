package com.bookshop.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookshop.service.BOOKSHOP_USERDao;

/**
 * Servlet implementation class AdminUserIdCheck
 */
@WebServlet("/manage/adminuseridcheck")
public class AdminUserIdCheck extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
				
		String name=request.getParameter("name");
		
		//count表示查询的结果数
		int count=BOOKSHOP_USERDao.selectByName(name);
		
		PrintWriter out=response.getWriter();
		//count>0表示数据库中已经存有结果
		if(count>0)
			out.print("false");
		else
			out.print("true");
		
		out.close();
	}


}
