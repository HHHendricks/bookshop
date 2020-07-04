package com.bookshop.servlet.home;

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
 * Servlet implementation class indexSelect
 */
@WebServlet("/indexselect")
public class indexSelect extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<BOOKSHOP_CATEGORY> flist = BOOKSHOP_CATEGORYDao.selectCate("father");
		request.setAttribute("flist", flist);
		ArrayList<BOOKSHOP_CATEGORY> clist = BOOKSHOP_CATEGORYDao.selectCate("child");
		request.setAttribute("clist", clist);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
