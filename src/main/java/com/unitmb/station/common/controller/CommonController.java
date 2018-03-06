package com.unitmb.station.common.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CommonController {

    private static final Boolean SUCCESS = true;

    @Resource
    private HttpServletRequest request;

    @Resource
    private HttpServletResponse response;

    private void writer(boolean success, int code,  String message, Object data){
        PrintWriter writer = null;
        response.reset();
        response.setContentType("application/json; charset=UTF-8");
        try {
            writer = response.getWriter();
            JSONObject result = new JSONObject();
            result.put("success", success);
            result.put("message",message);
            result.put("code",code);
            result.put("data",data);
            System.out.println(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
            writer.write(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer != null)
                writer.close();
        }

    }

    protected void success () {
        writer(true, 0, "OK", null);
    }

    protected void success (Object data) {
        writer(true, 0, "OK", data);
    }

    protected void fail(){
        writer(false, -1, "Fail", null);
    }

    protected void fail(String message){
        writer(false, -1, (message == null || message.isEmpty()) ? "Fail" : message, null);
    }

    protected void fail(int code, String message){
        writer(false, code, (message == null || message.isEmpty()) ? "Fail" : message, null);
    }

    protected void fail(int code, String message, Object data){
        writer(false, code, (message == null || message.isEmpty()) ? "Fail" : message, data);
    }

}
