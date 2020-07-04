package com.bookshop.servlet.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookshop.entity.BOOKSHOP_USER;
import com.bookshop.service.BOOKSHOP_USERDao;

/**
 * Servlet implementation class DoUserSelect
 */
@WebServlet("/manage/admin_douserselect")
public class DoUserSelect extends HttpServlet {
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int cpage=1;		//当前页
		int count=5;		//每页显示的条数
		
		//获取用户指定的页面
		String cp=request.getParameter("cp");
		
		//接受用户搜索的关键字
		String keyword=request.getParameter("keyword");
		
		if(cp!=null) {
			cpage=Integer.parseInt(cp);
		}
		
		int arr[]=BOOKSHOP_USERDao.totalPage(count,keyword);
		
		
		//获取所有的用户记录
		ArrayList<BOOKSHOP_USER> list=BOOKSHOP_USERDao.selectAll(cpage,count,keyword);
		
		//放到请求对象域里
		request.setAttribute("userlist", list);
		request.setAttribute("tsum", arr[0]);		//总记录数
		request.setAttribute("tpage", arr[1]);		//总页数
		request.setAttribute("cpage", cpage);		//当前页
		
		request.getRequestDispatcher("admin_user.jsp").forward(request, response);
	}

	
}
