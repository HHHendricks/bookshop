package com.bookshop.servlet.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookshop.entity.BOOKSHOP_CATEGORY;
import com.bookshop.entity.BOOKSHOP_PRODUCT;
import com.bookshop.service.BOOKSHOP_CATEGORYDao;
import com.bookshop.service.BOOKSHOP_PRODUCTDao;

/**
 * Servlet implementation class ProductSelect
 */
@WebServlet("/manage/admin_productselect")
public class ProductSelect extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<BOOKSHOP_PRODUCT> plist = BOOKSHOP_PRODUCTDao.selectAll();
		request.setAttribute("plist", plist);
		request.getRequestDispatcher("admin_product.jsp").forward(request, response);
	}

}
