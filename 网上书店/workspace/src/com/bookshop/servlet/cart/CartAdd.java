package com.bookshop.servlet.cart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.entity.BOOKSHOP_CART;
import com.bookshop.entity.BOOKSHOP_PRODUCT;
import com.bookshop.entity.BOOKSHOP_USER;
import com.bookshop.service.BOOKSHOP_CARTDao;
import com.bookshop.service.BOOKSHOP_PRODUCTDao;

/**
 * Servlet implementation class CartAdd
 */
@WebServlet("/cartadd")
public class CartAdd extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BOOKSHOP_PRODUCT p = null;
		
		String pid = request.getParameter("id");
		String count = request.getParameter("count");
		String flag = request.getParameter("flag");
		
		HttpSession session = request.getSession();
		
//		String isLogin = (String)session.getAttribute("isLogin");
//		
//		BOOKSHOP_USER user = (BOOKSHOP_USER)session.getAttribute("name");
		
//		if(user!=null && isLogin.equals("1"))
//		{
//			String uid = (String)user.getUSER_ID();
			
			//测试用
			String uid = "temp";
			
			//通过用户和产品ID查重
			BOOKSHOP_CART temp = BOOKSHOP_CARTDao.checkSameCart(uid,pid);
			
			if(temp!=null)
			{
				int preNum = temp.getCART_QUANTITY();
				int newNum = preNum + Integer.parseInt(count);
				if(newNum>=5)
				{
					newNum = 5;
				}
				BOOKSHOP_CARTDao.updateNum(temp.getCART_ID(),newNum);
			}
			else
			{
				if(pid!=null)
				{
					p = BOOKSHOP_PRODUCTDao.selectById(Integer.parseInt(pid));
					BOOKSHOP_CART c = new BOOKSHOP_CART(
							0,
							p.getPRODUCT_FILENAME(),
							p.getPRODUCT_NAME(),
							p.getPRODUCT_PRICE(),
							Integer.parseInt(count),
							p.getPRODUCT_STOCK(),
							p.getPRODUCT_ID(),
							uid,
							1
							);
					
					BOOKSHOP_CARTDao.insert(c);
				}
	//			
	//		}
	//		else
	//		{
	//			PrintWriter out = response.getWriter();
	//			out.write("<script>");
	//			out.write("alert('游客请先登录再进行购物')");
	//			out.write("<location.href='login.jsp'");
	//			out.write("</script>");
	//			out.close();
	//			return;
	//		}
			}
			
			System.out.println(flag);
			
			if(flag.equals("z"))
			{
				response.sendRedirect("showcart");
			}
			else
			{
				response.sendRedirect("selectproductview?id="+pid);
			}
	}

}
