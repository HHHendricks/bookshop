package com.bookshop.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.entity.BOOKSHOP_USER;
import com.bookshop.service.BOOKSHOP_USERDao;

/**后台管理员登录
 * Servlet implementation class AdminLogin
 */
@WebServlet("/manage/adminlogin")
public class AdminLogin extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
								
		//接收用户在网站注册时发送来的信息
		String user_id=request.getParameter("userID");		//用户名
		String user_password=request.getParameter("password");	//密码
				
		int count=BOOKSHOP_USERDao.selectByNM(user_id,user_password);		//根据用户名和密码在数据库中查找是否有这个用户
				
				
		if(count>0) {		//查找到有这么个用户
			HttpSession session=request.getSession();
			BOOKSHOP_USER user=BOOKSHOP_USERDao.selectAdmin(user_id,user_password);
			session.setAttribute("user", user);
			session.setAttribute("isLogin", "1");
			
			if(user.getUSER_STATUS()==2) {		//登录的用户是管理员
				session.setAttribute("isAdminLogin", "1");
				response.sendRedirect("/bookshop/manage/admin_index.jsp");
			}
			else {		//登录的是普通用户
				response.sendRedirect("/bookshop/index.jsp");
			}
		}
		else {
			PrintWriter out=response.getWriter();
					
			out.write("<script>");
			out.write("alert('登录失败！');");
			out.write("location.href='login.jsp'");
			out.write("</script>");
			out.close();
		}
	}

}
