package com.bookshop.servlet.home;

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
 * Servlet implementation class SelectProductList
 */
@WebServlet("/selectproductlist")
public class SelectProductList extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<BOOKSHOP_CATEGORY> flist = BOOKSHOP_CATEGORYDao.selectCate("father");
		request.setAttribute("flist", flist);
		ArrayList<BOOKSHOP_CATEGORY> clist = BOOKSHOP_CATEGORYDao.selectCate("child");
		request.setAttribute("clist", clist);
		String fid = request.getParameter("fid");
		String cid = request.getParameter("cid");
		
		int id = 0;
		ArrayList<BOOKSHOP_PRODUCT> list = null; 
		
		if(fid!=null)
		{
			id = Integer.parseInt(fid);
			list = BOOKSHOP_PRODUCTDao.selectAllByFid(id);
		}
		
		if(cid!=null)
		{
			id = Integer.parseInt(cid);
			list = BOOKSHOP_PRODUCTDao.selectAllByCid(id);
		}
		
		request.setAttribute("title",BOOKSHOP_CATEGORYDao.selectById(id).getCATE_NAME());
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("productlist.jsp").forward(request, response);
	}
}
