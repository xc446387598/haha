package com.shenzhenair.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBUtil {
	int bufferSize = 20 * 1024 * 1024;//设读取文件的缓存为20MB  
    ArrayList<String> column3string = new ArrayList<String>();  
    ArrayList<String> column13string = new ArrayList<String>();  
      
    String driver = "com.mysql.jdbc.Driver";  
    static String dbName = "dyform";  
    static String password = "root";  
    static String userName = "root";  
    static String url = "jdbc:mysql://localhost:3307/" + dbName + "?rewriteBatchedStatements=true";  
    static String sql = "select * from workinfo";  
    Connection conn = null;  
  
    public static Connection getConnection() {  
        Connection conn = null;  
        try {  
            Class.forName("com.mysql.jdbc.Driver");  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        }  
        try {  
            conn = DriverManager.getConnection(url, userName, password);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return conn;  
    }  
}
