<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="admin_menu.jsp" %>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">商品管理</span></div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="/bookshop/manage/admin_toproductadd"><i class="icon-font"></i>新增商品</a>
                        <a id="updateOrd" href="javascript:void(0)"><i class="icon-font"></i>更新排序</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>ID</th>
                            <th>商品名称</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach var="p" items="${plist}">
                        	<tr>
                        		<td>${p.PRODUCT_ID}</td>
                        		<td><img src="../images/products/${p.PRODUCT_FILENAME}" width="80" height="80">${p.PRODUCT_NAME }</td>
                        		<td><a href="">修改&nbsp;&nbsp;</a><a href="">删除</a></td>
                        	</tr>
                        </c:forEach> 
                        <script>
                        	function catedel(id)
                        	{
                        		if(confirm("你确定要删除这个分类吗？"))
                        		{
                        			location.href="admin_docatedel?id="+id;	
                        		}
                        	}
                        </script>     
                    </table>
                    <div class="list-page"> 2 条 1/1 页</div>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</body>
</html>