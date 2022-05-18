package com.edward.jdbc.javabean;

public class Tbl {

    private int tbl_id = 0;
    private String own = null;
    private String name = null;

    private int create_time = 0;

    private int last_access_time = 0;

    public int getRetention() {
        return retention;
    }

    public void setRetention(int retention) {
        this.retention = retention;
    }

    private int  retention = 0;

    public int getLast_access_time() {
        return last_access_time;
    }

    public void setLast_access_time(int last_access_time) {
        this.last_access_time = last_access_time;
    }


    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public int getTbl_id() {
        return tbl_id;
    }

    public void setTbl_id(int tbl_id) {
        System.out.println("id:"+tbl_id);
        this.tbl_id = tbl_id;
    }

    public String getOwn() {
        return own;
    }

    public void setOwn(String own) {
        this.own = own;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "["+this.tbl_id+","+this.own+","+this.name+"]";
    }
}
