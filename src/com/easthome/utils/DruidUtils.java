package com.easthome.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
* 德鲁伊工具类
* */
public class DruidUtils {
    private DruidUtils(){}
    private static DataSource dataSource = null;
    static {
        try {
            Properties properties = new Properties();
            //输入路径
            InputStream in = DruidUtils.class.getClassLoader().getResourceAsStream("druid-config.properties");
            //将配置文件的内容存入properties集合
            properties.load(in);
            //创建德鲁伊连接池 将集合中的内容导入
            dataSource = DruidDataSourceFactory.createDataSource(properties);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取连接
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    //获取DataSource
    public static DataSource getDataSource(){
        return dataSource;
    }

    //关闭资源
    public static void close(Connection con , Statement statement){
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //重载
    public static void close(Connection con , Statement statement , ResultSet resultSet){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close(con,statement);
    }
}
