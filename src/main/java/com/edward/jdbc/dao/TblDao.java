package com.edward.jdbc.dao;

import com.edward.jdbc.javabean.Tbl;

import java.sql.SQLException;

public interface TblDao {

    public int addInfo(Tbl tbl);

    public Tbl[] queryInfo(String tbl_name) throws SQLException;

}
