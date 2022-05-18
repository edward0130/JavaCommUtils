package com.edward.jsoup.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {


    private static String classname;

    private static String url ;

    private static String username ;

    private static String password ;


    static {
        Properties pro = new Properties();

        try {
            InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");
            pro.load(in);

            classname = pro.getProperty("classname");
            url = pro.getProperty("url");
            username = pro.getProperty("username");
            password = pro.getProperty("password");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


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

    public static int executeUpdateWithID(String prepareSql, Object ... params)
    {
        Connection conn = null;

        PreparedStatement stmt = null;

        ResultSet rs = null;
        int key = 0;

        try {
            conn = getConn();
            stmt = conn.prepareStatement(prepareSql,Statement.RETURN_GENERATED_KEYS);

            if(params!=null)
            {
                for(int i=0; i<params.length; i++)
                {
                    stmt.setObject(i+1, params[i]);
                }
            }
            key = stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            if(rs.next())
            {
                key = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeAll(conn, stmt, rs);
        }
        return key;
    }

    public static int executeUpdate(String prepareSql, Object ... params)
    {
        Connection conn = null;

        PreparedStatement stmt = null;

        int key = 0;

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
            key = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeAll(conn, stmt);
        }
        return key;
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
