package com.bookshop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bookshop.dao.Basedao;
import com.bookshop.entity.BOOKSHOP_CATEGORY;
import com.bookshop.entity.BOOKSHOP_USER;

public class BOOKSHOP_CATEGORYDao {
	public static ArrayList<BOOKSHOP_CATEGORY> selectAll(){
		ArrayList<BOOKSHOP_CATEGORY> list = new ArrayList<BOOKSHOP_CATEGORY>();
		//声明结果集
		ResultSet rs = null;
		//构造连接对象
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;
		//获取所有分类
		String sql = "select * from BOOKSHOP_CATEGORY";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				BOOKSHOP_CATEGORY cate = new BOOKSHOP_CATEGORY(
						rs.getInt("CATE_ID"),
						rs.getString("CATE_NAME"),
						rs.getInt("CATE_PARENT_ID")
						);
						
				list.add(cate);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 查询分类并传至前台主页导航栏，分类包括父分类和子分类
	 * @param flag：  flag="father" 父分类  flag="child" 子分类
	 * @return
	 */
	public static ArrayList<BOOKSHOP_CATEGORY> selectCate(String flag){
		ArrayList<BOOKSHOP_CATEGORY> list = new ArrayList<BOOKSHOP_CATEGORY>();
		//声明结果集
		ResultSet rs = null;
		//构造连接对象
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;

		try {
			String sql = null;
			
			if(flag!=null && flag.equals("father"))
			{
				sql = "select * from bookshop_category where CATE_PARENT_ID = 0";
			}
			else
			{
				sql = "select * from bookshop_category where CATE_PARENT_ID != 0";
			}
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				BOOKSHOP_CATEGORY cate = new BOOKSHOP_CATEGORY(
						rs.getInt("CATE_ID"),
						rs.getString("CATE_NAME"),
						rs.getInt("CATE_PARENT_ID")
						);
						
				list.add(cate);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static int insert(BOOKSHOP_CATEGORY cate)
	{
		String sql = "insert into bookshop_category values(null, ?, ?)";
		
		Object[] params = {
							cate.getCATE_NAME(),
							cate.getCATE_PARENT_ID()
							};
		
		return Basedao.exectuIUD(sql, params);
	}
	
	public static BOOKSHOP_CATEGORY selectById(int id)
	{
		BOOKSHOP_CATEGORY cate = null;
		//声明结果集
		ResultSet rs = null;
		//构造连接对象
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;
		
		String sql ="select * from bookshop_category where CATE_ID = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next())
			{
				cate = new BOOKSHOP_CATEGORY(
						rs.getInt("CATE_ID"),
						rs.getString("CATE_NAME"),
						rs.getInt("CATE_PARENT_ID")
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return cate;
	}
	
	public static int update(BOOKSHOP_CATEGORY cate)
	{
		String sql = "update bookshop_category set CATE_NAME=?,CATE_PARENT_ID=? where CATE_ID = ?";
		
		Object[] params = {
				cate.getCATE_NAME(),
				cate.getCATE_PARENT_ID(),
				cate.getCATE_ID()
		};
		
		return Basedao.exectuIUD(sql, params);
	}
	
	public static int del(int id)
	{
		String sql = "delete from bookshop_category where CATE_ID = ?";
		Object[] params = {id};
		return Basedao.exectuIUD(sql, params);
	}
}
