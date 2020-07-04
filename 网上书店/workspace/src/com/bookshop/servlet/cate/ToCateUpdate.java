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
 * Servlet implementation class ToCateUpdate
 */
@WebServlet("/manage/admin_tocateupdate")
public class ToCateUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		BOOKSHOP_CATEGORY cate = BOOKSHOP_CATEGORYDao.selectById(id);
		ArrayList<BOOKSHOP_CATEGORY> catelist = BOOKSHOP_CATEGORYDao.selectAll();
		request.setAttribute("catelist", catelist);
		request.setAttribute("cate1", cate);
		request.getRequestDispatcher("admin_catemodify.jsp").forward(request, response);
	}

}
