package com.test.testsystem.utils;

import lombok.Data;

/**
 *common return result
 */
@Data
public class JsonResult {
 private String code;
 private String msg;
 private Object data;

    public JsonResult(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static JsonResult success(){
        return new JsonResult("1","success",null);
    }


    public static JsonResult success(Object data){
        return new JsonResult("1","success",data);
    }

    public static JsonResult error(String msg){
        return new JsonResult("0",msg,null);
    }
}
