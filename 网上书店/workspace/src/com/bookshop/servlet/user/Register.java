package com.bookshop.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookshop.entity.BOOKSHOP_USER;
import com.bookshop.service.BOOKSHOP_USERDao;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
				
		//接收用户在网站注册时发送来的信息
		String user_id=request.getParameter("ID");		//用户名
		String user_name=request.getParameter("NAME");
		String user_password=request.getParameter("PASSWORD");
		String user_sex=request.getParameter("SEX");
		String user_birthday=request.getParameter("BIRTHDAY");
		String user_email=request.getParameter("EMAIL");
		String user_mobile=request.getParameter("MOBILE");
		String user_address=request.getParameter("ADDRESS");
		//创建用户实体
		BOOKSHOP_USER u=new BOOKSHOP_USER(user_id,user_name,user_password,user_sex,user_birthday,null,user_email,user_mobile,user_address,1);
				
		//将用户加入到数据库的用于表中
		int count=BOOKSHOP_USERDao.insert(u);
		//count表示成功插入数据库的行数
				
		//成功或失败重定向到哪
		
		if(count>0){//插入成功表示行数大于0
			response.sendRedirect("login.jsp");
		}
		else{
			PrintWriter out=response.getWriter();
					
			out.write("<script>");
			out.write("alert('用户注册失败！');");
			out.write("location.href='reg.jsp'");
			out.write("</script>");
		}
	}

}
