package com.bookshop.servlet.cate;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookshop.entity.BOOKSHOP_CATEGORY;
import com.bookshop.service.BOOKSHOP_CATEGORYDao;

/**
 * Servlet implementation class ToCateAdd
 */
@WebServlet("/manage/amin_tocateadd")
public class ToCateAdd extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<BOOKSHOP_CATEGORY> catelist = BOOKSHOP_CATEGORYDao.selectAll();
		request.setAttribute("catelist", catelist);
		request.getRequestDispatcher("admin_cateadd.jsp").forward(request, response);
	}

}
