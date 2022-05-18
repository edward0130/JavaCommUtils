package com.edward.jsoup;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson2.JSONWriter.Feature.PrettyFormat;

public class JsonMain {

    @Test
    public void testJson() throws IOException {
        URL url = JsonMain.class.getClassLoader().getResource("datax.json");
        String path = url.getPath();
        String str = FileUtils.readFileToString(new File(path), "utf-8");
        System.out.println(str);
//        Map map = JSON.parseObject(str);
//
//        for(Object k: map.keySet())
//        {
//            System.out.println(k+":"+map.get(k));
//        }

//        //解析{}
//        JSONObject jsonObject = JSON.parseObject(str);
//        String strjob = jsonObject.getString("job");
//        System.out.println(strjob);
//        JSONObject job = JSON.parseObject(strjob);
//        String strcontent = job.getString("content");
//        System.out.println(strcontent);
//
//        JSONArray contentList = new JSONArray();
//        //解析[];
//        contentList = JSON.parseArray(strcontent);
//        JSONObject content = (JSONObject) contentList.get(0);
//        String strreader = content.getString("reader");
//        System.out.println(strreader);


        //对象转json
        Jobs jobs = new Jobs();
        Job job = new Job();
        Setting setting = new Setting();
        Speed speed = new Speed();
        Content content = new Content();
        Reader reader = new Reader();
        Parameter parameter = new Parameter();
        ArrayList<String> column = new ArrayList<>();
        column.add("col1");
        column.add("col2");
        parameter.setAccessId("1");
        parameter.setAccessKey("2");
        parameter.setPartition("3");
        parameter.setOdpsServer("odps");
        parameter.setProject("project");
        parameter.setTable("table");
        parameter.setTunnelServer("server");
        parameter.setPartition("partition");
        parameter.setColumn(column);

        speed.setChannel("1");
        setting.setSpeed(speed);

        reader.setName("odpsreader");
        reader.setParameter(parameter);
        content.setReader(reader);
        ArrayList<Content> contents = new ArrayList<>();
        contents.add(content);
        job.setSetting(setting);
        job.setContent(contents);
        jobs.setJob(job);

        String jsonstr = JSON.toJSONString(jobs, PrettyFormat);
        System.out.println(jsonstr);
        //json转对象

        Jobs job1 = JSON.parseObject(str, Jobs.class);
        System.out.println(job1.toString());

    }
}

class Speed{
    private String channel;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Speed{" +
                "channel='" + channel + '\'' +
                '}';
    }
}
class Setting {
    private Speed speed ;

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public Speed getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "speed=" + speed +
                '}';
    }
}

class Reader{
    private String name;
    private Parameter parameter;

    @Override
    public String toString() {
        return "Reader{" +
                "name='" + name + '\'' +
                ", parameter=" + parameter +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }
}

class Parameter{
    private String accessId;
    private String accessKey;
    private ArrayList<String> column = new ArrayList<String>();
    private String partition;
    private String odpsServer;
    private String project;
    private String table;
    private String tunnelServer;

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public ArrayList<String> getColumn() {
        return column;
    }

    public void setColumn(ArrayList<String> column) {
        this.column = column;
    }

    public String getPartition() {
        return partition;
    }

    public void setPartition(String partition) {
        this.partition = partition;
    }

    public String getOdpsServer() {
        return odpsServer;
    }

    public void setOdpsServer(String odpsServer) {
        this.odpsServer = odpsServer;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getTunnelServer() {
        return tunnelServer;
    }

    public void setTunnelServer(String tunnelServer) {
        this.tunnelServer = tunnelServer;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "accessId='" + accessId + '\'' +
                ", accessKey='" + accessKey + '\'' +
                ", column=" + column +
                ", partition='" + partition + '\'' +
                ", odpsServer='" + odpsServer + '\'' +
                ", project='" + project + '\'' +
                ", table='" + table + '\'' +
                ", tunnelServer='" + tunnelServer + '\'' +
                '}';
    }
}

class Content{
    private Reader reader ;

    private Reader writer;

    public Reader getWriter() {
        return writer;
    }

    public void setWriter(Reader writer) {
        this.writer = writer;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @Override
    public String toString() {
        return "Content{" +
                "reader=" + reader +
                ", writer=" + writer +
                '}';
    }
}

class Job {
    private Setting setting ;
    private List<Content> content = new ArrayList<Content>();

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Job{" +
                "setting=" + setting +
                ", content=" + content +
                '}';
    }
}


class Jobs
{
    private Job job;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Jobs{" +
                "job=" + job +
                '}';
    }
}
