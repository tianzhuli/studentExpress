/**
 * 
 */
package com.me.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * @author Administrator
 *
 */
@Repository
public class UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int getMatch(String userName,String passWord){
		String sqlstr="select count(*) from consumer_user where username=? and password=?";
		System.out.println(userName);
		System.out.println(passWord);
		return jdbcTemplate.queryForInt(sqlstr, new Object[]{userName,passWord});
	}
}
