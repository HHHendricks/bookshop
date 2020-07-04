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
 * Servlet implementation class DoUserUpdate
 */
@WebServlet("/manage/admin_douserupdate")
public class DoUserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=utf-8");
				
				//接收用户在网站更新时发送来的信息
				String user_id=request.getParameter("USER_ID");		//用户名
				String user_name=request.getParameter("USER_NAME");
				String user_password=request.getParameter("USER_PASSWORD");
				String user_sex=request.getParameter("USER_SEX");
				String user_birthday=request.getParameter("USER_BIRTHDAY");
				String user_email=request.getParameter("USER_EMAIL");
				String user_mobile=request.getParameter("USER_MOBILE");
				String user_address=request.getParameter("USER_ADDRESS");
				String user_status=request.getParameter("userStauts");
				
				int status=1;
				
				if(user_status!=null) {
					status=Integer.parseInt(user_status);
				}
				//创建用户实体
				BOOKSHOP_USER u=new BOOKSHOP_USER(user_id,user_name,user_password,user_sex,user_birthday,null,user_email,user_mobile,user_address,status);
				
				//将用户加入到数据库的用户表中，覆盖原来的行
				int count=BOOKSHOP_USERDao.update(u);
				//count表示成功修改数据库的行数
				
				//成功或失败重定向到哪
				
				if(count>0){//插入成功表示行数大于0
					response.sendRedirect("admin_douserselect?cp="+request.getParameter("cpage"));
				}
				else{
					PrintWriter out=response.getWriter();
					
					out.write("<script>");
					out.write("alert('用户更新失败')");
					out.write("location.herf='manage/admin_touserupdate?id="+user_id+"'");
					out.write("</script>");
				}
	}

}
