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

/**处理后台登录的过滤器，在用户未登录的时候禁止访问后台管理的页面
 * Servlet Filter implementation class AdminLogin
 */
@WebFilter("/manage/*")
public class AdminLogin implements Filter {

    /**
     * Default constructor. 
     */
    public AdminLogin() {
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
		
		
		HttpSession session=req.getSession();
		
		String flag=(String)session.getAttribute("isAdminLogin");
		
		String request_uri=req.getRequestURI();		//获取当前页面的路径地址
		String ctxPath=req.getContextPath();		//获取当前页面的根目录路径，即/bookshop
		String uri=request_uri.substring(ctxPath.length());	//获取当前页面去掉根目录之后的路径,即去掉了/bookshop
		
		if(uri.indexOf(".css")>0) {			//过滤掉css文件，防止CSS文件的ContentType被设置成html
			chain.doFilter(req, resp);
			return;
		}
		resp.setContentType("text/html;charset=UTF-8");
		
		if(uri.contains("admin_")) {	//对登录状况进行过滤，未登录则不允许访问后台的管理页面，即被过滤掉
			if(flag!=null && flag.equals("1")) {
				chain.doFilter(req, resp);
			}else {
				PrintWriter out=resp.getWriter();
				out.write("<script>");
				out.write("alert('请先登录再访问');");
				out.write("location.href='login.jsp';");
				out.write("</script>");
				out.close();
				return;
			}
		}else {	//不包含admin_字段的页面都可以通过
			chain.doFilter(req, resp);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
