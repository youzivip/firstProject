package com.springJDBCTest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * JDBC更灵活，完全依赖查询模型动态产生查询语句的综合查询系统中就只能使用JDBC了
 * Spring JDBC将获取连接，处理异常，释放资源的代码都封装在了execute()中
 *
 * Created by wangxiaodi1 on 2019/3/19.
 */
public class CreateNewTable {
    /**
     * JdbcTemplate 是线程安全的，所以所有的DAO都可以共享同一个JdbcTemplate实例，所以JdbcTemplate可以在Spring
     * 配置中定义
     * @return
     */
    public static JdbcTemplate getJdbcTemplate(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://192.168.50.40:3306/test?characterEncoding=UTF-8");
        ds.setUsername("admin");
        ds.setPassword("!Pass4Word");

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(ds);
        return jdbcTemplate;
    }
    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate =  getJdbcTemplate();
        String sql = "create table t_user(user_id int primary key,user_name varchar(64))";
        jdbcTemplate.execute(sql);
    }
}
