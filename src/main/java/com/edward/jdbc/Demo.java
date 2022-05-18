package com.edward.jdbc;

import com.edward.jdbc.dao.TblDao;
import com.edward.jdbc.dao.TblDaoImpl;
import com.edward.jdbc.javabean.Tbl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;

public class Demo {

    @Test
    public void insert(){

        TblDao tblDao = new TblDaoImpl();

        int n = 0;

        Tbl tbl = new Tbl();
        tbl.setTbl_id(2);
        tbl.setCreate_time(Integer.valueOf(String.valueOf(System.currentTimeMillis()/1000)));
        tbl.setOwn("hadoop");
        tbl.setName("tabl2");
        tbl.setRetention(5);
        tbl.setLast_access_time(Integer.valueOf(String.valueOf(System.currentTimeMillis()/1000)));

        n = tblDao.addInfo(tbl);
        if(n==1)
        {
            System.out.println("成功插入一条记录");
        }
        else {
            System.out.println("插入异常:"+n);
        }
    }

    /**
     *
     *
     *
     * @throws SQLException
     */
    @Test
    public void query() throws SQLException {

        /**  hive 获取表字段信息
        * select c.* from TBLS a, SDS b, COLUMNS_V2 c, DBS d
        *  where a.sd_id = b.sd_id and b.cd_id = c.cd_id
        *    and d.db_id = a.db_id and a.tbl_name = 't1'
        *    and d.name='default';
         * */
        TblDao tblDao = new TblDaoImpl();

        String tblName ="%t%";
        Tbl[] tbls = tblDao.queryInfo(tblName);

        for(Tbl t: tbls)
        {
            System.out.println(t.toString());
        }
    }
    @Test
    public void Test()
    {
        Tbl[] tbls =  new Tbl[2];
        tbls[0] = new Tbl();
        System.out.println(tbls[0].getTbl_id());
    }
}
