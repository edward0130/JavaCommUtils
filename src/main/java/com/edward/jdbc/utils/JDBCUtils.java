package com.edward.jdbc.utils;

import javax.xml.transform.Result;
import java.sql.*;

/**
 * JDBC工具类
 *
 */
public class JDBCUtils {

    private static String classname = "com.mysql.jdbc.Driver";

    private static String url = "jdbc:mysql://192.168.56.10:3306/DBTest";

    private static String username = "root";

    private static String password = "Abcd@1234";

    static{

        try {
            Class.forName(classname);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConn(){
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int executeUpdate(String prepareSql, Object[] params)
    {
        Connection conn = null;

        PreparedStatement stmt = null;

        int count = 0;
        try {
            conn = getConn();
            stmt = conn.prepareStatement(prepareSql);

            if(params!=null)
            {
                for(int i=0; i<params.length; i++)
                {
                    stmt.setObject(i+1, params[i]);
                }
            }
            count = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeAll(conn, stmt);
        }
        return count;
    }

    /**
     *
     * @param prepareSql
     * @param params
     * @return
     *
     * 没有关闭 conn, stmt;如果关闭 resultset获取不到值;
     *
     */
    public static ResultSet executeQuery(String prepareSql, Object[] params)
    {
        Connection conn = null;

        PreparedStatement stmt = null;

        ResultSet resultSet = null;

        try {
            conn = getConn();
            stmt = conn.prepareStatement(prepareSql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            if(params!=null)
            {
                for(int i=0; i<params.length; i++)
                {
                    stmt.setObject(i+1, params[i]);
                }
            }
            resultSet = stmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            //closeAll(conn,stmt);
        }
        return resultSet;
    }


    public static void closeAll(Connection conn, Statement stmt, ResultSet rs)
    {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if(stmt != null)
        {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if(conn != null)
        {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static void closeAll(Connection conn, Statement stmt)
    {
        if(stmt != null)
        {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if(conn != null)
        {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void closeAll(Connection conn)
    {
        if(conn != null)
        {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
