package com.bookshop.servlet.cate;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookshop.entity.BOOKSHOP_CATEGORY;
import com.bookshop.service.BOOKSHOP_CATEGORYDao;

/**
 * Servlet implementation class DoCateDel
 */
@WebServlet("/manage/admin_docatedel")
public class DoCateDel extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		BOOKSHOP_CATEGORYDao.del(id);					
		response.sendRedirect("admin_cateselect");
	}
}
