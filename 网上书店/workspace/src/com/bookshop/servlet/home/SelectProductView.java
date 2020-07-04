package com.bookshop.servlet.home;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.entity.BOOKSHOP_CATEGORY;
import com.bookshop.entity.BOOKSHOP_PRODUCT;
import com.bookshop.service.BOOKSHOP_CATEGORYDao;
import com.bookshop.service.BOOKSHOP_PRODUCTDao;

/**
 * Servlet implementation class SelectProductView
 */
@WebServlet("/selectproductview")
public class SelectProductView extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<BOOKSHOP_CATEGORY> flist = BOOKSHOP_CATEGORYDao.selectCate("father");
		request.setAttribute("flist", flist);
		ArrayList<BOOKSHOP_CATEGORY> clist = BOOKSHOP_CATEGORYDao.selectCate("child");
		request.setAttribute("clist", clist);
		
		String id = request.getParameter("id");
		
		//获取最近访问
		HttpSession session = request.getSession();
		//从session获取ids
		ArrayList<Integer> ids = (ArrayList<Integer>)session.getAttribute("ids"); 
		
		if(ids == null)
		{
			ids = new ArrayList<Integer>();
		}
		
		//ids最多存放五个，否则删除首个
		if(ids.size()>=5)
		{
			ids.remove(0);
		}
		
		//添加到ids列表，但是不能重复
		if(id!=null && !(ids.contains(Integer.parseInt(id))))
		{
			ids.add(Integer.parseInt(id));
		}
		
		session.setAttribute("ids", ids);
		
		ids = (ArrayList<Integer>)session.getAttribute("ids");
		
		if(ids!=null)
		{
			ArrayList<BOOKSHOP_PRODUCT> recentList = BOOKSHOP_PRODUCTDao.selectAllByIds(ids);
			request.setAttribute("recentList", recentList);
		}
		
		//获取父分类等信息
		BOOKSHOP_PRODUCT p = null;
		
		if(id!=null)
		{
			p = BOOKSHOP_PRODUCTDao.selectById(Integer.parseInt(id));
			request.setAttribute("p", p);
		}
		
		if(p!=null)
		{
			int cid = p.getPRODUCT_CID();
			ArrayList<BOOKSHOP_PRODUCT> classlist = BOOKSHOP_PRODUCTDao.selectAllByCid(cid);
			request.setAttribute("classlist", classlist);
			
			BOOKSHOP_CATEGORY cate = BOOKSHOP_CATEGORYDao.selectById(cid);
			request.setAttribute("cate", cate);
		}
		
		request.getRequestDispatcher("productview.jsp").forward(request, response);
	}

}
