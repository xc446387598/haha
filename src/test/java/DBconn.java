import java.io.BufferedInputStream;  
import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.SQLException;  
import java.util.ArrayList;  
  
public class DBconn {  
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
  
    public void readFile(String filename) throws SQLException, FileNotFoundException {  
        File file = new File(filename);  
        if (file.isFile() && file.exists()) {  
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));  
            InputStreamReader isr = new InputStreamReader(bis);  
            BufferedReader br = new BufferedReader(isr, bufferSize);  
            int count = 0;// 计数器  
            String lineTXT = null;  
            PreparedStatement pstmt = null;  
            String[] temp = null;  
            Connection conn = getConnection();  
            conn.setAutoCommit(false);// 设置数据手动提交，自己管理事务  
            String sql = "insert into workinfo(column3, column13) values (?, ?)";  
            pstmt = conn.prepareStatement(sql);  
            try {  
                while ((lineTXT = br.readLine()) != null) {  
                    temp = lineTXT.split("  ");  
                    pstmt.setString(1, temp[0]);  
                    pstmt.setString(2, temp[1]);  
                    pstmt.addBatch();// 用PreparedStatement的批量处理  
                    if (count % 5000 == 0) {// 当增加了500个批处理的时候再提交  
                        pstmt.executeBatch();// 执行批处理  
                        conn.commit();  
                        pstmt.clearBatch();  
                        //打印插入的条数  
                        //System.out.println("count: " + count);  
                    }  
                    count++;  
                }  
                pstmt.executeBatch();// 执行批处理  
                conn.commit();  
                pstmt.close();  
                conn.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    public void show() {  
        System.out.println("This is string:");  
        for (int i = 0; i < column3string.size(); i++) {  
            System.out.println(column3string.get(i));  
        }  
        System.out.println("This is integer:");  
        for (int i = 0; i < column13string.size(); i++) {  
            System.out.println(column13string.get(i));  
        }  
    }  
  
    public static void main(String[] args) throws FileNotFoundException {  
        System.out.println("开始........");  
        DBconn test = new DBconn();  
        //test.show();  
        long timeTestStart = System.currentTimeMillis();// 记录开始时间  
        try {  
            test.readFile("D:\\ProgramFiles\\tomcat7\\webapps\\ExcelDemo1\\upload\\1_attlog.dat");  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        System.out.println("succeed");  
        long timeTestEnd = System.currentTimeMillis();// 记录结束时间  
        long time = timeTestEnd - timeTestStart;  
        long secondTime = time / 1000;  
        System.out.println("Time:" + secondTime + " seconds");  
    }  
}  