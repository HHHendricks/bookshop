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

/**前台用户登录
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	
	/**		处理用户的登录
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
			
			response.sendRedirect("index.jsp");
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
