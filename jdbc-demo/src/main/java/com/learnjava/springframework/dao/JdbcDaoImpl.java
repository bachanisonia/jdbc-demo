package com.learnjava.springframework.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.learnjava.springframework.model.Circle;

@Component
public class JdbcDaoImpl {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	public int getCircleCount() {
		
		String sql = "select count(*) from circle";
	
		return jdbcTemplate.queryForInt(sql);
	}
	
	public String getCircleName(int circleId) {
		
		String sql = "select name from circle where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {circleId} ,String.class);
	}


	public Circle getCircle(int circleId) {
		
		Connection conn = null;
		Circle circle = null;
		
		try {
			
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from circle where id = ?");
			ps.setInt(1, circleId);
			
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				circle = new Circle(circleId, rs.getString("name"));
			}
			
			rs.close();
			ps.close();
			
			
		}
		catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			}
			catch (SQLException s) {
				s.printStackTrace();
			}
		}
		
		return circle;
		
	}
	
	

}
