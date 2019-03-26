package com.suse.netcenter.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的返回类
 * @author Tangerg
 * @create 2018-10-22 18:58
 */
public class Msg {
    @Override
    public String toString() {
        return "Msg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    //状态码   200-成功    400-失败
    private int code;
    //提示信息
    private String msg;

    //要返回给浏览器的数据
    private Map<String, Object> data = new HashMap<String, Object>();

    public static Msg success(){
        Msg result = new Msg();
        result.setCode(200);
        return result;
    }

    public static Msg fail(){
        Msg result = new Msg();
        result.setCode(400);
        return result;
    }

    public Msg addMsg(String msg){
        this.setMsg(msg);
        return this;
    }

    public Msg addData(String key,Object value){
        this.getData().put(key, value);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

}