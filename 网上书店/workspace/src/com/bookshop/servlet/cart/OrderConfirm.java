package com.bookshop.servlet.cart;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookshop.entity.BOOKSHOP_CART;
import com.bookshop.service.BOOKSHOP_CARTDao;

/**
 * Servlet implementation class OrderConfirm
 */
@WebServlet("/orderconfirm")
public class OrderConfirm extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String ids = request.getParameter("ids");
		
//		String isLogin = (String)session.getAttribute("isLogin");
//		
//		BOOKSHOP_USER user = (BOOKSHOP_USER)session.getAttribute("name");
		
//		if(user!=null && isLogin.equals("1"))
//		{
//			String uid = (String)user.getUSER_ID();
		
		//测试
		String uid = "temp";
		String id[] = ids.split(",");
		ArrayList<BOOKSHOP_CART>list = new ArrayList<BOOKSHOP_CART>();
		for(int i =0; i<id.length; i++)
		{
			BOOKSHOP_CART c = BOOKSHOP_CARTDao.getCartById(id[i]);
			list.add(c);
		}

		request.setAttribute("idList", list);
		request.getRequestDispatcher("order.jsp").forward(request, response);
		
//		}
//		else
//		{
//			PrintWriter out = response.getWriter();
//			out.write("<script>");
//			out.write("alert('游客请先登录再进行购物')");
//			out.write("<location.href='login.jsp'");
//			out.write("</script>");
//			out.close();
//		}
	}
}
