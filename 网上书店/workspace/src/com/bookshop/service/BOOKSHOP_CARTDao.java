package com.bookshop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bookshop.dao.Basedao;
import com.bookshop.entity.BOOKSHOP_CART;
import com.bookshop.entity.BOOKSHOP_CATEGORY;

public class BOOKSHOP_CARTDao {
	public static int insert(BOOKSHOP_CART c)
	{
		String sql = "insert into bookshop_cart values(null,?,?,?,?,?,?,?,1)";
		
		Object[] params = {
				c.getCART_P_FILENAME(),
				c.getCART_P_NAME(),
				c.getCART_P_PRICE(),
				c.getCART_QUANTITY(),
				c.getCART_P_STOCK(),
				c.getCART_P_ID(),
				c.getCART_U_ID()
		};
		
		return Basedao.exectuIUD(sql, params);
	}
	
	public static ArrayList<BOOKSHOP_CART> selectCart(String uid){
		ArrayList<BOOKSHOP_CART> list = new ArrayList<BOOKSHOP_CART>();
		//声明结果集
		ResultSet rs = null;
		//构造连接对象
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;
		//获取所有分类
		String sql = "select * from BOOKSHOP_CART where CART_U_ID = ? and CART_VALID = 1 order by CART_ID desc";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			rs = ps.executeQuery();
			
			
			while(rs.next())
			{
				BOOKSHOP_CART c = new BOOKSHOP_CART(
						rs.getInt("CART_ID"),
						rs.getString("CART_P_FILENAME"),
						rs.getString("CART_P_NAME"),
						rs.getInt("CART_P_PRICE"),
						rs.getInt("CART_QUANTITY"),
						rs.getInt("CART_P_STOCK"),
						rs.getInt("CART_P_ID"),
						rs.getString("CART_U_ID"),
						rs.getInt("CART_VALID")
						);
						
				list.add(c);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static BOOKSHOP_CART checkSameCart(String uid, String pid)
	{
		BOOKSHOP_CART temp = null;
		//声明结果集
		ResultSet rs = null;
		//构造连接对象
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;
		//获取所有分类
		String sql = "select * from BOOKSHOP_CART where CART_U_ID = ? and CART_P_ID = ? and CART_VALID = 1 order by CART_ID desc";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setInt(2, Integer.parseInt(pid));
			rs = ps.executeQuery();
					
					
			while(rs.next())
			{
				temp = new BOOKSHOP_CART(
						rs.getInt("CART_ID"),
						rs.getString("CART_P_FILENAME"),
						rs.getString("CART_P_NAME"),
						rs.getInt("CART_P_PRICE"),
						rs.getInt("CART_QUANTITY"),
						rs.getInt("CART_P_STOCK"),
						rs.getInt("CART_P_ID"),
						rs.getString("CART_U_ID"),
						rs.getInt("CART_VALID")
						);
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return temp;
	}
	
	public static int updateNum(int id, int n )
	{
		String sql = "update bookshop_cart set CART_QUANTITY = ? where CART_ID = ?";
		
		Object[] params = {n, id};
		
		return Basedao.exectuIUD(sql, params);
	}
	
	public static int deleteById(int id)
	{	
		if(id == 0) 
		{
			
			String sql = "delete from bookshop_cart";
			
			return Basedao.exectuIUD(sql, null);
			
		}
		else
		{
			String sql2 = "delete from bookshop_cart where CART_ID = ?";
		
			Object[] params2 = {id};
		
			return Basedao.exectuIUD(sql2, params2);
		}
	}
	
	public static BOOKSHOP_CART getCartById(String id)
	{
		BOOKSHOP_CART temp = null;
		//声明结果集
		ResultSet rs = null;
		//构造连接对象
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;
		//获取所有分类
		String sql = "select * from BOOKSHOP_CART where CART_ID = ? and CART_VALID = 1 order by CART_ID desc";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
					
					
			while(rs.next())
			{
				temp = new BOOKSHOP_CART(
						rs.getInt("CART_ID"),
						rs.getString("CART_P_FILENAME"),
						rs.getString("CART_P_NAME"),
						rs.getInt("CART_P_PRICE"),
						rs.getInt("CART_QUANTITY"),
						rs.getInt("CART_P_STOCK"),
						rs.getInt("CART_P_ID"),
						rs.getString("CART_U_ID"),
						rs.getInt("CART_VALID")
						);
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return temp;
	}
	
}
