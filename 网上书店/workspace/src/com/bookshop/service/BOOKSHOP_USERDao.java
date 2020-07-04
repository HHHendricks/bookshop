package com.bookshop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bookshop.dao.Basedao;
import com.bookshop.entity.BOOKSHOP_USER;

//操作数据库的类，进行各种数据准备并调用数据库操作类中的各种方法
public class BOOKSHOP_USERDao {
	/**
	 * 用户数据加入数据库
	 * @param u
	 * @return
	 */
	public static int insert(BOOKSHOP_USER u) {
		String sql="insert into bookshop_user values(?, ?, ?, ?, DATE_FORMAT(?, '%Y-%m-%d'), ?, ?, ?, ?, ?)";
		
		Object[] params= {
				u.getUSER_ID(),
				u.getUSER_NAME(),
				u.getUSER_PASSWORD(),
				u.getUSER_SEX(),
				u.getUSER_BIRTHDAY(),
				u.getUSER_IDENTITY_CODE(),
				u.getUSER_EMAIL(),
				u.getUSER_MOBILE(),
				u.getUSER_ADDRESS(),
				u.getUSER_STATUS()}; 
		
		return Basedao.exectuIUD(sql, params);
	}
	
	public static int del(String id){
		String sql="delete from bookshop_user where USER_ID=? and USER_STATUS!=2";
		
		Object[] params= {id};
		
		return Basedao.exectuIUD(sql, params);
	}
	
	/**
	 * 更新数据库中的用户用户数据
	 * @param u
	 * @return
	 */
	
	public static int update(BOOKSHOP_USER u) {
		String sql="update bookshop_user set USER_NAME=?, USER_PASSWORD=?, USER_SEX=?, USER_BIRTHDAY=DATE_FORMAT(?,'%Y-%m-%d'),USER_IDENTITY_CODE=?, USER_EMAIL=?, USER_MOBILE=?, USER_ADDRESS=?, USER_STATUS=? where USER_ID=?";
		
		Object[] params= {
				u.getUSER_NAME(),
				u.getUSER_PASSWORD(),
				u.getUSER_SEX(),
				u.getUSER_BIRTHDAY(),
				u.getUSER_IDENTITY_CODE(),
				u.getUSER_EMAIL(),
				u.getUSER_MOBILE(),
				u.getUSER_ADDRESS(),
				u.getUSER_STATUS(),
				u.getUSER_ID()
		};
		
		return Basedao.exectuIUD(sql, params);
	}
	
	public static int selectByName(String id) {		//根据ID查找用户数量
		int count= 0;		//查找结果的数量
		
		Connection conn=Basedao.getconn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		
		try {
			String sql="select count(*) from bookshop_user where USER_ID=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				count=rs.getInt(1);	//获取总记录数
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		
		
		
		return count;
	}
	
	/**
	 * 获取总记录数和总页数
	 * @param count
	 * @return
	 */
	
	public static int[] totalPage(int count,String keyword) {
		int arr[]= {0,1};		//第一个是总记录数，第二个是总页数
		
		Connection conn=Basedao.getconn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		
		try {
			String sql="";
			
			if(keyword!=null) {		//查看用户是否有搜索特定用户
				sql="select count(*) from bookshop_user where USER_NAME like ?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, "%"+keyword+"%");
			}
			else {
				sql="select count(*) from bookshop_user";
				ps=conn.prepareStatement(sql);
			}
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				arr[0]=rs.getInt(1);	//获取总记录数
				
				if(arr[0]%count==0)
					arr[1]=arr[0]/count;
				else
					arr[1]=arr[0]/count+1;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		
		
		
		return arr;
	}
	
	
	public static ArrayList<BOOKSHOP_USER>selectAll(int cpage,int count,String keyword){		//根据页码查询相关数据
		ArrayList<BOOKSHOP_USER> list =new ArrayList<BOOKSHOP_USER>();
		//声明结果集
		ResultSet rs=null;
		//获取链接对象，即连接数据库
		Connection conn=Basedao.getconn();
		//准备sql语句
		PreparedStatement ps=null;
		
		
		
		try {
			String sql="";
			
			if(keyword!=null) {
				sql="select * from bookshop_user where USER_NAME like ? order by USER_BIRTHDAY desc limit ?, ?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, "%"+keyword+"%");
				ps.setInt(2, (cpage-1)*count);
				ps.setInt(3, count);
			}
			else {
				
				sql="select * from bookshop_user order by USER_BIRTHDAY desc limit ?, ?";
				ps=conn.prepareStatement(sql);
			
				ps.setInt(1, (cpage-1)*count);
				ps.setInt(2, count);
			}
			
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				BOOKSHOP_USER u=new BOOKSHOP_USER(
						rs.getString("USER_ID"),
						rs.getString("USER_NAME"),
						rs.getString("USER_PASSWORD"),
						rs.getString("USER_SEX"),
						rs.getString("USER_BIRTHDAY"),
						rs.getString("USER_IDENTITY_CODE"),
						rs.getString("USER_EMAIL"),
						rs.getString("USER_MOBILE"),
						rs.getString("USER_ADDRESS"),
						rs.getInt("USER_STATUS")
						
					);
				
				
				list.add(u);
			
			}
			
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		
		
		
		return list;
	}
	
	/**
	 * 通过ID查找用户
	 * @param cpage
	 * @param count
	 * @param keyword
	 * @return
	 */
	
	public static BOOKSHOP_USER selectById(String id){		//查找指定ID的用户实体
		BOOKSHOP_USER u=null;
		//声明结果集
		ResultSet rs=null;
		//获取链接对象，即连接数据库
		Connection conn=Basedao.getconn();
		//准备sql语句
		PreparedStatement ps=null;
		
		
		
		try {
			String sql="select m.*, DATE_FORMAT(m.user_birthday, '%Y-%m-%d')birthday from bookshop_user m where USER_ID=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				u=new BOOKSHOP_USER(
						rs.getString("USER_ID"),
						rs.getString("USER_NAME"),
						rs.getString("USER_PASSWORD"),
						rs.getString("USER_SEX"),
						rs.getString("birthday"),
						rs.getString("USER_IDENTITY_CODE"),
						rs.getString("USER_EMAIL"),
						rs.getString("USER_MOBILE"),
						rs.getString("USER_ADDRESS"),
						rs.getInt("USER_STATUS")
					);
			}
				
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		
		return u;
	}


	public static int selectByNM(String id,String password) {			//根据用户名和密码判断用户登陆的情况
		int count=0;
		
		Connection conn=Basedao.getconn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		
		try {
			String sql="select count(*) from bookshop_user where USER_ID=? and USER_PASSWORD=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				count=rs.getInt(1);	//获取总记录数
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		
		return count;
	}
	
	/**
	 * 根据用户名和密码找出对应的用户实体
	 * @param id
	 * @param password
	 * @return
	 */
	
	public static BOOKSHOP_USER selectAdmin(String id,String password) {
		BOOKSHOP_USER u=null;
		//声明结果集
		ResultSet rs=null;
		//获取链接对象，即连接数据库
		Connection conn=Basedao.getconn();
		//准备sql语句
		PreparedStatement ps=null;
		
		
		
		try {
			String sql="select m.*, DATE_FORMAT(m.user_birthday, '%Y-%m-%d')birthday from bookshop_user m where USER_ID=? and USER_PASSWORD=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				u=new BOOKSHOP_USER(
						rs.getString("USER_ID"),
						rs.getString("USER_NAME"),
						rs.getString("USER_PASSWORD"),
						rs.getString("USER_SEX"),
						rs.getString("birthday"),
						rs.getString("USER_IDENTITY_CODE"),
						rs.getString("USER_EMAIL"),
						rs.getString("USER_MOBILE"),
						rs.getString("USER_ADDRESS"),
						rs.getInt("USER_STATUS")
					);
			}
				
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		
		return u;
	}
}