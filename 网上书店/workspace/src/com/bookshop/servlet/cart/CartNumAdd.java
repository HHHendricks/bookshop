package com.bookshop.servlet.cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookshop.service.BOOKSHOP_CARTDao;

/**
 * Servlet implementation class CartNumAdd
 */
@WebServlet("/cartnumadd")
public class CartNumAdd extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String count = request.getParameter("count");
		String id = request.getParameter("id");

		BOOKSHOP_CARTDao.updateNum(Integer.parseInt(id), Integer.parseInt(count));
	}
}
