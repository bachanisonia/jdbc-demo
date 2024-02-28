package com.learnjava.springframework;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.learnjava.springframework.dao.JdbcDaoImpl;
import com.learnjava.springframework.model.Circle;

public class JdbcDemo {

	public static void main(String[] args) {
		
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
		
		JdbcDaoImpl dao = context.getBean("jdbcDaoImpl", JdbcDaoImpl.class);
		
		//Circle circle = dao.getCircle(1);
		//System.out.println(circle.getName());
		
		//System.out.println(dao.getCircleCount());
		System.out.println(dao.getCircleName(1));

	}

}
