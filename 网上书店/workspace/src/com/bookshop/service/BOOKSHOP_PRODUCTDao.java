package com.bookshop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bookshop.dao.Basedao;
import com.bookshop.entity.BOOKSHOP_PRODUCT;

public class BOOKSHOP_PRODUCTDao {
	public static int insert(BOOKSHOP_PRODUCT p)
	{
		String sql = "insert into bookshop_product values(null, ?, ?, ?, ?, ?, ?, ?)";
		
		Object[] params = {
							p.getPRODUCT_NAME(),
							p.getPRODUCT_DESCRIPTION(),
							p.getPRODUCT_PRICE(),
							p.getPRODUCT_STOCK(),
							p.getPRODUCT_FID(),
							p.getPRODUCT_CID(),
							p.getPRODUCT_FILENAME()
							};
		
		return Basedao.exectuIUD(sql, params);
	}
	
	public static ArrayList<BOOKSHOP_PRODUCT> selectAll(){
		ArrayList<BOOKSHOP_PRODUCT> list = new ArrayList<BOOKSHOP_PRODUCT>();
		//声明结果集
		ResultSet rs = null;
		//构造连接对象
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;
		//获取所有分类
		String sql = "select * from BOOKSHOP_PRODUCT";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				BOOKSHOP_PRODUCT p = new BOOKSHOP_PRODUCT(
						rs.getInt("PRODUCT_ID"),
						rs.getString("PRODUCT_NAME"),
						rs.getString("PRODUCT_DESCRIPTION"),
						rs.getInt("PRODUCT_PRICE"),
						rs.getInt("PRODUCT_STOCK"),
						rs.getInt("PRODUCT_FID"),
						rs.getInt("PRODUCT_CID"),
						rs.getString("PRODUCT_FILENAME")
						);
						
				list.add(p);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static BOOKSHOP_PRODUCT selectById(int id){
		BOOKSHOP_PRODUCT p = null;
		//声明结果集
		ResultSet rs = null;
		//构造连接对象
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;
		//获取所有分类
		String sql = "select * from BOOKSHOP_PRODUCT where PRODUCT_ID = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
		    p = new BOOKSHOP_PRODUCT(
					rs.getInt("PRODUCT_ID"),
					rs.getString("PRODUCT_NAME"),
					rs.getString("PRODUCT_DESCRIPTION"),
					rs.getInt("PRODUCT_PRICE"),
					rs.getInt("PRODUCT_STOCK"),
					rs.getInt("PRODUCT_FID"),
					rs.getInt("PRODUCT_CID"),
					rs.getString("PRODUCT_FILENAME")
					);
			}
		    System.out.println(p.getPRODUCT_NAME());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	
	public static ArrayList<BOOKSHOP_PRODUCT> selectAllByFid( int fid){
		ArrayList<BOOKSHOP_PRODUCT> list = new ArrayList<BOOKSHOP_PRODUCT>();
		//声明结果集
		ResultSet rs = null;
		//构造连接对象
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;
		//获取所有分类
		String sql = "select * from BOOKSHOP_PRODUCT where PRODUCT_FID = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fid);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				BOOKSHOP_PRODUCT p = new BOOKSHOP_PRODUCT(
						rs.getInt("PRODUCT_ID"),
						rs.getString("PRODUCT_NAME"),
						rs.getString("PRODUCT_DESCRIPTION"),
						rs.getInt("PRODUCT_PRICE"),
						rs.getInt("PRODUCT_STOCK"),
						rs.getInt("PRODUCT_FID"),
						rs.getInt("PRODUCT_CID"),
						rs.getString("PRODUCT_FILENAME")
						);
						
				list.add(p);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static ArrayList<BOOKSHOP_PRODUCT> selectAllByCid( int cid){
		ArrayList<BOOKSHOP_PRODUCT> list = new ArrayList<BOOKSHOP_PRODUCT>();
		//声明结果集
		ResultSet rs = null;
		//构造连接对象
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;
		//获取所有分类
		String sql = "select * from BOOKSHOP_PRODUCT where PRODUCT_CID = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cid);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				BOOKSHOP_PRODUCT p = new BOOKSHOP_PRODUCT(
						rs.getInt("PRODUCT_ID"),
						rs.getString("PRODUCT_NAME"),
						rs.getString("PRODUCT_DESCRIPTION"),
						rs.getInt("PRODUCT_PRICE"),
						rs.getInt("PRODUCT_STOCK"),
						rs.getInt("PRODUCT_FID"),
						rs.getInt("PRODUCT_CID"),
						rs.getString("PRODUCT_FILENAME")
						);
						
				list.add(p);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static ArrayList<BOOKSHOP_PRODUCT> selectAllByIds( ArrayList<Integer> ids){
		ArrayList<BOOKSHOP_PRODUCT> recentList = new ArrayList<BOOKSHOP_PRODUCT>();
		BOOKSHOP_PRODUCT p = null;
		//声明结果集
		ResultSet rs = null;
		//构造连接对象
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;
		//获取所有分类
		String sql = "select * from BOOKSHOP_PRODUCT where PRODUCT_ID = ?";
		try {
			for(int i = 0; i<ids.size();i++)
			{
				ps = conn.prepareStatement(sql);
				ps.setInt(1, ids.get(i));
				rs = ps.executeQuery();
				
				while(rs.next())
				{
					p = new BOOKSHOP_PRODUCT(
							rs.getInt("PRODUCT_ID"),
							rs.getString("PRODUCT_NAME"),
							rs.getString("PRODUCT_DESCRIPTION"),
							rs.getInt("PRODUCT_PRICE"),
							rs.getInt("PRODUCT_STOCK"),
							rs.getInt("PRODUCT_FID"),
							rs.getInt("PRODUCT_CID"),
							rs.getString("PRODUCT_FILENAME")
							);
							
					recentList.add(p);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recentList;
	}
}
