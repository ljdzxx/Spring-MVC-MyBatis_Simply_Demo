package com.baobaotao.service;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.baobaotao.domain.User;
import com.baobaotao.tools.DBTools;
import com.baobaotao.mybatis.mapping.UserMapper;

public class TestMybatisLogin {
	
	/**
     * 新增用户
     */
    private static void insertUser(String userName, String password) {
        SqlSession session = DBTools.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = new User(userName, password);
        try {
            mapper.insertUser(user);
            System.out.println(user.toString());
             session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
    }
    
    
    /**
     * 删除用户
     */
    private static void deleteUser(int id){
        SqlSession session=DBTools.getSession();
        UserMapper mapper=session.getMapper(UserMapper.class);
        try {
            mapper.deleteUser(id);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
    }
    
    
    /**
     * 根据id查询用户
     */
    private static void selectUserById(int id){
        SqlSession session=DBTools.getSession();
        UserMapper mapper=session.getMapper(UserMapper.class);
        try {
        	User user = mapper.selectUserById(id);
        	System.out.println(user.toString());
            
            //session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //session.rollback();
        }
    }
    
    /**
     * 模糊查询用户
     */
    private static void selectUserLikeName(String userName){
        SqlSession session=DBTools.getSession();
        UserMapper mapper=session.getMapper(UserMapper.class);
        try {
        	List<User> user = mapper.selectUserLikeName(userName);
        	for(User u:user){
        		System.out.println(u.toString());
        	}
            
            //session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //session.rollback();
        }
    }
    
    /**
     * 查询所有的用户
     */
    private static void selectAllUser(){
        SqlSession session=DBTools.getSession();
        UserMapper mapper=session.getMapper(UserMapper.class);
        try {
	        List<User> user=mapper.selectAllUser();
	        System.out.println(user.toString());
	        //session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //session.rollback();
        }
    }
    
    private static void login(String userName, String password){
    	
    	SqlSession session=DBTools.getSession();
        UserMapper mapper=session.getMapper(UserMapper.class);
        try {
	        User user=mapper.login(userName, password);
	        if(null != user){
	        	System.out.println(user.toString());
	        }
	        //session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //session.rollback();
        }
    }
	
	
	
	public static void main(String[] args) {
		//selectUserById(1);
		//selectUserLikeName("admi");
        //insertUser("lei","123");
		//deleteUser(2);
		
		login("admin", "123456");
		
		selectAllUser();
  }
	
	
}
