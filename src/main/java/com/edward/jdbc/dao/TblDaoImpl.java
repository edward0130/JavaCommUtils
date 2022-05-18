package com.edward.jdbc.dao;

import com.edward.jdbc.javabean.Tbl;
import com.edward.jdbc.utils.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TblDaoImpl implements TblDao{
    @Override
    public int addInfo(Tbl tbl) {

        String sql = "insert into TBLS(TBL_ID, CREATE_TIME, OWNER, TBL_NAME, LAST_ACCESS_TIME, RETENTION) values(?,?,?,?,?,?)";
        Object[] obj = new Object[6];
        obj[0]=tbl.getTbl_id();
        obj[1]=tbl.getCreate_time();
        obj[2]=tbl.getOwn();
        obj[3]=tbl.getName();
        obj[4]=tbl.getLast_access_time();
        obj[5]=tbl.getRetention();

        return JDBCUtils.executeUpdate(sql, obj);
    }

    @Override
    public Tbl[] queryInfo(String tbl_name) throws SQLException {

        String sql = "select TBL_ID, CREATE_TIME, OWNER, TBL_NAME, LAST_ACCESS_TIME, RETENTION " +
                "from TBLS where tbl_name like ?";

        Object[]obj = new Object[1];
        obj[0] = tbl_name;

        ResultSet resultSet = null;
        Tbl[] tbl = null;
        try {

            resultSet = JDBCUtils.executeQuery(sql, obj);

            //游标挪到最后，获取总条数；
            resultSet.last();
            int row = resultSet.getRow();
            //游标挪到第一条之前；
            resultSet.beforeFirst();

            //申请数组空间，存放结果集
            tbl = new Tbl[row];

            int i=0;
            while(resultSet.next())
            {
                //new tbl
                tbl[i]=new Tbl();
                tbl[i].setTbl_id(resultSet.getInt(1));
                tbl[i].setCreate_time(resultSet.getInt(2));
                tbl[i].setOwn(resultSet.getString(3));
                tbl[i].setName(resultSet.getString(4));
                tbl[i].setLast_access_time(resultSet.getInt(5));
                tbl[i].setRetention(resultSet.getInt(6));
                i++;
            };
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(resultSet != null)
                resultSet.close();
        }

        return tbl;
    }
}
