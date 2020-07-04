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
 * Servlet implementation class DoUserDel
 */
@WebServlet("/manage/admin_douserdel")
public class DoUserDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
			
		//接收用户在网站注册时发送来的信息
		String id=request.getParameter("id");		//得到用户ID
				
		//将单个用户从数据库表中删除
		int count=BOOKSHOP_USERDao.del(id);
		//count表示成功删除数据库的行数
		
		//成功或失败重定向到哪
			
		if(count>0){//删除成功表示行数大于0
			response.sendRedirect("admin_douserselect?cp="+request.getParameter("cpage"));
		}
		else{
			PrintWriter out=response.getWriter();
					
			out.write("<script>");
			out.write("alert('用户添加失败')");
			out.write("location.href='manage/admin_douserselect?cp="+request.getParameter("cpage")+"'");
			out.write("</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		//接收用户在网站注册时发送来的信息
		String ids[]=request.getParameterValues("id[]");		//得到选中的用户名
		
		int count=0;		//表示成功删除掉的行数
		
		for(int i=0;i<ids.length;i++)
			//将用户加入到数据库的用于表中
			count+=BOOKSHOP_USERDao.del(ids[i]);
			//count表示成功插入数据库的行数
		
		//成功或失败重定向到哪
		
		if(count==ids.length){//插入成功表示行数大于0
			response.sendRedirect("/bookshop/manage/admin_douserselect?");
		}
	}

}
