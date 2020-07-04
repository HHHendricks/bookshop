package com.bookshop.servlet.product;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookshop.entity.BOOKSHOP_PRODUCT;
import com.bookshop.service.BOOKSHOP_PRODUCTDao;
import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * Servlet implementation class DoProductAdd
 */
@WebServlet("/manage/admin_doproductadd")
public class DoProductAdd extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建SmartUpload对象
		SmartUpload su = new SmartUpload();
		//初始化
		su.initialize(this.getServletConfig(), request, response);
		//上传过程
		try {
			su.upload();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取上传文件对象
		Files fs = su.getFiles();
		File f = fs.getFile(0);
		//获取上传文件名
		String fname = f.getFileName();
		//保存到指定文件夹
		try {
			String path =  request.getSession().getServletContext().getRealPath("/images/products");
			System.out.println(path);
			f.saveAs(path+'/'+fname,su.SAVE_PHYSICAL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Request req1 = su.getRequest();
		
		String pname = req1.getParameter("productName");
		String id = req1.getParameter("parentId");
		String price = req1.getParameter("productPrice");
		String desc = req1.getParameter("productDesc");
		String stock = req1.getParameter("productStock");
		
		BOOKSHOP_PRODUCT p = new BOOKSHOP_PRODUCT(
				0,
				pname,
				desc,
				Integer.parseInt(price),
				Integer.parseInt(stock),
				Integer.parseInt(id.split("~")[0]),
				Integer.parseInt(id.split("~")[1]),
				fname
				);
		
		
		int count = BOOKSHOP_PRODUCTDao.insert(p);
		
		if(count>0) {
			response.sendRedirect("admin_productselect");
		}
		else
		{
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('商品添加失败')");
			out.write("location.href='manage/admin_toproductadd'");
			out.write("</script>");
		}
	}

}
