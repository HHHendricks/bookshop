package com.bookshop.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class DoUserAdd
 */
@WebFilter("/manage/admin_douseradd")
public class DoUserAdd implements Filter {

    /**
     * Default constructor. 
     */
    public DoUserAdd() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		
		//设置字符集
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String user_id=request.getParameter("ID");		//用户名
		String user_name=request.getParameter("NAME");
		String user_password=request.getParameter("PASSWORD");
		String user_repassword=request.getParameter("REPASSOWRD");
		String user_address=request.getParameter("ADDRESS");

		
		PrintWriter out=resp.getWriter();
		
		if(user_id.equals("")) {	//进行过滤，被过滤掉的东西就直接return，下面的语句就不执行
			out.write("<script>");
			out.write("alert('用户名不能为空');");
			out.write("location.href='admin_useradd.jsp';");
			out.write("</script>");
			out.close();
			return;
		}

		if(user_name.equals("")) {
			out.write("<script>");
			out.write("alert('用户姓名不能为空');");
			out.write("location.href='admin_useradd.jsp.jsp';");
			out.write("</script>");
			out.close();
			return;
		}
		
		if(user_password.equals("")) {
			out.write("<script>");
			out.write("alert('密码不能为空');");
			out.write("location.href='admin_useradd.jsp.jsp';");
			out.write("</script>");
			out.close();
			return;
		}
		
		if(user_repassword.equals("")||user_repassword.equals(user_password)) {
			out.write("<script>");
			out.write("alert('两次输入的密码不一致');");
			out.write("location.href='admin_useradd.jsp.jsp';");
			out.write("</script>");
			out.close();
			return;
		}
		
		if(user_address.equals("")) {
			out.write("<script>");
			out.write("alert('收货地址不能为空');");
			out.write("location.href='admin_useradd.jsp.jsp';");
			out.write("</script>");
			out.close();
			return;
		}

		// pass the request along the filter chain
		chain.doFilter(req, resp);		//通过过滤则使用这条语句，被过滤的数据会直接return掉
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
